package io.clnra.c_l_n_r_a.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {

    private Long id;

    @NotNull
    @Size(max = 36)
    private String userEmail;

    @NotNull
    @Size(max = 25)
    private String password;

}
