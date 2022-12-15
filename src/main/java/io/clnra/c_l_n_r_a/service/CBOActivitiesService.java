package io.clnra.c_l_n_r_a.service;

import io.clnra.c_l_n_r_a.domain.CBOActivities;
import io.clnra.c_l_n_r_a.model.CBOActivitiesDTO;
import io.clnra.c_l_n_r_a.repos.CBOActivitiesRepository;
import io.clnra.c_l_n_r_a.util.NotFoundException;
import io.clnra.c_l_n_r_a.util.WebUtils;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CBOActivitiesService {

    private final CBOActivitiesRepository cBOActivitiesRepository;

    public CBOActivitiesService(final CBOActivitiesRepository cBOActivitiesRepository) {
        this.cBOActivitiesRepository = cBOActivitiesRepository;
    }

    public List<CBOActivitiesDTO> findAll() {
        final List<CBOActivities> cBOActivitiess = cBOActivitiesRepository.findAll(Sort.by("id"));
        return cBOActivitiess.stream()
                .map((cBOActivities) -> mapToDTO(cBOActivities, new CBOActivitiesDTO()))
                .collect(Collectors.toList());
    }

    public CBOActivitiesDTO get(final Long id) {
        return cBOActivitiesRepository.findById(id)
                .map(cBOActivities -> mapToDTO(cBOActivities, new CBOActivitiesDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final CBOActivitiesDTO cBOActivitiesDTO) {
        final CBOActivities cBOActivities = new CBOActivities();
        mapToEntity(cBOActivitiesDTO, cBOActivities);
        return cBOActivitiesRepository.save(cBOActivities).getId();
    }

    public void update(final Long id, final CBOActivitiesDTO cBOActivitiesDTO) {
        final CBOActivities cBOActivities = cBOActivitiesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(cBOActivitiesDTO, cBOActivities);
        cBOActivitiesRepository.save(cBOActivities);
    }

    public void delete(final Long id) {
        cBOActivitiesRepository.deleteById(id);
    }

    private CBOActivitiesDTO mapToDTO(final CBOActivities cBOActivities,
            final CBOActivitiesDTO cBOActivitiesDTO) {
        cBOActivitiesDTO.setId(cBOActivities.getId());
        cBOActivitiesDTO.setActivityName(cBOActivities.getActivityName());
        return cBOActivitiesDTO;
    }

    private CBOActivities mapToEntity(final CBOActivitiesDTO cBOActivitiesDTO,
            final CBOActivities cBOActivities) {
        cBOActivities.setActivityName(cBOActivitiesDTO.getActivityName());
        return cBOActivities;
    }

    @Transactional
    public String getReferencedWarning(final Long id) {
        final CBOActivities cBOActivities = cBOActivitiesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        if (!cBOActivities.getActivitiesCBORegistrations().isEmpty()) {
            return WebUtils.getMessage("cBOActivities.cBORegistration.manyToMany.referenced", cBOActivities.getActivitiesCBORegistrations().iterator().next().getId());
        }
        return null;
    }

}
