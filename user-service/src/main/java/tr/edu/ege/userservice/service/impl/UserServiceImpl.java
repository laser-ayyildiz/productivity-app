package tr.edu.ege.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.edu.ege.userservice.dto.UserDto;
import tr.edu.ege.userservice.exception.ServiceException;
import tr.edu.ege.userservice.exception.UserError;
import tr.edu.ege.userservice.model.User;
import tr.edu.ege.userservice.repository.UserRepository;
import tr.edu.ege.userservice.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto get(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ServiceException(UserError.NOT_FOUND));
        return UserDto.of(user);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ServiceException(UserError.NOT_FOUND));
        userRepository.delete(user);
    }
}
