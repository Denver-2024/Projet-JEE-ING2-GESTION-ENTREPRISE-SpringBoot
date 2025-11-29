package fr.cytech.projetjeespring.controllers.hr;

import fr.cytech.projetjeespring.entities.Bonus;
import fr.cytech.projetjeespring.repositories.BonusRepository;
import fr.cytech.projetjeespring.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bonuses")
@RequiredArgsConstructor
public class BonusController {

    private final BonusRepository bonusRepository;
    private final EmployeeService employeeService;

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @GetMapping
    public String listBonuses(Model model) {
        model.addAttribute("bonuses", bonusRepository.findAll());
        return "hr/bonuses_list";
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @GetMapping("/edit")
    public String editBonus(@RequestParam(required = false) Integer id, Model model) {
        Bonus bonus = (id != null) ? bonusRepository.findById(id).orElse(new Bonus()) : new Bonus();
        model.addAttribute("bonus", bonus);
        model.addAttribute("employees", employeeService.findAll());
        return "hr/bonuses_form";
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @PostMapping("/save")
    public String saveBonus(@ModelAttribute Bonus bonus) {
        bonusRepository.save(bonus);
        return "redirect:/bonuses";
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteBonus(@PathVariable Integer id) {
        bonusRepository.deleteById(id);
        return "redirect:/bonuses";
    }
}