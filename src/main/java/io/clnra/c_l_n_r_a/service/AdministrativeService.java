package io.clnra.c_l_n_r_a.service;

import io.clnra.c_l_n_r_a.domain.Administrative;
import io.clnra.c_l_n_r_a.domain.CBORegistration;
import io.clnra.c_l_n_r_a.model.AdministrativeDTO;
import io.clnra.c_l_n_r_a.repos.AdministrativeRepository;
import io.clnra.c_l_n_r_a.repos.CBORegistrationRepository;
import io.clnra.c_l_n_r_a.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class AdministrativeService {

    private final AdministrativeRepository administrativeRepository;
    private final CBORegistrationRepository cBORegistrationRepository;

    public AdministrativeService(final AdministrativeRepository administrativeRepository,
            final CBORegistrationRepository cBORegistrationRepository) {
        this.administrativeRepository = administrativeRepository;
        this.cBORegistrationRepository = cBORegistrationRepository;
    }

    public List<AdministrativeDTO> findAll() {
        final List<Administrative> administratives = administrativeRepository.findAll(Sort.by("id"));
        return administratives.stream()
                .map((administrative) -> mapToDTO(administrative, new AdministrativeDTO()))
                .collect(Collectors.toList());
    }

    public AdministrativeDTO get(final Long id) {
        return administrativeRepository.findById(id)
                .map(administrative -> mapToDTO(administrative, new AdministrativeDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final AdministrativeDTO administrativeDTO) {
        final Administrative administrative = new Administrative();
        mapToEntity(administrativeDTO, administrative);
        return administrativeRepository.save(administrative).getId();
    }

    public void update(final Long id, final AdministrativeDTO administrativeDTO) {
        final Administrative administrative = administrativeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(administrativeDTO, administrative);
        administrativeRepository.save(administrative);
    }

    public void delete(final Long id) {
        administrativeRepository.deleteById(id);
    }

    private AdministrativeDTO mapToDTO(final Administrative administrative,
            final AdministrativeDTO administrativeDTO) {
        administrativeDTO.setId(administrative.getId());
        administrativeDTO.setCountry(administrative.getCountry());
        administrativeDTO.setCountryCode(administrative.getCountryCode());
        administrativeDTO.setCountryCurrency(administrative.getCountryCurrency());
        administrativeDTO.setCurrencyCode(administrative.getCurrencyCode());
        administrativeDTO.setRegionName(administrative.getRegionName());
        administrativeDTO.setProvince(administrative.getProvince());
        administrativeDTO.setDistrict(administrative.getDistrict());
        administrativeDTO.setSubDistrict(administrative.getSubDistrict());
        administrativeDTO.setWard(administrative.getWard());
        administrativeDTO.setCBORegistration(administrative.getCBORegistration() == null ? null : administrative.getCBORegistration().getId());
        return administrativeDTO;
    }

    private Administrative mapToEntity(final AdministrativeDTO administrativeDTO,
            final Administrative administrative) {
        administrative.setCountry(administrativeDTO.getCountry());
        administrative.setCountryCode(administrativeDTO.getCountryCode());
        administrative.setCountryCurrency(administrativeDTO.getCountryCurrency());
        administrative.setCurrencyCode(administrativeDTO.getCurrencyCode());
        administrative.setRegionName(administrativeDTO.getRegionName());
        administrative.setProvince(administrativeDTO.getProvince());
        administrative.setDistrict(administrativeDTO.getDistrict());
        administrative.setSubDistrict(administrativeDTO.getSubDistrict());
        administrative.setWard(administrativeDTO.getWard());
        final CBORegistration cBORegistration = administrativeDTO.getCBORegistration() == null ? null : cBORegistrationRepository.findById(administrativeDTO.getCBORegistration())
                .orElseThrow(() -> new NotFoundException("cBORegistration not found"));
        administrative.setCBORegistration(cBORegistration);
        return administrative;
    }

}
