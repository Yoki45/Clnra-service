package io.clnra.c_l_n_r_a.controller;

import io.clnra.c_l_n_r_a.model.HuntingDTO;
import io.clnra.c_l_n_r_a.service.HuntingService;
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
@RequestMapping("/huntings")
public class HuntingController {

    private final HuntingService huntingService;

    public HuntingController(final HuntingService huntingService) {
        this.huntingService = huntingService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("huntings", huntingService.findAll());
        return "hunting/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("hunting") final HuntingDTO huntingDTO) {
        return "hunting/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("hunting") @Valid final HuntingDTO huntingDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("huntingLog") && huntingService.huntingLogExists(huntingDTO.getHuntingLog())) {
            bindingResult.rejectValue("huntingLog", "Exists.hunting.huntingLog");
        }
        if (bindingResult.hasErrors()) {
            return "hunting/add";
        }
        huntingService.create(huntingDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("hunting.create.success"));
        return "redirect:/huntings";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("hunting", huntingService.get(id));
        return "hunting/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("hunting") @Valid final HuntingDTO huntingDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("huntingLog") &&
                !huntingService.get(id).getHuntingLog().equals(huntingDTO.getHuntingLog()) &&
                huntingService.huntingLogExists(huntingDTO.getHuntingLog())) {
            bindingResult.rejectValue("huntingLog", "Exists.hunting.huntingLog");
        }
        if (bindingResult.hasErrors()) {
            return "hunting/edit";
        }
        huntingService.update(id, huntingDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("hunting.update.success"));
        return "redirect:/huntings";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = huntingService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            huntingService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("hunting.delete.success"));
        }
        return "redirect:/huntings";
    }

}
