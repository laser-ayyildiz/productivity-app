package tr.edu.ege.userservice.service;

import tr.edu.ege.userservice.dto.UserDto;
import tr.edu.ege.userservice.dto.UserUpdateDto;

public interface UserService {

    UserDto get(Long id);

    void delete(Long id);

    UserDto update(Long id, UserUpdateDto userUpdateDto);
}
