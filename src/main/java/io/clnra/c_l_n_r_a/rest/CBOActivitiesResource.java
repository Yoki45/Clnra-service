package io.clnra.c_l_n_r_a.rest;

import io.clnra.c_l_n_r_a.model.CBOActivitiesDTO;
import io.clnra.c_l_n_r_a.service.CBOActivitiesService;
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
@RequestMapping(value = "/api/cBOActivitiess", produces = MediaType.APPLICATION_JSON_VALUE)
public class CBOActivitiesResource {

    private final CBOActivitiesService cBOActivitiesService;

    public CBOActivitiesResource(final CBOActivitiesService cBOActivitiesService) {
        this.cBOActivitiesService = cBOActivitiesService;
    }

    @GetMapping
    public ResponseEntity<List<CBOActivitiesDTO>> getAllCBOActivitiess() {
        return ResponseEntity.ok(cBOActivitiesService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CBOActivitiesDTO> getCBOActivities(@PathVariable final Long id) {
        return ResponseEntity.ok(cBOActivitiesService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createCBOActivities(
            @RequestBody @Valid final CBOActivitiesDTO cBOActivitiesDTO) {
        return new ResponseEntity<>(cBOActivitiesService.create(cBOActivitiesDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCBOActivities(@PathVariable final Long id,
            @RequestBody @Valid final CBOActivitiesDTO cBOActivitiesDTO) {
        cBOActivitiesService.update(id, cBOActivitiesDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteCBOActivities(@PathVariable final Long id) {
        cBOActivitiesService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
