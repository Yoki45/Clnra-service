package io.clnra.c_l_n_r_a.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


@Getter
@Setter
public class HWCDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    @JsonProperty("hWCLog")
    private String hWCLog;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Size(max = 255)
    private String problemSpecies;

    @Size(max = 255)
    private String placeIncident;

    @Size(max = 255)
    @JsonProperty("hWCType")
    private String hWCType;

    @Size(max = 255)
    private String livestockKilled;

    private Integer valueOfLivestock;

    @Size(max = 255)
    private String livestockImage;

    @Size(max = 255)
    private String cropsDamaged;

    private Integer valueOfCrops;

    private Integer infrastructureValue;

    private Integer numberOfMales;

    private Integer numberOfFemale;

    @Size(max = 255)
    private String damageConfirmed;

    @Size(max = 255)
    private String reported;

    @Size(max = 255)
    private String respondedBy;

    @Size(max = 255)
    private String actionTaken;

    @Size(max = 255)
    private String complainantName;

    @Size(max = 255)
    private String addressComplainant;

    @Size(max = 255)
    private String investigatedBy;

    @Size(max = 255)
    private String comments;

}
