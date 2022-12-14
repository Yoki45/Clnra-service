package io.clnra.c_l_n_r_a.rest;

import io.clnra.c_l_n_r_a.model.AdministrativeDTO;
import io.clnra.c_l_n_r_a.service.AdministrativeService;
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
@RequestMapping(value = "/api/administratives", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdministrativeResource {

    private final AdministrativeService administrativeService;

    public AdministrativeResource(final AdministrativeService administrativeService) {
        this.administrativeService = administrativeService;
    }

    @GetMapping
    public ResponseEntity<List<AdministrativeDTO>> getAllAdministratives() {
        return ResponseEntity.ok(administrativeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministrativeDTO> getAdministrative(@PathVariable final Long id) {
        return ResponseEntity.ok(administrativeService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createAdministrative(
            @RequestBody @Valid final AdministrativeDTO administrativeDTO) {
        return new ResponseEntity<>(administrativeService.create(administrativeDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAdministrative(@PathVariable final Long id,
            @RequestBody @Valid final AdministrativeDTO administrativeDTO) {
        administrativeService.update(id, administrativeDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteAdministrative(@PathVariable final Long id) {
        administrativeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
