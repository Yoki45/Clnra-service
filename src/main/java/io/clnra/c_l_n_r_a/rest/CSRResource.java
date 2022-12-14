package io.clnra.c_l_n_r_a.rest;

import io.clnra.c_l_n_r_a.model.CSRDTO;
import io.clnra.c_l_n_r_a.service.CSRService;
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
@RequestMapping(value = "/api/cSRs", produces = MediaType.APPLICATION_JSON_VALUE)
public class CSRResource {

    private final CSRService cSRService;

    public CSRResource(final CSRService cSRService) {
        this.cSRService = cSRService;
    }

    @GetMapping
    public ResponseEntity<List<CSRDTO>> getAllCSRs() {
        return ResponseEntity.ok(cSRService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CSRDTO> getCSR(@PathVariable final Long id) {
        return ResponseEntity.ok(cSRService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createCSR(@RequestBody @Valid final CSRDTO cSRDTO) {
        return new ResponseEntity<>(cSRService.create(cSRDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCSR(@PathVariable final Long id,
            @RequestBody @Valid final CSRDTO cSRDTO) {
        cSRService.update(id, cSRDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteCSR(@PathVariable final Long id) {
        cSRService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
