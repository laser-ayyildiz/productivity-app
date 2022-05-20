package tr.edu.ege.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.edu.ege.userservice.dto.JwtResponse;
import tr.edu.ege.userservice.dto.SignInRequest;
import tr.edu.ege.userservice.dto.SignupRequest;
import tr.edu.ege.userservice.dto.UserDto;
import tr.edu.ege.userservice.exception.ServiceException;
import tr.edu.ege.userservice.exception.UserError;
import tr.edu.ege.userservice.model.User;
import tr.edu.ege.userservice.repository.UserRepository;
import tr.edu.ege.userservice.security.JwtUtils;
import tr.edu.ege.userservice.security.MyUserDetails;
import tr.edu.ege.userservice.service.AuthenticationService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    @Override
    public JwtResponse signIn(SignInRequest signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername());
    }

    @Override
    public UserDto signUp(SignupRequest signUpRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getUsername()))) {
            throw new ServiceException(UserError.USERNAME_ALREADY_EXISTS);
        }
        if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
            throw new ServiceException(UserError.EMAIL_ALREADY_EXISTS);
        }
        User user = User.builder()
                .name(signUpRequest.getName())
                .surname(signUpRequest.getSurname())
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(encoder.encode(signUpRequest.getPassword()))
                .build();
        user = userRepository.save(user);
        return UserDto.of(user);
    }
}
