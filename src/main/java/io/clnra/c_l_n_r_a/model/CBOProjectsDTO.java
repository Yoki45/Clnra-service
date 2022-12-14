package io.clnra.c_l_n_r_a.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


@Getter
@Setter
public class CBOProjectsDTO {

    private Long id;

    @NotNull
    @Size(max = 45)
    private String projectName;

    private Integer projectDuration;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate projectStartDate;

    private Integer projectValue;

    @Size(max = 255)
    private String projectObjectives;

    @Size(max = 255)
    private String fundingAgency;

    @Size(max = 255)
    private String contactPerson;

}
