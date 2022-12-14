package io.clnra.c_l_n_r_a.rest;

import io.clnra.c_l_n_r_a.model.PartnersDTO;
import io.clnra.c_l_n_r_a.service.PartnersService;
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
@RequestMapping(value = "/api/partnerss", produces = MediaType.APPLICATION_JSON_VALUE)
public class PartnersResource {

    private final PartnersService partnersService;

    public PartnersResource(final PartnersService partnersService) {
        this.partnersService = partnersService;
    }

    @GetMapping
    public ResponseEntity<List<PartnersDTO>> getAllPartnerss() {
        return ResponseEntity.ok(partnersService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartnersDTO> getPartners(@PathVariable final Long id) {
        return ResponseEntity.ok(partnersService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createPartners(@RequestBody @Valid final PartnersDTO partnersDTO) {
        return new ResponseEntity<>(partnersService.create(partnersDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePartners(@PathVariable final Long id,
            @RequestBody @Valid final PartnersDTO partnersDTO) {
        partnersService.update(id, partnersDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deletePartners(@PathVariable final Long id) {
        partnersService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
