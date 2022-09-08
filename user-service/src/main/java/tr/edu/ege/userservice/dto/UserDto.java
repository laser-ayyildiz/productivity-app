package tr.edu.ege.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.edu.ege.userservice.model.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;

    private String name;

    private String surname;

    private String username;

    private String email;

    public static UserDto of(User user) {
        return UserDto.builder().id(user.getId()).username(user.getUsername()).email(user.getEmail())
                .name(user.getName()).surname(user.getSurname()).build();
    }
}
