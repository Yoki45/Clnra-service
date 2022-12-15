package io.clnra.c_l_n_r_a.controller;

import io.clnra.c_l_n_r_a.model.PatrolsDTO;
import io.clnra.c_l_n_r_a.service.PatrolsService;
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
@RequestMapping("/patrolss")
public class PatrolsController {

    private final PatrolsService patrolsService;

    public PatrolsController(final PatrolsService patrolsService) {
        this.patrolsService = patrolsService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("patrolss", patrolsService.findAll());
        return "patrols/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("patrols") final PatrolsDTO patrolsDTO) {
        return "patrols/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("patrols") @Valid final PatrolsDTO patrolsDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "patrols/add";
        }
        patrolsService.create(patrolsDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("patrols.create.success"));
        return "redirect:/patrolss";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("patrols", patrolsService.get(id));
        return "patrols/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("patrols") @Valid final PatrolsDTO patrolsDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "patrols/edit";
        }
        patrolsService.update(id, patrolsDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("patrols.update.success"));
        return "redirect:/patrolss";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = patrolsService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            patrolsService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("patrols.delete.success"));
        }
        return "redirect:/patrolss";
    }

}
