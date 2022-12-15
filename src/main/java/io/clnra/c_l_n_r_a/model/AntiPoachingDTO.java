package io.clnra.c_l_n_r_a.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


@Getter
@Setter
public class AntiPoachingDTO {

    private Long id;

    @NotNull
    private UUID antiPoachingLog;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Size(max = 255)
    private String siteName;

    private Integer numberOfGuards;

    private Integer numberOfArms;

    @Size(max = 255)
    @JsonProperty("aPCoverageArea")
    private String aPCoverageArea;

    @Size(max = 255)
    private String measureOfAPEffort;

    @Size(max = 255)
    private String indicatorIllegalActivities;

    @Size(max = 255)
    private String encounterRateIllegalActivities;

    @Size(max = 255)
    private String primaryIndicators;

    @Size(max = 255)
    private String secondaryIndicators;

    @Size(max = 255)
    @JsonProperty("aPAction")
    private String aPAction;

}
