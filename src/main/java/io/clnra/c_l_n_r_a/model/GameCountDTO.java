package io.clnra.c_l_n_r_a.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GameCountDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String gameCount;

    @Size(max = 255)
    private String animalType;

    private Integer adultMale;

    private Integer adultFemale;

    private Integer subAdultMale;

    private Integer subadultFemale;

    private Integer juvenileMale;

    private Integer juvenileFemale;

}
