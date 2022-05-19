package tr.edu.ege.userservice.service;

import tr.edu.ege.userservice.dto.LoginDto;
import tr.edu.ege.userservice.dto.RegisterDto;
import tr.edu.ege.userservice.dto.UserDto;

public interface AuthenticationService {

    UserDto login(LoginDto loginDto);

    UserDto register(RegisterDto registerDto);
}
