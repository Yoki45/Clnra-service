package io.clnra.c_l_n_r_a.controller;

import io.clnra.c_l_n_r_a.model.CBOManagementDTO;
import io.clnra.c_l_n_r_a.service.CBOManagementService;
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
@RequestMapping("/cBOManagements")
public class CBOManagementController {

    private final CBOManagementService cBOManagementService;

    public CBOManagementController(final CBOManagementService cBOManagementService) {
        this.cBOManagementService = cBOManagementService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("cBOManagements", cBOManagementService.findAll());
        return "cBOManagement/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("cBOManagement") final CBOManagementDTO cBOManagementDTO) {
        return "cBOManagement/add";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("cBOManagement") @Valid final CBOManagementDTO cBOManagementDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "cBOManagement/add";
        }
        cBOManagementService.create(cBOManagementDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("cBOManagement.create.success"));
        return "redirect:/cBOManagements";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("cBOManagement", cBOManagementService.get(id));
        return "cBOManagement/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("cBOManagement") @Valid final CBOManagementDTO cBOManagementDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "cBOManagement/edit";
        }
        cBOManagementService.update(id, cBOManagementDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("cBOManagement.update.success"));
        return "redirect:/cBOManagements";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = cBOManagementService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            cBOManagementService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("cBOManagement.delete.success"));
        }
        return "redirect:/cBOManagements";
    }

}
