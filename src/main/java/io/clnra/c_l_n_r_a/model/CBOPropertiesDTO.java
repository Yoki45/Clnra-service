package io.clnra.c_l_n_r_a.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CBOPropertiesDTO {

    private Long id;

    @NotNull
    @Size(max = 36)
    private String propertyName;

    @NotNull
    @Size(max = 255)
    private String propertyType;

    @Size(max = 35)
    private String location;

    private Integer propertyValue;

    @Size(max = 255)
    private String registrationTag;

}
