package io.clnra.c_l_n_r_a.rest;

import io.clnra.c_l_n_r_a.model.GameCountDTO;
import io.clnra.c_l_n_r_a.service.GameCountService;
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
@RequestMapping(value = "/api/gameCounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class GameCountResource {

    private final GameCountService gameCountService;

    public GameCountResource(final GameCountService gameCountService) {
        this.gameCountService = gameCountService;
    }

    @GetMapping
    public ResponseEntity<List<GameCountDTO>> getAllGameCounts() {
        return ResponseEntity.ok(gameCountService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameCountDTO> getGameCount(@PathVariable final Long id) {
        return ResponseEntity.ok(gameCountService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createGameCount(
            @RequestBody @Valid final GameCountDTO gameCountDTO) {
        return new ResponseEntity<>(gameCountService.create(gameCountDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGameCount(@PathVariable final Long id,
            @RequestBody @Valid final GameCountDTO gameCountDTO) {
        gameCountService.update(id, gameCountDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteGameCount(@PathVariable final Long id) {
        gameCountService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
