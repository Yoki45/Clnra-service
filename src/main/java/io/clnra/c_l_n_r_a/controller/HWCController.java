package io.clnra.c_l_n_r_a.controller;

import io.clnra.c_l_n_r_a.model.HWCDTO;
import io.clnra.c_l_n_r_a.service.HWCService;
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
@RequestMapping("/hWCs")
public class HWCController {

    private final HWCService hWCService;

    public HWCController(final HWCService hWCService) {
        this.hWCService = hWCService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("hWCs", hWCService.findAll());
        return "hWC/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("hWC") final HWCDTO hWCDTO) {
        return "hWC/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("hWC") @Valid final HWCDTO hWCDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "hWC/add";
        }
        hWCService.create(hWCDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("hWC.create.success"));
        return "redirect:/hWCs";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("hWC", hWCService.get(id));
        return "hWC/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("hWC") @Valid final HWCDTO hWCDTO, final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "hWC/edit";
        }
        hWCService.update(id, hWCDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("hWC.update.success"));
        return "redirect:/hWCs";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = hWCService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            hWCService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("hWC.delete.success"));
        }
        return "redirect:/hWCs";
    }

}
