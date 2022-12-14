package io.clnra.c_l_n_r_a.service;

import io.clnra.c_l_n_r_a.domain.Hunting;
import io.clnra.c_l_n_r_a.model.HuntingDTO;
import io.clnra.c_l_n_r_a.repos.HuntingRepository;
import io.clnra.c_l_n_r_a.util.NotFoundException;
import io.clnra.c_l_n_r_a.util.WebUtils;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class HuntingService {

    private final HuntingRepository huntingRepository;

    public HuntingService(final HuntingRepository huntingRepository) {
        this.huntingRepository = huntingRepository;
    }

    public List<HuntingDTO> findAll() {
        final List<Hunting> huntings = huntingRepository.findAll(Sort.by("id"));
        return huntings.stream()
                .map((hunting) -> mapToDTO(hunting, new HuntingDTO()))
                .collect(Collectors.toList());
    }

    public HuntingDTO get(final Long id) {
        return huntingRepository.findById(id)
                .map(hunting -> mapToDTO(hunting, new HuntingDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final HuntingDTO huntingDTO) {
        final Hunting hunting = new Hunting();
        mapToEntity(huntingDTO, hunting);
        return huntingRepository.save(hunting).getId();
    }

    public void update(final Long id, final HuntingDTO huntingDTO) {
        final Hunting hunting = huntingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(huntingDTO, hunting);
        huntingRepository.save(hunting);
    }

    public void delete(final Long id) {
        huntingRepository.deleteById(id);
    }

    private HuntingDTO mapToDTO(final Hunting hunting, final HuntingDTO huntingDTO) {
        huntingDTO.setId(hunting.getId());
        huntingDTO.setHuntingLog(hunting.getHuntingLog());
        huntingDTO.setAnimalName(hunting.getAnimalName());
        huntingDTO.setYear(hunting.getYear());
        huntingDTO.setAllocatedQuota(hunting.getAllocatedQuota());
        huntingDTO.setTrophyFee(hunting.getTrophyFee());
        huntingDTO.setNumberHunted(hunting.getNumberHunted());
        huntingDTO.setFixedQuota(hunting.getFixedQuota());
        huntingDTO.setOptionalQuota(hunting.getOptionalQuota());
        return huntingDTO;
    }

    private Hunting mapToEntity(final HuntingDTO huntingDTO, final Hunting hunting) {
        hunting.setHuntingLog(huntingDTO.getHuntingLog());
        hunting.setAnimalName(huntingDTO.getAnimalName());
        hunting.setYear(huntingDTO.getYear());
        hunting.setAllocatedQuota(huntingDTO.getAllocatedQuota());
        hunting.setTrophyFee(huntingDTO.getTrophyFee());
        hunting.setNumberHunted(huntingDTO.getNumberHunted());
        hunting.setFixedQuota(huntingDTO.getFixedQuota());
        hunting.setOptionalQuota(huntingDTO.getOptionalQuota());
        return hunting;
    }

    public boolean huntingLogExists(final UUID huntingLog) {
        return huntingRepository.existsByHuntingLog(huntingLog);
    }

    @Transactional
    public String getReferencedWarning(final Long id) {
        final Hunting hunting = huntingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        if (!hunting.getCBOHuntingCBORegistrations().isEmpty()) {
            return WebUtils.getMessage("hunting.cBORegistration.manyToMany.referenced", hunting.getCBOHuntingCBORegistrations().iterator().next().getId());
        }
        return null;
    }

}
