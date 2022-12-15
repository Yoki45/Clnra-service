package io.clnra.c_l_n_r_a.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PartnersDTO {

    private Long id;

    @NotNull
    @Size(max = 36)
    private String partnerName;

    @NotNull
    @Size(max = 255)
    private String partnerType;

    @NotNull
    private String partnerAddress;

    @NotNull
    @Size(max = 255)
    private String partnerEmail;

    @Size(max = 255)
    @JsonProperty("cBOName")
    private String cBOName;

}
