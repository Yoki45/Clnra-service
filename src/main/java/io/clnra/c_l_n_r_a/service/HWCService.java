package io.clnra.c_l_n_r_a.service;

import io.clnra.c_l_n_r_a.domain.HWC;
import io.clnra.c_l_n_r_a.model.HWCDTO;
import io.clnra.c_l_n_r_a.repos.HWCRepository;
import io.clnra.c_l_n_r_a.util.NotFoundException;
import io.clnra.c_l_n_r_a.util.WebUtils;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class HWCService {

    private final HWCRepository hWCRepository;

    public HWCService(final HWCRepository hWCRepository) {
        this.hWCRepository = hWCRepository;
    }

    public List<HWCDTO> findAll() {
        final List<HWC> hWCs = hWCRepository.findAll(Sort.by("id"));
        return hWCs.stream()
                .map((hWC) -> mapToDTO(hWC, new HWCDTO()))
                .collect(Collectors.toList());
    }

    public HWCDTO get(final Long id) {
        return hWCRepository.findById(id)
                .map(hWC -> mapToDTO(hWC, new HWCDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final HWCDTO hWCDTO) {
        final HWC hWC = new HWC();
        mapToEntity(hWCDTO, hWC);
        return hWCRepository.save(hWC).getId();
    }

    public void update(final Long id, final HWCDTO hWCDTO) {
        final HWC hWC = hWCRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(hWCDTO, hWC);
        hWCRepository.save(hWC);
    }

    public void delete(final Long id) {
        hWCRepository.deleteById(id);
    }

    private HWCDTO mapToDTO(final HWC hWC, final HWCDTO hWCDTO) {
        hWCDTO.setId(hWC.getId());
        hWCDTO.setHWCLog(hWC.getHWCLog());
        hWCDTO.setDate(hWC.getDate());
        hWCDTO.setProblemSpecies(hWC.getProblemSpecies());
        hWCDTO.setPlaceIncident(hWC.getPlaceIncident());
        hWCDTO.setHWCType(hWC.getHWCType());
        hWCDTO.setLivestockKilled(hWC.getLivestockKilled());
        hWCDTO.setValueOfLivestock(hWC.getValueOfLivestock());
        hWCDTO.setLivestockImage(hWC.getLivestockImage());
        hWCDTO.setCropsDamaged(hWC.getCropsDamaged());
        hWCDTO.setValueOfCrops(hWC.getValueOfCrops());
        hWCDTO.setInfrastructureValue(hWC.getInfrastructureValue());
        hWCDTO.setNumberOfMales(hWC.getNumberOfMales());
        hWCDTO.setNumberOfFemale(hWC.getNumberOfFemale());
        hWCDTO.setDamageConfirmed(hWC.getDamageConfirmed());
        hWCDTO.setReported(hWC.getReported());
        hWCDTO.setRespondedBy(hWC.getRespondedBy());
        hWCDTO.setActionTaken(hWC.getActionTaken());
        hWCDTO.setComplainantName(hWC.getComplainantName());
        hWCDTO.setAddressComplainant(hWC.getAddressComplainant());
        hWCDTO.setInvestigatedBy(hWC.getInvestigatedBy());
        hWCDTO.setComments(hWC.getComments());
        return hWCDTO;
    }

    private HWC mapToEntity(final HWCDTO hWCDTO, final HWC hWC) {
        hWC.setHWCLog(hWCDTO.getHWCLog());
        hWC.setDate(hWCDTO.getDate());
        hWC.setProblemSpecies(hWCDTO.getProblemSpecies());
        hWC.setPlaceIncident(hWCDTO.getPlaceIncident());
        hWC.setHWCType(hWCDTO.getHWCType());
        hWC.setLivestockKilled(hWCDTO.getLivestockKilled());
        hWC.setValueOfLivestock(hWCDTO.getValueOfLivestock());
        hWC.setLivestockImage(hWCDTO.getLivestockImage());
        hWC.setCropsDamaged(hWCDTO.getCropsDamaged());
        hWC.setValueOfCrops(hWCDTO.getValueOfCrops());
        hWC.setInfrastructureValue(hWCDTO.getInfrastructureValue());
        hWC.setNumberOfMales(hWCDTO.getNumberOfMales());
        hWC.setNumberOfFemale(hWCDTO.getNumberOfFemale());
        hWC.setDamageConfirmed(hWCDTO.getDamageConfirmed());
        hWC.setReported(hWCDTO.getReported());
        hWC.setRespondedBy(hWCDTO.getRespondedBy());
        hWC.setActionTaken(hWCDTO.getActionTaken());
        hWC.setComplainantName(hWCDTO.getComplainantName());
        hWC.setAddressComplainant(hWCDTO.getAddressComplainant());
        hWC.setInvestigatedBy(hWCDTO.getInvestigatedBy());
        hWC.setComments(hWCDTO.getComments());
        return hWC;
    }

    @Transactional
    public String getReferencedWarning(final Long id) {
        final HWC hWC = hWCRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        if (!hWC.getHumanConflictCBORegistrations().isEmpty()) {
            return WebUtils.getMessage("hWC.cBORegistration.manyToMany.referenced", hWC.getHumanConflictCBORegistrations().iterator().next().getId());
        }
        return null;
    }

}
