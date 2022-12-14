package io.clnra.c_l_n_r_a.service;

import io.clnra.c_l_n_r_a.domain.Patrols;
import io.clnra.c_l_n_r_a.model.PatrolsDTO;
import io.clnra.c_l_n_r_a.repos.PatrolsRepository;
import io.clnra.c_l_n_r_a.util.NotFoundException;
import io.clnra.c_l_n_r_a.util.WebUtils;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class PatrolsService {

    private final PatrolsRepository patrolsRepository;

    public PatrolsService(final PatrolsRepository patrolsRepository) {
        this.patrolsRepository = patrolsRepository;
    }

    public List<PatrolsDTO> findAll() {
        final List<Patrols> patrolss = patrolsRepository.findAll(Sort.by("id"));
        return patrolss.stream()
                .map((patrols) -> mapToDTO(patrols, new PatrolsDTO()))
                .collect(Collectors.toList());
    }

    public PatrolsDTO get(final Long id) {
        return patrolsRepository.findById(id)
                .map(patrols -> mapToDTO(patrols, new PatrolsDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final PatrolsDTO patrolsDTO) {
        final Patrols patrols = new Patrols();
        mapToEntity(patrolsDTO, patrols);
        return patrolsRepository.save(patrols).getId();
    }

    public void update(final Long id, final PatrolsDTO patrolsDTO) {
        final Patrols patrols = patrolsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(patrolsDTO, patrols);
        patrolsRepository.save(patrols);
    }

    public void delete(final Long id) {
        patrolsRepository.deleteById(id);
    }

    private PatrolsDTO mapToDTO(final Patrols patrols, final PatrolsDTO patrolsDTO) {
        patrolsDTO.setId(patrols.getId());
        patrolsDTO.setPatrolsLog(patrols.getPatrolsLog());
        return patrolsDTO;
    }

    private Patrols mapToEntity(final PatrolsDTO patrolsDTO, final Patrols patrols) {
        patrols.setPatrolsLog(patrolsDTO.getPatrolsLog());
        return patrols;
    }

    @Transactional
    public String getReferencedWarning(final Long id) {
        final Patrols patrols = patrolsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        if (!patrols.getCBOPatrolsCBORegistrations().isEmpty()) {
            return WebUtils.getMessage("patrols.cBORegistration.manyToMany.referenced", patrols.getCBOPatrolsCBORegistrations().iterator().next().getId());
        }
        return null;
    }

}
