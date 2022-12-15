package io.clnra.c_l_n_r_a.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TourismDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String tourismLog;

}
