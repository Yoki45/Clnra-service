package io.clnra.c_l_n_r_a.rest;

import io.clnra.c_l_n_r_a.model.HWCDTO;
import io.clnra.c_l_n_r_a.service.HWCService;
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
@RequestMapping(value = "/api/hWCs", produces = MediaType.APPLICATION_JSON_VALUE)
public class HWCResource {

    private final HWCService hWCService;

    public HWCResource(final HWCService hWCService) {
        this.hWCService = hWCService;
    }

    @GetMapping
    public ResponseEntity<List<HWCDTO>> getAllHWCs() {
        return ResponseEntity.ok(hWCService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HWCDTO> getHWC(@PathVariable final Long id) {
        return ResponseEntity.ok(hWCService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createHWC(@RequestBody @Valid final HWCDTO hWCDTO) {
        return new ResponseEntity<>(hWCService.create(hWCDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateHWC(@PathVariable final Long id,
            @RequestBody @Valid final HWCDTO hWCDTO) {
        hWCService.update(id, hWCDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteHWC(@PathVariable final Long id) {
        hWCService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
