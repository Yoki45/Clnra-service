package io.clnra.c_l_n_r_a.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CBOManagementDTO {

    private Long id;

    @NotNull
    @Size(max = 36)
    private String fullName;

    @NotNull
    @Size(max = 255)
    private String gender;

    @NotNull
    @Size(max = 255)
    @JsonProperty("cBOName")
    private String cBOName;

    @NotNull
    @Size(max = 255)
    private String relationToCBO;

    private Integer yoB;

    @NotNull
    private Integer phoneNumber;

    @NotNull
    @Size(max = 30)
    private String emailAddress;

    @NotNull
    @Size(max = 255)
    private String userStatus;

}
