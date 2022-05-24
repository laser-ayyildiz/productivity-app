package tr.edu.ege.apigateway.service;

import tr.edu.ege.apigateway.dto.JwtResponse;
import tr.edu.ege.apigateway.dto.SigninRequest;
import tr.edu.ege.apigateway.dto.SignupRequest;
import tr.edu.ege.apigateway.dto.UserDto;

public interface AuthenticationService {

    JwtResponse signIn(SigninRequest signInRequest);

    UserDto signUp(SignupRequest signupRequest);
}
