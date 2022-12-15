package io.clnra.c_l_n_r_a.controller;

import io.clnra.c_l_n_r_a.model.PartnersDTO;
import io.clnra.c_l_n_r_a.service.PartnersService;
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
@RequestMapping("/partnerss")
public class PartnersController {

    private final PartnersService partnersService;

    public PartnersController(final PartnersService partnersService) {
        this.partnersService = partnersService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("partnerss", partnersService.findAll());
        return "partners/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("partners") final PartnersDTO partnersDTO) {
        return "partners/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("partners") @Valid final PartnersDTO partnersDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "partners/add";
        }
        partnersService.create(partnersDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("partners.create.success"));
        return "redirect:/partnerss";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("partners", partnersService.get(id));
        return "partners/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("partners") @Valid final PartnersDTO partnersDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "partners/edit";
        }
        partnersService.update(id, partnersDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("partners.update.success"));
        return "redirect:/partnerss";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = partnersService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            partnersService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("partners.delete.success"));
        }
        return "redirect:/partnerss";
    }

}
