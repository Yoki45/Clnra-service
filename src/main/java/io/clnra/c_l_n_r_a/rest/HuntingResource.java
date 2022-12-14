package io.clnra.c_l_n_r_a.rest;

import io.clnra.c_l_n_r_a.model.HuntingDTO;
import io.clnra.c_l_n_r_a.service.HuntingService;
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
@RequestMapping(value = "/api/huntings", produces = MediaType.APPLICATION_JSON_VALUE)
public class HuntingResource {

    private final HuntingService huntingService;

    public HuntingResource(final HuntingService huntingService) {
        this.huntingService = huntingService;
    }

    @GetMapping
    public ResponseEntity<List<HuntingDTO>> getAllHuntings() {
        return ResponseEntity.ok(huntingService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HuntingDTO> getHunting(@PathVariable final Long id) {
        return ResponseEntity.ok(huntingService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createHunting(@RequestBody @Valid final HuntingDTO huntingDTO) {
        return new ResponseEntity<>(huntingService.create(huntingDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateHunting(@PathVariable final Long id,
            @RequestBody @Valid final HuntingDTO huntingDTO) {
        huntingService.update(id, huntingDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteHunting(@PathVariable final Long id) {
        huntingService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
