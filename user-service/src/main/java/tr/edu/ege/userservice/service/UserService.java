package tr.edu.ege.userservice.service;

import tr.edu.ege.userservice.dto.UserDto;

public interface UserService {

    UserDto get(Long id);

    void delete(Long id);
}
