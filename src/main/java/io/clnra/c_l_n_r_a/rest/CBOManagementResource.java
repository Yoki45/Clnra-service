package io.clnra.c_l_n_r_a.rest;

import io.clnra.c_l_n_r_a.model.CBOManagementDTO;
import io.clnra.c_l_n_r_a.service.CBOManagementService;
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
@RequestMapping(value = "/api/cBOManagements", produces = MediaType.APPLICATION_JSON_VALUE)
public class CBOManagementResource {

    private final CBOManagementService cBOManagementService;

    public CBOManagementResource(final CBOManagementService cBOManagementService) {
        this.cBOManagementService = cBOManagementService;
    }

    @GetMapping
    public ResponseEntity<List<CBOManagementDTO>> getAllCBOManagements() {
        return ResponseEntity.ok(cBOManagementService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CBOManagementDTO> getCBOManagement(@PathVariable final Long id) {
        return ResponseEntity.ok(cBOManagementService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createCBOManagement(
            @RequestBody @Valid final CBOManagementDTO cBOManagementDTO) {
        return new ResponseEntity<>(cBOManagementService.create(cBOManagementDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCBOManagement(@PathVariable final Long id,
            @RequestBody @Valid final CBOManagementDTO cBOManagementDTO) {
        cBOManagementService.update(id, cBOManagementDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteCBOManagement(@PathVariable final Long id) {
        cBOManagementService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
