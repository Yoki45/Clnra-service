package io.clnra.c_l_n_r_a.controller;

import io.clnra.c_l_n_r_a.model.CBOPropertiesDTO;
import io.clnra.c_l_n_r_a.service.CBOPropertiesService;
import io.clnra.c_l_n_r_a.util.WebUtils;
import jakarta.validation.Valid;
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
@RequestMapping("/cBOPropertiess")
public class CBOPropertiesController {

    private final CBOPropertiesService cBOPropertiesService;

    public CBOPropertiesController(final CBOPropertiesService cBOPropertiesService) {
        this.cBOPropertiesService = cBOPropertiesService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("cBOPropertiess", cBOPropertiesService.findAll());
        return "cBOProperties/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("cBOProperties") final CBOPropertiesDTO cBOPropertiesDTO) {
        return "cBOProperties/add";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("cBOProperties") @Valid final CBOPropertiesDTO cBOPropertiesDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("registrationTag") && cBOPropertiesService.registrationTagExists(cBOPropertiesDTO.getRegistrationTag())) {
            bindingResult.rejectValue("registrationTag", "Exists.cBOProperties.registrationTag");
        }
        if (bindingResult.hasErrors()) {
            return "cBOProperties/add";
        }
        cBOPropertiesService.create(cBOPropertiesDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("cBOProperties.create.success"));
        return "redirect:/cBOPropertiess";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("cBOProperties", cBOPropertiesService.get(id));
        return "cBOProperties/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("cBOProperties") @Valid final CBOPropertiesDTO cBOPropertiesDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("registrationTag") &&
                !cBOPropertiesService.get(id).getRegistrationTag().equalsIgnoreCase(cBOPropertiesDTO.getRegistrationTag()) &&
                cBOPropertiesService.registrationTagExists(cBOPropertiesDTO.getRegistrationTag())) {
            bindingResult.rejectValue("registrationTag", "Exists.cBOProperties.registrationTag");
        }
        if (bindingResult.hasErrors()) {
            return "cBOProperties/edit";
        }
        cBOPropertiesService.update(id, cBOPropertiesDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("cBOProperties.update.success"));
        return "redirect:/cBOPropertiess";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = cBOPropertiesService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            cBOPropertiesService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("cBOProperties.delete.success"));
        }
        return "redirect:/cBOPropertiess";
    }

}
