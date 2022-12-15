package io.clnra.c_l_n_r_a.rest;

import io.clnra.c_l_n_r_a.model.CBORegistrationDTO;
import io.clnra.c_l_n_r_a.service.CBORegistrationService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/cBORegistrations", produces = MediaType.APPLICATION_JSON_VALUE)
public class CBORegistrationResource {

    private final CBORegistrationService cBORegistrationService;

    public CBORegistrationResource(final CBORegistrationService cBORegistrationService) {
        this.cBORegistrationService = cBORegistrationService;
    }

    @GetMapping
    public ResponseEntity<List<CBORegistrationDTO>> getAllCBORegistrations() {
        return ResponseEntity.ok(cBORegistrationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CBORegistrationDTO> getCBORegistration(@PathVariable final Long id) {
        return ResponseEntity.ok(cBORegistrationService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createCBORegistration(
            @RequestBody @Valid final CBORegistrationDTO cBORegistrationDTO) {
        return new ResponseEntity<>(cBORegistrationService.create(cBORegistrationDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCBORegistration(@PathVariable final Long id,
            @RequestBody @Valid final CBORegistrationDTO cBORegistrationDTO) {
        cBORegistrationService.update(id, cBORegistrationDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteCBORegistration(@PathVariable final Long id) {
        cBORegistrationService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
