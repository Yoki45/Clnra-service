package io.clnra.c_l_n_r_a.service;

import io.clnra.c_l_n_r_a.domain.AntiPoaching;
import io.clnra.c_l_n_r_a.domain.CBOActivities;
import io.clnra.c_l_n_r_a.domain.CBOManagement;
import io.clnra.c_l_n_r_a.domain.CBOProjects;
import io.clnra.c_l_n_r_a.domain.CBOProperties;
import io.clnra.c_l_n_r_a.domain.CBORegistration;
import io.clnra.c_l_n_r_a.domain.CSR;
import io.clnra.c_l_n_r_a.domain.GameCount;
import io.clnra.c_l_n_r_a.domain.HWC;
import io.clnra.c_l_n_r_a.domain.Hunting;
import io.clnra.c_l_n_r_a.domain.Partners;
import io.clnra.c_l_n_r_a.domain.Patrols;
import io.clnra.c_l_n_r_a.domain.Tourism;
import io.clnra.c_l_n_r_a.model.CBORegistrationDTO;
import io.clnra.c_l_n_r_a.repos.AntiPoachingRepository;
import io.clnra.c_l_n_r_a.repos.CBOActivitiesRepository;
import io.clnra.c_l_n_r_a.repos.CBOManagementRepository;
import io.clnra.c_l_n_r_a.repos.CBOProjectsRepository;
import io.clnra.c_l_n_r_a.repos.CBOPropertiesRepository;
import io.clnra.c_l_n_r_a.repos.CBORegistrationRepository;
import io.clnra.c_l_n_r_a.repos.CSRRepository;
import io.clnra.c_l_n_r_a.repos.GameCountRepository;
import io.clnra.c_l_n_r_a.repos.HWCRepository;
import io.clnra.c_l_n_r_a.repos.HuntingRepository;
import io.clnra.c_l_n_r_a.repos.PartnersRepository;
import io.clnra.c_l_n_r_a.repos.PatrolsRepository;
import io.clnra.c_l_n_r_a.repos.TourismRepository;
import io.clnra.c_l_n_r_a.util.NotFoundException;
import io.clnra.c_l_n_r_a.util.WebUtils;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Transactional
@Service
public class CBORegistrationService {

    private final CBORegistrationRepository cBORegistrationRepository;
    private final CBOManagementRepository cBOManagementRepository;
    private final PartnersRepository partnersRepository;
    private final CBOPropertiesRepository cBOPropertiesRepository;
    private final CBOProjectsRepository cBOProjectsRepository;
    private final CBOActivitiesRepository cBOActivitiesRepository;
    private final HuntingRepository huntingRepository;
    private final GameCountRepository gameCountRepository;
    private final TourismRepository tourismRepository;
    private final HWCRepository hWCRepository;
    private final AntiPoachingRepository antiPoachingRepository;
    private final CSRRepository cSRRepository;
    private final PatrolsRepository patrolsRepository;

    public CBORegistrationService(final CBORegistrationRepository cBORegistrationRepository,
            final CBOManagementRepository cBOManagementRepository,
            final PartnersRepository partnersRepository,
            final CBOPropertiesRepository cBOPropertiesRepository,
            final CBOProjectsRepository cBOProjectsRepository,
            final CBOActivitiesRepository cBOActivitiesRepository,
            final HuntingRepository huntingRepository,
            final GameCountRepository gameCountRepository,
            final TourismRepository tourismRepository, final HWCRepository hWCRepository,
            final AntiPoachingRepository antiPoachingRepository, final CSRRepository cSRRepository,
            final PatrolsRepository patrolsRepository) {
        this.cBORegistrationRepository = cBORegistrationRepository;
        this.cBOManagementRepository = cBOManagementRepository;
        this.partnersRepository = partnersRepository;
        this.cBOPropertiesRepository = cBOPropertiesRepository;
        this.cBOProjectsRepository = cBOProjectsRepository;
        this.cBOActivitiesRepository = cBOActivitiesRepository;
        this.huntingRepository = huntingRepository;
        this.gameCountRepository = gameCountRepository;
        this.tourismRepository = tourismRepository;
        this.hWCRepository = hWCRepository;
        this.antiPoachingRepository = antiPoachingRepository;
        this.cSRRepository = cSRRepository;
        this.patrolsRepository = patrolsRepository;
    }

    public List<CBORegistrationDTO> findAll() {
        final List<CBORegistration> cBORegistrations = cBORegistrationRepository.findAll(Sort.by("id"));
        return cBORegistrations.stream()
                .map((cBORegistration) -> mapToDTO(cBORegistration, new CBORegistrationDTO()))
                .collect(Collectors.toList());
    }

    public CBORegistrationDTO get(final Long id) {
        return cBORegistrationRepository.findById(id)
                .map(cBORegistration -> mapToDTO(cBORegistration, new CBORegistrationDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final CBORegistrationDTO cBORegistrationDTO) {
        final CBORegistration cBORegistration = new CBORegistration();
        mapToEntity(cBORegistrationDTO, cBORegistration);
        return cBORegistrationRepository.save(cBORegistration).getId();
    }

    public void update(final Long id, final CBORegistrationDTO cBORegistrationDTO) {
        final CBORegistration cBORegistration = cBORegistrationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(cBORegistrationDTO, cBORegistration);
        cBORegistrationRepository.save(cBORegistration);
    }

    public void delete(final Long id) {
        cBORegistrationRepository.deleteById(id);
    }

    private CBORegistrationDTO mapToDTO(final CBORegistration cBORegistration,
            final CBORegistrationDTO cBORegistrationDTO) {
        cBORegistrationDTO.setId(cBORegistration.getId());
        cBORegistrationDTO.setCBOName(cBORegistration.getCBOName());
        cBORegistrationDTO.setCBOID(cBORegistration.getCBOID());
        cBORegistrationDTO.setCountry(cBORegistration.getCountry());
        cBORegistrationDTO.setCBORegistered(cBORegistration.getCBORegistered());
        cBORegistrationDTO.setRegNumber(cBORegistration.getRegNumber());
        cBORegistrationDTO.setApproxPopulation(cBORegistration.getApproxPopulation());
        cBORegistrationDTO.setCBOAddress(cBORegistration.getCBOAddress());
        cBORegistrationDTO.setGPSLatitude(cBORegistration.getGPSLatitude());
        cBORegistrationDTO.setGPSLongitude(cBORegistration.getGPSLongitude());
        cBORegistrationDTO.setCBOContact(cBORegistration.getCBOContact());
        cBORegistrationDTO.setCBOConstitution(cBORegistration.getCBOConstitution());
        cBORegistrationDTO.setManagements(cBORegistration.getManagementCBOManagements() == null ? null : cBORegistration.getManagementCBOManagements().stream()
                .map(cBOManagement -> cBOManagement.getId())
                .collect(Collectors.toList()));
        cBORegistrationDTO.setCBOPartnerss(cBORegistration.getCBOPartnersPartnerss() == null ? null : cBORegistration.getCBOPartnersPartnerss().stream()
                .map(partners -> partners.getId())
                .collect(Collectors.toList()));
        cBORegistrationDTO.setPropertiess(cBORegistration.getPropertiesCBOPropertiess() == null ? null : cBORegistration.getPropertiesCBOPropertiess().stream()
                .map(cBOProperties -> cBOProperties.getId())
                .collect(Collectors.toList()));
        cBORegistrationDTO.setProjects(cBORegistration.getProjectCBOProjectss() == null ? null : cBORegistration.getProjectCBOProjectss().stream()
                .map(cBOProjects -> cBOProjects.getId())
                .collect(Collectors.toList()));
        cBORegistrationDTO.setActivitiess(cBORegistration.getActivitiesCBOActivitiess() == null ? null : cBORegistration.getActivitiesCBOActivitiess().stream()
                .map(cBOActivities -> cBOActivities.getId())
                .collect(Collectors.toList()));
        cBORegistrationDTO.setCBOHuntings(cBORegistration.getCBOHuntingHuntings() == null ? null : cBORegistration.getCBOHuntingHuntings().stream()
                .map(hunting -> hunting.getId())
                .collect(Collectors.toList()));
        cBORegistrationDTO.setWildlifePPs(cBORegistration.getWildlifePPGameCounts() == null ? null : cBORegistration.getWildlifePPGameCounts().stream()
                .map(gameCount -> gameCount.getId())
                .collect(Collectors.toList()));
        cBORegistrationDTO.setCBOTourisms(cBORegistration.getCBOTourismTourisms() == null ? null : cBORegistration.getCBOTourismTourisms().stream()
                .map(tourism -> tourism.getId())
                .collect(Collectors.toList()));
        cBORegistrationDTO.setHumanConflicts(cBORegistration.getHumanConflictHWCs() == null ? null : cBORegistration.getHumanConflictHWCs().stream()
                .map(hWC -> hWC.getId())
                .collect(Collectors.toList()));
        cBORegistrationDTO.setAPs(cBORegistration.getAPAntiPoachings() == null ? null : cBORegistration.getAPAntiPoachings().stream()
                .map(antiPoaching -> antiPoaching.getId())
                .collect(Collectors.toList()));
        cBORegistrationDTO.setCSRCBOs(cBORegistration.getCSRCBOCSRs() == null ? null : cBORegistration.getCSRCBOCSRs().stream()
                .map(cSR -> cSR.getId())
                .collect(Collectors.toList()));
        cBORegistrationDTO.setCBOPatrolss(cBORegistration.getCBOPatrolsPatrolss() == null ? null : cBORegistration.getCBOPatrolsPatrolss().stream()
                .map(patrols -> patrols.getId())
                .collect(Collectors.toList()));
        return cBORegistrationDTO;
    }

    private CBORegistration mapToEntity(final CBORegistrationDTO cBORegistrationDTO,
            final CBORegistration cBORegistration) {
        cBORegistration.setCBOName(cBORegistrationDTO.getCBOName());
        cBORegistration.setCBOID(cBORegistrationDTO.getCBOID());
        cBORegistration.setCountry(cBORegistrationDTO.getCountry());
        cBORegistration.setCBORegistered(cBORegistrationDTO.getCBORegistered());
        cBORegistration.setRegNumber(cBORegistrationDTO.getRegNumber());
        cBORegistration.setApproxPopulation(cBORegistrationDTO.getApproxPopulation());
        cBORegistration.setCBOAddress(cBORegistrationDTO.getCBOAddress());
        cBORegistration.setGPSLatitude(cBORegistrationDTO.getGPSLatitude());
        cBORegistration.setGPSLongitude(cBORegistrationDTO.getGPSLongitude());
        cBORegistration.setCBOContact(cBORegistrationDTO.getCBOContact());
        cBORegistration.setCBOConstitution(cBORegistrationDTO.getCBOConstitution());
        final List<CBOManagement> managements = cBOManagementRepository.findAllById(
                cBORegistrationDTO.getManagements() == null ? Collections.emptyList() : cBORegistrationDTO.getManagements());
        if (managements.size() != (cBORegistrationDTO.getManagements() == null ? 0 : cBORegistrationDTO.getManagements().size())) {
            throw new NotFoundException("one of managements not found");
        }
        cBORegistration.setManagementCBOManagements(managements.stream().collect(Collectors.toSet()));
        final List<Partners> cBOPartnerss = partnersRepository.findAllById(
                cBORegistrationDTO.getCBOPartnerss() == null ? Collections.emptyList() : cBORegistrationDTO.getCBOPartnerss());
        if (cBOPartnerss.size() != (cBORegistrationDTO.getCBOPartnerss() == null ? 0 : cBORegistrationDTO.getCBOPartnerss().size())) {
            throw new NotFoundException("one of cBOPartnerss not found");
        }
        cBORegistration.setCBOPartnersPartnerss(cBOPartnerss.stream().collect(Collectors.toSet()));
        final List<CBOProperties> propertiess = cBOPropertiesRepository.findAllById(
                cBORegistrationDTO.getPropertiess() == null ? Collections.emptyList() : cBORegistrationDTO.getPropertiess());
        if (propertiess.size() != (cBORegistrationDTO.getPropertiess() == null ? 0 : cBORegistrationDTO.getPropertiess().size())) {
            throw new NotFoundException("one of propertiess not found");
        }
        cBORegistration.setPropertiesCBOPropertiess(propertiess.stream().collect(Collectors.toSet()));
        final List<CBOProjects> projects = cBOProjectsRepository.findAllById(
                cBORegistrationDTO.getProjects() == null ? Collections.emptyList() : cBORegistrationDTO.getProjects());
        if (projects.size() != (cBORegistrationDTO.getProjects() == null ? 0 : cBORegistrationDTO.getProjects().size())) {
            throw new NotFoundException("one of projects not found");
        }
        cBORegistration.setProjectCBOProjectss(projects.stream().collect(Collectors.toSet()));
        final List<CBOActivities> activitiess = cBOActivitiesRepository.findAllById(
                cBORegistrationDTO.getActivitiess() == null ? Collections.emptyList() : cBORegistrationDTO.getActivitiess());
        if (activitiess.size() != (cBORegistrationDTO.getActivitiess() == null ? 0 : cBORegistrationDTO.getActivitiess().size())) {
            throw new NotFoundException("one of activitiess not found");
        }
        cBORegistration.setActivitiesCBOActivitiess(activitiess.stream().collect(Collectors.toSet()));
        final List<Hunting> cBOHuntings = huntingRepository.findAllById(
                cBORegistrationDTO.getCBOHuntings() == null ? Collections.emptyList() : cBORegistrationDTO.getCBOHuntings());
        if (cBOHuntings.size() != (cBORegistrationDTO.getCBOHuntings() == null ? 0 : cBORegistrationDTO.getCBOHuntings().size())) {
            throw new NotFoundException("one of cBOHuntings not found");
        }
        cBORegistration.setCBOHuntingHuntings(cBOHuntings.stream().collect(Collectors.toSet()));
        final List<GameCount> wildlifePPs = gameCountRepository.findAllById(
                cBORegistrationDTO.getWildlifePPs() == null ? Collections.emptyList() : cBORegistrationDTO.getWildlifePPs());
        if (wildlifePPs.size() != (cBORegistrationDTO.getWildlifePPs() == null ? 0 : cBORegistrationDTO.getWildlifePPs().size())) {
            throw new NotFoundException("one of wildlifePPs not found");
        }
        cBORegistration.setWildlifePPGameCounts(wildlifePPs.stream().collect(Collectors.toSet()));
        final List<Tourism> cBOTourisms = tourismRepository.findAllById(
                cBORegistrationDTO.getCBOTourisms() == null ? Collections.emptyList() : cBORegistrationDTO.getCBOTourisms());
        if (cBOTourisms.size() != (cBORegistrationDTO.getCBOTourisms() == null ? 0 : cBORegistrationDTO.getCBOTourisms().size())) {
            throw new NotFoundException("one of cBOTourisms not found");
        }
        cBORegistration.setCBOTourismTourisms(cBOTourisms.stream().collect(Collectors.toSet()));
        final List<HWC> humanConflicts = hWCRepository.findAllById(
                cBORegistrationDTO.getHumanConflicts() == null ? Collections.emptyList() : cBORegistrationDTO.getHumanConflicts());
        if (humanConflicts.size() != (cBORegistrationDTO.getHumanConflicts() == null ? 0 : cBORegistrationDTO.getHumanConflicts().size())) {
            throw new NotFoundException("one of humanConflicts not found");
        }
        cBORegistration.setHumanConflictHWCs(humanConflicts.stream().collect(Collectors.toSet()));
        final List<AntiPoaching> aPs = antiPoachingRepository.findAllById(
                cBORegistrationDTO.getAPs() == null ? Collections.emptyList() : cBORegistrationDTO.getAPs());
        if (aPs.size() != (cBORegistrationDTO.getAPs() == null ? 0 : cBORegistrationDTO.getAPs().size())) {
            throw new NotFoundException("one of aPs not found");
        }
        cBORegistration.setAPAntiPoachings(aPs.stream().collect(Collectors.toSet()));
        final List<CSR> cSRCBOs = cSRRepository.findAllById(
                cBORegistrationDTO.getCSRCBOs() == null ? Collections.emptyList() : cBORegistrationDTO.getCSRCBOs());
        if (cSRCBOs.size() != (cBORegistrationDTO.getCSRCBOs() == null ? 0 : cBORegistrationDTO.getCSRCBOs().size())) {
            throw new NotFoundException("one of cSRCBOs not found");
        }
        cBORegistration.setCSRCBOCSRs(cSRCBOs.stream().collect(Collectors.toSet()));
        final List<Patrols> cBOPatrolss = patrolsRepository.findAllById(
                cBORegistrationDTO.getCBOPatrolss() == null ? Collections.emptyList() : cBORegistrationDTO.getCBOPatrolss());
        if (cBOPatrolss.size() != (cBORegistrationDTO.getCBOPatrolss() == null ? 0 : cBORegistrationDTO.getCBOPatrolss().size())) {
            throw new NotFoundException("one of cBOPatrolss not found");
        }
        cBORegistration.setCBOPatrolsPatrolss(cBOPatrolss.stream().collect(Collectors.toSet()));
        return cBORegistration;
    }

    public boolean cBOIDExists(final Integer cBOID) {
        return cBORegistrationRepository.existsByCBOID(cBOID);
    }

    @Transactional
    public String getReferencedWarning(final Long id) {
        final CBORegistration cBORegistration = cBORegistrationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        if (!cBORegistration.getCBORegistrationAdministratives().isEmpty()) {
            return WebUtils.getMessage("cBORegistration.administrative.manyToOne.referenced", cBORegistration.getCBORegistrationAdministratives().iterator().next().getId());
        }
        return null;
    }

}
