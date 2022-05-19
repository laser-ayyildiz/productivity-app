package tr.edu.ege.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignInRequest {

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 50)
    private String username;

    @NotNull
    @NotEmpty
    @Size(min = 6, max = 50)
    private String password;
}
