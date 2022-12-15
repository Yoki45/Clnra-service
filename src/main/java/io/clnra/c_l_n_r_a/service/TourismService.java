package io.clnra.c_l_n_r_a.service;

import io.clnra.c_l_n_r_a.domain.Tourism;
import io.clnra.c_l_n_r_a.model.TourismDTO;
import io.clnra.c_l_n_r_a.repos.TourismRepository;
import io.clnra.c_l_n_r_a.util.NotFoundException;
import io.clnra.c_l_n_r_a.util.WebUtils;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class TourismService {

    private final TourismRepository tourismRepository;

    public TourismService(final TourismRepository tourismRepository) {
        this.tourismRepository = tourismRepository;
    }

    public List<TourismDTO> findAll() {
        final List<Tourism> tourisms = tourismRepository.findAll(Sort.by("id"));
        return tourisms.stream()
                .map((tourism) -> mapToDTO(tourism, new TourismDTO()))
                .collect(Collectors.toList());
    }

    public TourismDTO get(final Long id) {
        return tourismRepository.findById(id)
                .map(tourism -> mapToDTO(tourism, new TourismDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final TourismDTO tourismDTO) {
        final Tourism tourism = new Tourism();
        mapToEntity(tourismDTO, tourism);
        return tourismRepository.save(tourism).getId();
    }

    public void update(final Long id, final TourismDTO tourismDTO) {
        final Tourism tourism = tourismRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(tourismDTO, tourism);
        tourismRepository.save(tourism);
    }

    public void delete(final Long id) {
        tourismRepository.deleteById(id);
    }

    private TourismDTO mapToDTO(final Tourism tourism, final TourismDTO tourismDTO) {
        tourismDTO.setId(tourism.getId());
        tourismDTO.setTourismLog(tourism.getTourismLog());
        return tourismDTO;
    }

    private Tourism mapToEntity(final TourismDTO tourismDTO, final Tourism tourism) {
        tourism.setTourismLog(tourismDTO.getTourismLog());
        return tourism;
    }

    @Transactional
    public String getReferencedWarning(final Long id) {
        final Tourism tourism = tourismRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        if (!tourism.getCBOTourismCBORegistrations().isEmpty()) {
            return WebUtils.getMessage("tourism.cBORegistration.manyToMany.referenced", tourism.getCBOTourismCBORegistrations().iterator().next().getId());
        }
        return null;
    }

}
