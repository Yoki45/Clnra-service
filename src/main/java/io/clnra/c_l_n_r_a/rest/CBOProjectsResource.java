package io.clnra.c_l_n_r_a.rest;

import io.clnra.c_l_n_r_a.model.CBOProjectsDTO;
import io.clnra.c_l_n_r_a.service.CBOProjectsService;
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
@RequestMapping(value = "/api/cBOProjectss", produces = MediaType.APPLICATION_JSON_VALUE)
public class CBOProjectsResource {

    private final CBOProjectsService cBOProjectsService;

    public CBOProjectsResource(final CBOProjectsService cBOProjectsService) {
        this.cBOProjectsService = cBOProjectsService;
    }

    @GetMapping
    public ResponseEntity<List<CBOProjectsDTO>> getAllCBOProjectss() {
        return ResponseEntity.ok(cBOProjectsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CBOProjectsDTO> getCBOProjects(@PathVariable final Long id) {
        return ResponseEntity.ok(cBOProjectsService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createCBOProjects(
            @RequestBody @Valid final CBOProjectsDTO cBOProjectsDTO) {
        return new ResponseEntity<>(cBOProjectsService.create(cBOProjectsDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCBOProjects(@PathVariable final Long id,
            @RequestBody @Valid final CBOProjectsDTO cBOProjectsDTO) {
        cBOProjectsService.update(id, cBOProjectsDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteCBOProjects(@PathVariable final Long id) {
        cBOProjectsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
