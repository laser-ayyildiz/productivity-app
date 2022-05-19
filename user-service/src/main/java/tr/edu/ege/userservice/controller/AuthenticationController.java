package tr.edu.ege.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tr.edu.ege.userservice.dto.LoginDto;
import tr.edu.ege.userservice.dto.RegisterDto;
import tr.edu.ege.userservice.dto.UserDto;
import tr.edu.ege.userservice.service.AuthenticationService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private AuthenticationService authenticationService;


    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid LoginDto loginDto) {
        return new ResponseEntity<>(authenticationService.login(loginDto), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid RegisterDto registerDto) {
        return new ResponseEntity<>(authenticationService.register(registerDto), HttpStatus.OK);
    }
}
