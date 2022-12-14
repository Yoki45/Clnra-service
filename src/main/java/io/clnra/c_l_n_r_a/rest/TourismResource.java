package io.clnra.c_l_n_r_a.rest;

import io.clnra.c_l_n_r_a.model.TourismDTO;
import io.clnra.c_l_n_r_a.service.TourismService;
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
@RequestMapping(value = "/api/tourisms", produces = MediaType.APPLICATION_JSON_VALUE)
public class TourismResource {

    private final TourismService tourismService;

    public TourismResource(final TourismService tourismService) {
        this.tourismService = tourismService;
    }

    @GetMapping
    public ResponseEntity<List<TourismDTO>> getAllTourisms() {
        return ResponseEntity.ok(tourismService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TourismDTO> getTourism(@PathVariable final Long id) {
        return ResponseEntity.ok(tourismService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createTourism(@RequestBody @Valid final TourismDTO tourismDTO) {
        return new ResponseEntity<>(tourismService.create(tourismDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTourism(@PathVariable final Long id,
            @RequestBody @Valid final TourismDTO tourismDTO) {
        tourismService.update(id, tourismDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteTourism(@PathVariable final Long id) {
        tourismService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
