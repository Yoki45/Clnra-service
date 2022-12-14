package io.clnra.c_l_n_r_a.service;

import io.clnra.c_l_n_r_a.domain.CBOManagement;
import io.clnra.c_l_n_r_a.model.CBOManagementDTO;
import io.clnra.c_l_n_r_a.repos.CBOManagementRepository;
import io.clnra.c_l_n_r_a.util.NotFoundException;
import io.clnra.c_l_n_r_a.util.WebUtils;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CBOManagementService {

    private final CBOManagementRepository cBOManagementRepository;

    public CBOManagementService(final CBOManagementRepository cBOManagementRepository) {
        this.cBOManagementRepository = cBOManagementRepository;
    }

    public List<CBOManagementDTO> findAll() {
        final List<CBOManagement> cBOManagements = cBOManagementRepository.findAll(Sort.by("id"));
        return cBOManagements.stream()
                .map((cBOManagement) -> mapToDTO(cBOManagement, new CBOManagementDTO()))
                .collect(Collectors.toList());
    }

    public CBOManagementDTO get(final Long id) {
        return cBOManagementRepository.findById(id)
                .map(cBOManagement -> mapToDTO(cBOManagement, new CBOManagementDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final CBOManagementDTO cBOManagementDTO) {
        final CBOManagement cBOManagement = new CBOManagement();
        mapToEntity(cBOManagementDTO, cBOManagement);
        return cBOManagementRepository.save(cBOManagement).getId();
    }

    public void update(final Long id, final CBOManagementDTO cBOManagementDTO) {
        final CBOManagement cBOManagement = cBOManagementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(cBOManagementDTO, cBOManagement);
        cBOManagementRepository.save(cBOManagement);
    }

    public void delete(final Long id) {
        cBOManagementRepository.deleteById(id);
    }

    private CBOManagementDTO mapToDTO(final CBOManagement cBOManagement,
            final CBOManagementDTO cBOManagementDTO) {
        cBOManagementDTO.setId(cBOManagement.getId());
        cBOManagementDTO.setFullName(cBOManagement.getFullName());
        cBOManagementDTO.setGender(cBOManagement.getGender());
        cBOManagementDTO.setCBOName(cBOManagement.getCBOName());
        cBOManagementDTO.setRelationToCBO(cBOManagement.getRelationToCBO());
        cBOManagementDTO.setYoB(cBOManagement.getYoB());
        cBOManagementDTO.setPhoneNumber(cBOManagement.getPhoneNumber());
        cBOManagementDTO.setEmailAddress(cBOManagement.getEmailAddress());
        cBOManagementDTO.setUserStatus(cBOManagement.getUserStatus());
        return cBOManagementDTO;
    }

    private CBOManagement mapToEntity(final CBOManagementDTO cBOManagementDTO,
            final CBOManagement cBOManagement) {
        cBOManagement.setFullName(cBOManagementDTO.getFullName());
        cBOManagement.setGender(cBOManagementDTO.getGender());
        cBOManagement.setCBOName(cBOManagementDTO.getCBOName());
        cBOManagement.setRelationToCBO(cBOManagementDTO.getRelationToCBO());
        cBOManagement.setYoB(cBOManagementDTO.getYoB());
        cBOManagement.setPhoneNumber(cBOManagementDTO.getPhoneNumber());
        cBOManagement.setEmailAddress(cBOManagementDTO.getEmailAddress());
        cBOManagement.setUserStatus(cBOManagementDTO.getUserStatus());
        return cBOManagement;
    }

    @Transactional
    public String getReferencedWarning(final Long id) {
        final CBOManagement cBOManagement = cBOManagementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        if (!cBOManagement.getManagementCBORegistrations().isEmpty()) {
            return WebUtils.getMessage("cBOManagement.cBORegistration.manyToMany.referenced", cBOManagement.getManagementCBORegistrations().iterator().next().getId());
        }
        return null;
    }

}
