package tr.edu.ege.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.edu.ege.userservice.dto.JwtResponse;
import tr.edu.ege.userservice.dto.SignInRequest;
import tr.edu.ege.userservice.dto.SignupRequest;
import tr.edu.ege.userservice.dto.UserDto;
import tr.edu.ege.userservice.service.AuthenticationService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.uri}/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }

}
