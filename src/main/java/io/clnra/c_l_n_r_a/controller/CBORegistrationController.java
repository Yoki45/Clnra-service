package io.clnra.c_l_n_r_a.controller;

import io.clnra.c_l_n_r_a.domain.AntiPoaching;
import io.clnra.c_l_n_r_a.domain.CBOActivities;
import io.clnra.c_l_n_r_a.domain.CBOManagement;
import io.clnra.c_l_n_r_a.domain.CBOProjects;
import io.clnra.c_l_n_r_a.domain.CBOProperties;
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
import io.clnra.c_l_n_r_a.repos.CSRRepository;
import io.clnra.c_l_n_r_a.repos.GameCountRepository;
import io.clnra.c_l_n_r_a.repos.HWCRepository;
import io.clnra.c_l_n_r_a.repos.HuntingRepository;
import io.clnra.c_l_n_r_a.repos.PartnersRepository;
import io.clnra.c_l_n_r_a.repos.PatrolsRepository;
import io.clnra.c_l_n_r_a.repos.TourismRepository;
import io.clnra.c_l_n_r_a.service.CBORegistrationService;
import io.clnra.c_l_n_r_a.util.WebUtils;
import jakarta.validation.Valid;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/cBORegistrations")
public class CBORegistrationController {

    private final CBORegistrationService cBORegistrationService;
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

    public CBORegistrationController(final CBORegistrationService cBORegistrationService,
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
        this.cBORegistrationService = cBORegistrationService;
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

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("managementsValues", cBOManagementRepository.findAll(Sort.by("id"))
                .stream()
                .collect(Collectors.toMap(CBOManagement::getId, CBOManagement::getFullName)));
        model.addAttribute("cBOPartnerssValues", partnersRepository.findAll(Sort.by("id"))
                .stream()
                .collect(Collectors.toMap(Partners::getId, Partners::getPartnerName)));
        model.addAttribute("propertiessValues", cBOPropertiesRepository.findAll(Sort.by("id"))
                .stream()
                .collect(Collectors.toMap(CBOProperties::getId, CBOProperties::getPropertyName)));
        model.addAttribute("projectsValues", cBOProjectsRepository.findAll(Sort.by("id"))
                .stream()
                .collect(Collectors.toMap(CBOProjects::getId, CBOProjects::getProjectName)));
        model.addAttribute("activitiessValues", cBOActivitiesRepository.findAll(Sort.by("id"))
                .stream()
                .collect(Collectors.toMap(CBOActivities::getId, CBOActivities::getActivityName)));
        model.addAttribute("cBOHuntingsValues", huntingRepository.findAll(Sort.by("id"))
                .stream()
                .collect(Collectors.toMap(Hunting::getId, Hunting::getAnimalName)));
        model.addAttribute("wildlifePPsValues", gameCountRepository.findAll(Sort.by("id"))
                .stream()
                .collect(Collectors.toMap(GameCount::getId, GameCount::getGameCount)));
        model.addAttribute("cBOTourismsValues", tourismRepository.findAll(Sort.by("id"))
                .stream()
                .collect(Collectors.toMap(Tourism::getId, Tourism::getTourismLog)));
        model.addAttribute("humanConflictsValues", hWCRepository.findAll(Sort.by("id"))
                .stream()
                .collect(Collectors.toMap(HWC::getId, HWC::getHWCLog)));
        model.addAttribute("aPsValues", antiPoachingRepository.findAll(Sort.by("id"))
                .stream()
                .collect(Collectors.toMap(AntiPoaching::getId, AntiPoaching::getSiteName)));
        model.addAttribute("cSRCBOsValues", cSRRepository.findAll(Sort.by("id"))
                .stream()
                .collect(Collectors.toMap(CSR::getId, CSR::getCRSLog)));
        model.addAttribute("cBOPatrolssValues", patrolsRepository.findAll(Sort.by("id"))
                .stream()
                .collect(Collectors.toMap(Patrols::getId, Patrols::getPatrolsLog)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("cBORegistrations", cBORegistrationService.findAll());
        return "cBORegistration/list";
    }

    @GetMapping("/add")
    public String add(
            @ModelAttribute("cBORegistration") final CBORegistrationDTO cBORegistrationDTO) {
        return "cBORegistration/add";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("cBORegistration") @Valid final CBORegistrationDTO cBORegistrationDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "cBORegistration/add";
        }
        cBORegistrationService.create(cBORegistrationDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("cBORegistration.create.success"));
        return "redirect:/cBORegistrations";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("cBORegistration", cBORegistrationService.get(id));
        return "cBORegistration/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("cBORegistration") @Valid final CBORegistrationDTO cBORegistrationDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "cBORegistration/edit";
        }
        cBORegistrationService.update(id, cBORegistrationDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("cBORegistration.update.success"));
        return "redirect:/cBORegistrations";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = cBORegistrationService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            cBORegistrationService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("cBORegistration.delete.success"));
        }
        return "redirect:/cBORegistrations";
    }

}
