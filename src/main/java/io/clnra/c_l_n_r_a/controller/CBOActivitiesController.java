package io.clnra.c_l_n_r_a.controller;

import io.clnra.c_l_n_r_a.model.CBOActivitiesDTO;
import io.clnra.c_l_n_r_a.service.CBOActivitiesService;
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
@RequestMapping("/cBOActivitiess")
public class CBOActivitiesController {

    private final CBOActivitiesService cBOActivitiesService;

    public CBOActivitiesController(final CBOActivitiesService cBOActivitiesService) {
        this.cBOActivitiesService = cBOActivitiesService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("cBOActivitiess", cBOActivitiesService.findAll());
        return "cBOActivities/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("cBOActivities") final CBOActivitiesDTO cBOActivitiesDTO) {
        return "cBOActivities/add";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("cBOActivities") @Valid final CBOActivitiesDTO cBOActivitiesDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "cBOActivities/add";
        }
        cBOActivitiesService.create(cBOActivitiesDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("cBOActivities.create.success"));
        return "redirect:/cBOActivitiess";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("cBOActivities", cBOActivitiesService.get(id));
        return "cBOActivities/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("cBOActivities") @Valid final CBOActivitiesDTO cBOActivitiesDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "cBOActivities/edit";
        }
        cBOActivitiesService.update(id, cBOActivitiesDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("cBOActivities.update.success"));
        return "redirect:/cBOActivitiess";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = cBOActivitiesService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            cBOActivitiesService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("cBOActivities.delete.success"));
        }
        return "redirect:/cBOActivitiess";
    }

}
