package tr.edu.ege.userservice.service;

import tr.edu.ege.userservice.dto.JwtResponse;
import tr.edu.ege.userservice.dto.SignInRequest;
import tr.edu.ege.userservice.dto.SignupRequest;
import tr.edu.ege.userservice.dto.UserDto;

public interface AuthenticationService {

    JwtResponse signIn(SignInRequest signInRequest);

    UserDto signUp(SignupRequest signupRequest);
}
