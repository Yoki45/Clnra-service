package io.clnra.c_l_n_r_a.model;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Country {

    @Valid
    private AdministrativeDTO botswanaZimbabweMalawiTanzaniaZambia;

}
