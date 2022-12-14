package io.clnra.c_l_n_r_a.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class HuntingDTO {

    private Long id;

    @NotNull
    private UUID huntingLog;

    @NotNull
    @Size(max = 255)
    private String animalName;

    @NotNull
    private Integer year;

    private Integer allocatedQuota;

    private Long trophyFee;

    private Integer numberHunted;

    private Integer fixedQuota;

    private Integer optionalQuota;

}
