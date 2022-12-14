package io.clnra.c_l_n_r_a.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ErrorResponse {

    private Integer httpStatus;
    private String exception;
    private String message;
    private List<FieldError> fieldErrors;

}
