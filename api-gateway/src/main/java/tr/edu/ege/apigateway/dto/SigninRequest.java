package tr.edu.ege.apigateway.dto;

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
public class SigninRequest {

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 50)
    private String username;

    @NotNull
    @NotEmpty
    @Size(min = 6, max = 50)
    private String password;
}
