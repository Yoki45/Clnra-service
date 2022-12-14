package io.clnra.c_l_n_r_a.service;

import io.clnra.c_l_n_r_a.domain.AntiPoaching;
import io.clnra.c_l_n_r_a.model.AntiPoachingDTO;
import io.clnra.c_l_n_r_a.repos.AntiPoachingRepository;
import io.clnra.c_l_n_r_a.util.NotFoundException;
import io.clnra.c_l_n_r_a.util.WebUtils;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class AntiPoachingService {

    private final AntiPoachingRepository antiPoachingRepository;

    public AntiPoachingService(final AntiPoachingRepository antiPoachingRepository) {
        this.antiPoachingRepository = antiPoachingRepository;
    }

    public List<AntiPoachingDTO> findAll() {
        final List<AntiPoaching> antiPoachings = antiPoachingRepository.findAll(Sort.by("id"));
        return antiPoachings.stream()
                .map((antiPoaching) -> mapToDTO(antiPoaching, new AntiPoachingDTO()))
                .collect(Collectors.toList());
    }

    public AntiPoachingDTO get(final Long id) {
        return antiPoachingRepository.findById(id)
                .map(antiPoaching -> mapToDTO(antiPoaching, new AntiPoachingDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final AntiPoachingDTO antiPoachingDTO) {
        final AntiPoaching antiPoaching = new AntiPoaching();
        mapToEntity(antiPoachingDTO, antiPoaching);
        return antiPoachingRepository.save(antiPoaching).getId();
    }

    public void update(final Long id, final AntiPoachingDTO antiPoachingDTO) {
        final AntiPoaching antiPoaching = antiPoachingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(antiPoachingDTO, antiPoaching);
        antiPoachingRepository.save(antiPoaching);
    }

    public void delete(final Long id) {
        antiPoachingRepository.deleteById(id);
    }

    private AntiPoachingDTO mapToDTO(final AntiPoaching antiPoaching,
            final AntiPoachingDTO antiPoachingDTO) {
        antiPoachingDTO.setId(antiPoaching.getId());
        antiPoachingDTO.setAntiPoachingLog(antiPoaching.getAntiPoachingLog());
        antiPoachingDTO.setDate(antiPoaching.getDate());
        antiPoachingDTO.setSiteName(antiPoaching.getSiteName());
        antiPoachingDTO.setNumberOfGuards(antiPoaching.getNumberOfGuards());
        antiPoachingDTO.setNumberOfArms(antiPoaching.getNumberOfArms());
        antiPoachingDTO.setAPCoverageArea(antiPoaching.getAPCoverageArea());
        antiPoachingDTO.setMeasureOfAPEffort(antiPoaching.getMeasureOfAPEffort());
        antiPoachingDTO.setIndicatorIllegalActivities(antiPoaching.getIndicatorIllegalActivities());
        antiPoachingDTO.setEncounterRateIllegalActivities(antiPoaching.getEncounterRateIllegalActivities());
        antiPoachingDTO.setPrimaryIndicators(antiPoaching.getPrimaryIndicators());
        antiPoachingDTO.setSecondaryIndicators(antiPoaching.getSecondaryIndicators());
        antiPoachingDTO.setAPAction(antiPoaching.getAPAction());
        return antiPoachingDTO;
    }

    private AntiPoaching mapToEntity(final AntiPoachingDTO antiPoachingDTO,
            final AntiPoaching antiPoaching) {
        antiPoaching.setAntiPoachingLog(antiPoachingDTO.getAntiPoachingLog());
        antiPoaching.setDate(antiPoachingDTO.getDate());
        antiPoaching.setSiteName(antiPoachingDTO.getSiteName());
        antiPoaching.setNumberOfGuards(antiPoachingDTO.getNumberOfGuards());
        antiPoaching.setNumberOfArms(antiPoachingDTO.getNumberOfArms());
        antiPoaching.setAPCoverageArea(antiPoachingDTO.getAPCoverageArea());
        antiPoaching.setMeasureOfAPEffort(antiPoachingDTO.getMeasureOfAPEffort());
        antiPoaching.setIndicatorIllegalActivities(antiPoachingDTO.getIndicatorIllegalActivities());
        antiPoaching.setEncounterRateIllegalActivities(antiPoachingDTO.getEncounterRateIllegalActivities());
        antiPoaching.setPrimaryIndicators(antiPoachingDTO.getPrimaryIndicators());
        antiPoaching.setSecondaryIndicators(antiPoachingDTO.getSecondaryIndicators());
        antiPoaching.setAPAction(antiPoachingDTO.getAPAction());
        return antiPoaching;
    }

    @Transactional
    public String getReferencedWarning(final Long id) {
        final AntiPoaching antiPoaching = antiPoachingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        if (!antiPoaching.getAPCBORegistrations().isEmpty()) {
            return WebUtils.getMessage("antiPoaching.cBORegistration.manyToMany.referenced", antiPoaching.getAPCBORegistrations().iterator().next().getId());
        }
        return null;
    }

}
