package io.clnra.c_l_n_r_a.controller;

import io.clnra.c_l_n_r_a.model.CSRDTO;
import io.clnra.c_l_n_r_a.service.CSRService;
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
@RequestMapping("/cSRs")
public class CSRController {

    private final CSRService cSRService;

    public CSRController(final CSRService cSRService) {
        this.cSRService = cSRService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("cSRs", cSRService.findAll());
        return "cSR/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("cSR") final CSRDTO cSRDTO) {
        return "cSR/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("cSR") @Valid final CSRDTO cSRDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "cSR/add";
        }
        cSRService.create(cSRDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("cSR.create.success"));
        return "redirect:/cSRs";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("cSR", cSRService.get(id));
        return "cSR/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("cSR") @Valid final CSRDTO cSRDTO, final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "cSR/edit";
        }
        cSRService.update(id, cSRDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("cSR.update.success"));
        return "redirect:/cSRs";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = cSRService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            cSRService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("cSR.delete.success"));
        }
        return "redirect:/cSRs";
    }

}
