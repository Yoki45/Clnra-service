package io.clnra.c_l_n_r_a.service;

import io.clnra.c_l_n_r_a.domain.CSR;
import io.clnra.c_l_n_r_a.model.CSRDTO;
import io.clnra.c_l_n_r_a.repos.CSRRepository;
import io.clnra.c_l_n_r_a.util.NotFoundException;
import io.clnra.c_l_n_r_a.util.WebUtils;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CSRService {

    private final CSRRepository cSRRepository;

    public CSRService(final CSRRepository cSRRepository) {
        this.cSRRepository = cSRRepository;
    }

    public List<CSRDTO> findAll() {
        final List<CSR> cSRs = cSRRepository.findAll(Sort.by("id"));
        return cSRs.stream()
                .map((cSR) -> mapToDTO(cSR, new CSRDTO()))
                .collect(Collectors.toList());
    }

    public CSRDTO get(final Long id) {
        return cSRRepository.findById(id)
                .map(cSR -> mapToDTO(cSR, new CSRDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final CSRDTO cSRDTO) {
        final CSR cSR = new CSR();
        mapToEntity(cSRDTO, cSR);
        return cSRRepository.save(cSR).getId();
    }

    public void update(final Long id, final CSRDTO cSRDTO) {
        final CSR cSR = cSRRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(cSRDTO, cSR);
        cSRRepository.save(cSR);
    }

    public void delete(final Long id) {
        cSRRepository.deleteById(id);
    }

    private CSRDTO mapToDTO(final CSR cSR, final CSRDTO cSRDTO) {
        cSRDTO.setId(cSR.getId());
        cSRDTO.setCRSLog(cSR.getCRSLog());
        return cSRDTO;
    }

    private CSR mapToEntity(final CSRDTO cSRDTO, final CSR cSR) {
        cSR.setCRSLog(cSRDTO.getCRSLog());
        return cSR;
    }

    @Transactional
    public String getReferencedWarning(final Long id) {
        final CSR cSR = cSRRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        if (!cSR.getCSRCBOCBORegistrations().isEmpty()) {
            return WebUtils.getMessage("cSR.cBORegistration.manyToMany.referenced", cSR.getCSRCBOCBORegistrations().iterator().next().getId());
        }
        return null;
    }

}
