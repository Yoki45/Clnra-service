package io.clnra.c_l_n_r_a.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AdministrativeDTO {

    private Long id;

    @NotNull
    @Size(max = 15)
    private String country;

    @NotNull
    @Size(max = 5)
    private String countryCode;

    @NotNull
    @Size(max = 25)
    private String countryCurrency;

    @NotNull
    @Size(max = 5)
    private String currencyCode;

    @Size(max = 35)
    private String regionName;

    @Size(max = 30)
    private String province;

    @Size(max = 30)
    private String district;

    @Size(max = 30)
    private String subDistrict;

    @Size(max = 30)
    private String ward;

    @JsonProperty("cBORegistration")
    private Long cBORegistration;

}
