package io.clnra.c_l_n_r_a.rest;

import io.clnra.c_l_n_r_a.model.PatrolsDTO;
import io.clnra.c_l_n_r_a.service.PatrolsService;
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
@RequestMapping(value = "/api/patrolss", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatrolsResource {

    private final PatrolsService patrolsService;

    public PatrolsResource(final PatrolsService patrolsService) {
        this.patrolsService = patrolsService;
    }

    @GetMapping
    public ResponseEntity<List<PatrolsDTO>> getAllPatrolss() {
        return ResponseEntity.ok(patrolsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatrolsDTO> getPatrols(@PathVariable final Long id) {
        return ResponseEntity.ok(patrolsService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createPatrols(@RequestBody @Valid final PatrolsDTO patrolsDTO) {
        return new ResponseEntity<>(patrolsService.create(patrolsDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePatrols(@PathVariable final Long id,
            @RequestBody @Valid final PatrolsDTO patrolsDTO) {
        patrolsService.update(id, patrolsDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deletePatrols(@PathVariable final Long id) {
        patrolsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
