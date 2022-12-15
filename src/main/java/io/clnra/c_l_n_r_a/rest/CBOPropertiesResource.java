package io.clnra.c_l_n_r_a.rest;

import io.clnra.c_l_n_r_a.model.CBOPropertiesDTO;
import io.clnra.c_l_n_r_a.service.CBOPropertiesService;
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
@RequestMapping(value = "/api/cBOPropertiess", produces = MediaType.APPLICATION_JSON_VALUE)
public class CBOPropertiesResource {

    private final CBOPropertiesService cBOPropertiesService;

    public CBOPropertiesResource(final CBOPropertiesService cBOPropertiesService) {
        this.cBOPropertiesService = cBOPropertiesService;
    }

    @GetMapping
    public ResponseEntity<List<CBOPropertiesDTO>> getAllCBOPropertiess() {
        return ResponseEntity.ok(cBOPropertiesService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CBOPropertiesDTO> getCBOProperties(@PathVariable final Long id) {
        return ResponseEntity.ok(cBOPropertiesService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createCBOProperties(
            @RequestBody @Valid final CBOPropertiesDTO cBOPropertiesDTO) {
        return new ResponseEntity<>(cBOPropertiesService.create(cBOPropertiesDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCBOProperties(@PathVariable final Long id,
            @RequestBody @Valid final CBOPropertiesDTO cBOPropertiesDTO) {
        cBOPropertiesService.update(id, cBOPropertiesDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteCBOProperties(@PathVariable final Long id) {
        cBOPropertiesService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
