package io.clnra.c_l_n_r_a.controller;

import io.clnra.c_l_n_r_a.model.AntiPoachingDTO;
import io.clnra.c_l_n_r_a.service.AntiPoachingService;
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
@RequestMapping("/antiPoachings")
public class AntiPoachingController {

    private final AntiPoachingService antiPoachingService;

    public AntiPoachingController(final AntiPoachingService antiPoachingService) {
        this.antiPoachingService = antiPoachingService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("antiPoachings", antiPoachingService.findAll());
        return "antiPoaching/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("antiPoaching") final AntiPoachingDTO antiPoachingDTO) {
        return "antiPoaching/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("antiPoaching") @Valid final AntiPoachingDTO antiPoachingDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "antiPoaching/add";
        }
        antiPoachingService.create(antiPoachingDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("antiPoaching.create.success"));
        return "redirect:/antiPoachings";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("antiPoaching", antiPoachingService.get(id));
        return "antiPoaching/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("antiPoaching") @Valid final AntiPoachingDTO antiPoachingDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "antiPoaching/edit";
        }
        antiPoachingService.update(id, antiPoachingDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("antiPoaching.update.success"));
        return "redirect:/antiPoachings";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = antiPoachingService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            antiPoachingService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("antiPoaching.delete.success"));
        }
        return "redirect:/antiPoachings";
    }

}
