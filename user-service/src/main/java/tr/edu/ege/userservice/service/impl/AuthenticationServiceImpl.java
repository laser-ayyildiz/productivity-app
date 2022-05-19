package tr.edu.ege.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.edu.ege.userservice.dto.LoginDto;
import tr.edu.ege.userservice.dto.RegisterDto;
import tr.edu.ege.userservice.dto.UserDto;
import tr.edu.ege.userservice.repository.UserRepository;
import tr.edu.ege.userservice.service.AuthenticationService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private UserRepository userRepository;

    @Override
    public UserDto login(LoginDto loginDto) {
        return null;
    }

    @Override
    public UserDto register(RegisterDto registerDto) {
        return null;
    }
}
