package tr.edu.ege.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tr.edu.ege.userservice.dto.UserDto;
import tr.edu.ege.userservice.dto.UserUpdateDto;
import tr.edu.ege.userservice.exception.GlobalError;
import tr.edu.ege.userservice.exception.ServiceException;
import tr.edu.ege.userservice.security.MyUserDetails;
import tr.edu.ege.userservice.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("${app.uri}/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        MyUserDetails loggedInUser = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        if (!loggedInUser.getId().equals(id)) {
            throw new ServiceException(GlobalError.FORBIDDEN);
        }
        userService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody @Valid UserUpdateDto userUpdateDto) {
        MyUserDetails loggedInUser = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        if (!loggedInUser.getId().equals(id)) {
            throw new ServiceException(GlobalError.FORBIDDEN);
        }
        return ResponseEntity.ok(userService.update(id, userUpdateDto));
    }
}
