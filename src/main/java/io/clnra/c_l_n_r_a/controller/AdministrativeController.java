package io.clnra.c_l_n_r_a.controller;

import io.clnra.c_l_n_r_a.domain.CBORegistration;
import io.clnra.c_l_n_r_a.model.AdministrativeDTO;
import io.clnra.c_l_n_r_a.repos.CBORegistrationRepository;
import io.clnra.c_l_n_r_a.service.AdministrativeService;
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
@RequestMapping("/administratives")
public class AdministrativeController {

    private final AdministrativeService administrativeService;
    private final CBORegistrationRepository cBORegistrationRepository;

    public AdministrativeController(final AdministrativeService administrativeService,
            final CBORegistrationRepository cBORegistrationRepository) {
        this.administrativeService = administrativeService;
        this.cBORegistrationRepository = cBORegistrationRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("cBORegistrationValues", cBORegistrationRepository.findAll(Sort.by("id"))
                .stream()
                .collect(Collectors.toMap(CBORegistration::getId, CBORegistration::getCBOName)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("administratives", administrativeService.findAll());
        return "administrative/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("administrative") final AdministrativeDTO administrativeDTO) {
        return "administrative/add";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("administrative") @Valid final AdministrativeDTO administrativeDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "administrative/add";
        }
        administrativeService.create(administrativeDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("administrative.create.success"));
        return "redirect:/administratives";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("administrative", administrativeService.get(id));
        return "administrative/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("administrative") @Valid final AdministrativeDTO administrativeDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "administrative/edit";
        }
        administrativeService.update(id, administrativeDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("administrative.update.success"));
        return "redirect:/administratives";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        administrativeService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("administrative.delete.success"));
        return "redirect:/administratives";
    }

}
