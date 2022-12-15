package io.clnra.c_l_n_r_a.controller;

import io.clnra.c_l_n_r_a.model.GameCountDTO;
import io.clnra.c_l_n_r_a.service.GameCountService;
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
@RequestMapping("/gameCounts")
public class GameCountController {

    private final GameCountService gameCountService;

    public GameCountController(final GameCountService gameCountService) {
        this.gameCountService = gameCountService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("gameCounts", gameCountService.findAll());
        return "gameCount/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("gameCount") final GameCountDTO gameCountDTO) {
        return "gameCount/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("gameCount") @Valid final GameCountDTO gameCountDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "gameCount/add";
        }
        gameCountService.create(gameCountDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("gameCount.create.success"));
        return "redirect:/gameCounts";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("gameCount", gameCountService.get(id));
        return "gameCount/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("gameCount") @Valid final GameCountDTO gameCountDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "gameCount/edit";
        }
        gameCountService.update(id, gameCountDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("gameCount.update.success"));
        return "redirect:/gameCounts";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = gameCountService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            gameCountService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("gameCount.delete.success"));
        }
        return "redirect:/gameCounts";
    }

}
