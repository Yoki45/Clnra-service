package io.clnra.c_l_n_r_a.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CBORegistrationDTO {

    private Long id;

    @NotNull
    @Size(max = 36)
    @JsonProperty("cBOName")
    private String cBOName;

    @NotNull
    @JsonProperty("cBOID")
    private Integer cBOID;

    @Size(max = 255)
    private String country;

    @NotNull
    @Size(max = 255)
    @JsonProperty("cBORegistered")
    private String cBORegistered;

    @Size(max = 255)
    private String regNumber;

    private Integer approxPopulation;

    @NotNull
    @JsonProperty("cBOAddress")
    private String cBOAddress;

    @Digits(integer = 10, fraction = 1)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(type = "string", example = "97.8")
    @JsonProperty("gPSLatitude")
    private BigDecimal gPSLatitude;

    @Digits(integer = 10, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(type = "string", example = "48.08")
    @JsonProperty("gPSLongitude")
    private BigDecimal gPSLongitude;

    @NotNull
    @Size(max = 255)
    @JsonProperty("cBOContact")
    private String cBOContact;

    @NotNull
    @Size(max = 255)
    @JsonProperty("cBOConstitution")
    private String cBOConstitution;

    private List<Long> managements;

    @JsonProperty("cBOPartnerss")
    private List<Long> cBOPartnerss;

    private List<Long> propertiess;

    private List<Long> projects;

    private List<Long> activitiess;

    @JsonProperty("cBOHuntings")
    private List<Long> cBOHuntings;

    private List<Long> wildlifePPs;

    @JsonProperty("cBOTourisms")
    private List<Long> cBOTourisms;

    private List<Long> humanConflicts;

    @JsonProperty("aPs")
    private List<Long> aPs;

    @JsonProperty("cSRCBOs")
    private List<Long> cSRCBOs;

    @JsonProperty("cBOPatrolss")
    private List<Long> cBOPatrolss;

}
