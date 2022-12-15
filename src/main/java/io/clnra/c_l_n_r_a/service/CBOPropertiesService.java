package io.clnra.c_l_n_r_a.service;

import io.clnra.c_l_n_r_a.domain.CBOProperties;
import io.clnra.c_l_n_r_a.model.CBOPropertiesDTO;
import io.clnra.c_l_n_r_a.repos.CBOPropertiesRepository;
import io.clnra.c_l_n_r_a.util.NotFoundException;
import io.clnra.c_l_n_r_a.util.WebUtils;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CBOPropertiesService {

    private final CBOPropertiesRepository cBOPropertiesRepository;

    public CBOPropertiesService(final CBOPropertiesRepository cBOPropertiesRepository) {
        this.cBOPropertiesRepository = cBOPropertiesRepository;
    }

    public List<CBOPropertiesDTO> findAll() {
        final List<CBOProperties> cBOPropertiess = cBOPropertiesRepository.findAll(Sort.by("id"));
        return cBOPropertiess.stream()
                .map((cBOProperties) -> mapToDTO(cBOProperties, new CBOPropertiesDTO()))
                .collect(Collectors.toList());
    }

    public CBOPropertiesDTO get(final Long id) {
        return cBOPropertiesRepository.findById(id)
                .map(cBOProperties -> mapToDTO(cBOProperties, new CBOPropertiesDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final CBOPropertiesDTO cBOPropertiesDTO) {
        final CBOProperties cBOProperties = new CBOProperties();
        mapToEntity(cBOPropertiesDTO, cBOProperties);
        return cBOPropertiesRepository.save(cBOProperties).getId();
    }

    public void update(final Long id, final CBOPropertiesDTO cBOPropertiesDTO) {
        final CBOProperties cBOProperties = cBOPropertiesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(cBOPropertiesDTO, cBOProperties);
        cBOPropertiesRepository.save(cBOProperties);
    }

    public void delete(final Long id) {
        cBOPropertiesRepository.deleteById(id);
    }

    private CBOPropertiesDTO mapToDTO(final CBOProperties cBOProperties,
            final CBOPropertiesDTO cBOPropertiesDTO) {
        cBOPropertiesDTO.setId(cBOProperties.getId());
        cBOPropertiesDTO.setPropertyName(cBOProperties.getPropertyName());
        cBOPropertiesDTO.setPropertyType(cBOProperties.getPropertyType());
        cBOPropertiesDTO.setLocation(cBOProperties.getLocation());
        cBOPropertiesDTO.setPropertyValue(cBOProperties.getPropertyValue());
        cBOPropertiesDTO.setRegistrationTag(cBOProperties.getRegistrationTag());
        return cBOPropertiesDTO;
    }

    private CBOProperties mapToEntity(final CBOPropertiesDTO cBOPropertiesDTO,
            final CBOProperties cBOProperties) {
        cBOProperties.setPropertyName(cBOPropertiesDTO.getPropertyName());
        cBOProperties.setPropertyType(cBOPropertiesDTO.getPropertyType());
        cBOProperties.setLocation(cBOPropertiesDTO.getLocation());
        cBOProperties.setPropertyValue(cBOPropertiesDTO.getPropertyValue());
        cBOProperties.setRegistrationTag(cBOPropertiesDTO.getRegistrationTag());
        return cBOProperties;
    }

    public boolean registrationTagExists(final String registrationTag) {
        return cBOPropertiesRepository.existsByRegistrationTagIgnoreCase(registrationTag);
    }

    @Transactional
    public String getReferencedWarning(final Long id) {
        final CBOProperties cBOProperties = cBOPropertiesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        if (!cBOProperties.getPropertiesCBORegistrations().isEmpty()) {
            return WebUtils.getMessage("cBOProperties.cBORegistration.manyToMany.referenced", cBOProperties.getPropertiesCBORegistrations().iterator().next().getId());
        }
        return null;
    }

}
