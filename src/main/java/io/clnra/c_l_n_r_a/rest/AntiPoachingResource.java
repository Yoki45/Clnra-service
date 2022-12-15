package io.clnra.c_l_n_r_a.rest;

import io.clnra.c_l_n_r_a.model.AntiPoachingDTO;
import io.clnra.c_l_n_r_a.service.AntiPoachingService;
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
@RequestMapping(value = "/api/antiPoachings", produces = MediaType.APPLICATION_JSON_VALUE)
public class AntiPoachingResource {

    private final AntiPoachingService antiPoachingService;

    public AntiPoachingResource(final AntiPoachingService antiPoachingService) {
        this.antiPoachingService = antiPoachingService;
    }

    @GetMapping
    public ResponseEntity<List<AntiPoachingDTO>> getAllAntiPoachings() {
        return ResponseEntity.ok(antiPoachingService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AntiPoachingDTO> getAntiPoaching(@PathVariable final Long id) {
        return ResponseEntity.ok(antiPoachingService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createAntiPoaching(
            @RequestBody @Valid final AntiPoachingDTO antiPoachingDTO) {
        return new ResponseEntity<>(antiPoachingService.create(antiPoachingDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAntiPoaching(@PathVariable final Long id,
            @RequestBody @Valid final AntiPoachingDTO antiPoachingDTO) {
        antiPoachingService.update(id, antiPoachingDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteAntiPoaching(@PathVariable final Long id) {
        antiPoachingService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
