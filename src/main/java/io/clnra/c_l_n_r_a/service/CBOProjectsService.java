package io.clnra.c_l_n_r_a.service;

import io.clnra.c_l_n_r_a.domain.CBOProjects;
import io.clnra.c_l_n_r_a.model.CBOProjectsDTO;
import io.clnra.c_l_n_r_a.repos.CBOProjectsRepository;
import io.clnra.c_l_n_r_a.util.NotFoundException;
import io.clnra.c_l_n_r_a.util.WebUtils;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CBOProjectsService {

    private final CBOProjectsRepository cBOProjectsRepository;

    public CBOProjectsService(final CBOProjectsRepository cBOProjectsRepository) {
        this.cBOProjectsRepository = cBOProjectsRepository;
    }

    public List<CBOProjectsDTO> findAll() {
        final List<CBOProjects> cBOProjectss = cBOProjectsRepository.findAll(Sort.by("id"));
        return cBOProjectss.stream()
                .map((cBOProjects) -> mapToDTO(cBOProjects, new CBOProjectsDTO()))
                .collect(Collectors.toList());
    }

    public CBOProjectsDTO get(final Long id) {
        return cBOProjectsRepository.findById(id)
                .map(cBOProjects -> mapToDTO(cBOProjects, new CBOProjectsDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final CBOProjectsDTO cBOProjectsDTO) {
        final CBOProjects cBOProjects = new CBOProjects();
        mapToEntity(cBOProjectsDTO, cBOProjects);
        return cBOProjectsRepository.save(cBOProjects).getId();
    }

    public void update(final Long id, final CBOProjectsDTO cBOProjectsDTO) {
        final CBOProjects cBOProjects = cBOProjectsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(cBOProjectsDTO, cBOProjects);
        cBOProjectsRepository.save(cBOProjects);
    }

    public void delete(final Long id) {
        cBOProjectsRepository.deleteById(id);
    }

    private CBOProjectsDTO mapToDTO(final CBOProjects cBOProjects,
            final CBOProjectsDTO cBOProjectsDTO) {
        cBOProjectsDTO.setId(cBOProjects.getId());
        cBOProjectsDTO.setProjectName(cBOProjects.getProjectName());
        cBOProjectsDTO.setProjectDuration(cBOProjects.getProjectDuration());
        cBOProjectsDTO.setProjectStartDate(cBOProjects.getProjectStartDate());
        cBOProjectsDTO.setProjectValue(cBOProjects.getProjectValue());
        cBOProjectsDTO.setProjectObjectives(cBOProjects.getProjectObjectives());
        cBOProjectsDTO.setFundingAgency(cBOProjects.getFundingAgency());
        cBOProjectsDTO.setContactPerson(cBOProjects.getContactPerson());
        return cBOProjectsDTO;
    }

    private CBOProjects mapToEntity(final CBOProjectsDTO cBOProjectsDTO,
            final CBOProjects cBOProjects) {
        cBOProjects.setProjectName(cBOProjectsDTO.getProjectName());
        cBOProjects.setProjectDuration(cBOProjectsDTO.getProjectDuration());
        cBOProjects.setProjectStartDate(cBOProjectsDTO.getProjectStartDate());
        cBOProjects.setProjectValue(cBOProjectsDTO.getProjectValue());
        cBOProjects.setProjectObjectives(cBOProjectsDTO.getProjectObjectives());
        cBOProjects.setFundingAgency(cBOProjectsDTO.getFundingAgency());
        cBOProjects.setContactPerson(cBOProjectsDTO.getContactPerson());
        return cBOProjects;
    }

    @Transactional
    public String getReferencedWarning(final Long id) {
        final CBOProjects cBOProjects = cBOProjectsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        if (!cBOProjects.getProjectCBORegistrations().isEmpty()) {
            return WebUtils.getMessage("cBOProjects.cBORegistration.manyToMany.referenced", cBOProjects.getProjectCBORegistrations().iterator().next().getId());
        }
        return null;
    }

}
