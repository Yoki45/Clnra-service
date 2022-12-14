package io.clnra.c_l_n_r_a.controller;

import io.clnra.c_l_n_r_a.model.CBOProjectsDTO;
import io.clnra.c_l_n_r_a.service.CBOProjectsService;
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
@RequestMapping("/cBOProjectss")
public class CBOProjectsController {

    private final CBOProjectsService cBOProjectsService;

    public CBOProjectsController(final CBOProjectsService cBOProjectsService) {
        this.cBOProjectsService = cBOProjectsService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("cBOProjectss", cBOProjectsService.findAll());
        return "cBOProjects/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("cBOProjects") final CBOProjectsDTO cBOProjectsDTO) {
        return "cBOProjects/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("cBOProjects") @Valid final CBOProjectsDTO cBOProjectsDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "cBOProjects/add";
        }
        cBOProjectsService.create(cBOProjectsDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("cBOProjects.create.success"));
        return "redirect:/cBOProjectss";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("cBOProjects", cBOProjectsService.get(id));
        return "cBOProjects/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("cBOProjects") @Valid final CBOProjectsDTO cBOProjectsDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "cBOProjects/edit";
        }
        cBOProjectsService.update(id, cBOProjectsDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("cBOProjects.update.success"));
        return "redirect:/cBOProjectss";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = cBOProjectsService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            cBOProjectsService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("cBOProjects.delete.success"));
        }
        return "redirect:/cBOProjectss";
    }

}
