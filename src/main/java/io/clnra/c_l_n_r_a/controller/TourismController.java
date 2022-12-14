package io.clnra.c_l_n_r_a.controller;

import io.clnra.c_l_n_r_a.model.TourismDTO;
import io.clnra.c_l_n_r_a.service.TourismService;
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
@RequestMapping("/tourisms")
public class TourismController {

    private final TourismService tourismService;

    public TourismController(final TourismService tourismService) {
        this.tourismService = tourismService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("tourisms", tourismService.findAll());
        return "tourism/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("tourism") final TourismDTO tourismDTO) {
        return "tourism/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("tourism") @Valid final TourismDTO tourismDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "tourism/add";
        }
        tourismService.create(tourismDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("tourism.create.success"));
        return "redirect:/tourisms";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("tourism", tourismService.get(id));
        return "tourism/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("tourism") @Valid final TourismDTO tourismDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "tourism/edit";
        }
        tourismService.update(id, tourismDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("tourism.update.success"));
        return "redirect:/tourisms";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = tourismService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            tourismService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("tourism.delete.success"));
        }
        return "redirect:/tourisms";
    }

}
