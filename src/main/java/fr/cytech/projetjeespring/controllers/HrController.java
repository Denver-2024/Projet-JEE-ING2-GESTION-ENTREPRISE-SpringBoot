package fr.cytech.projetjeespring.controllers;

import fr.cytech.projetjeespring.entities.Absence;
import fr.cytech.projetjeespring.entities.Bonus;
import fr.cytech.projetjeespring.repositories.AbsenceRepository;
import fr.cytech.projetjeespring.repositories.BonusRepository;
import fr.cytech.projetjeespring.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hr")
@RequiredArgsConstructor
public class HrController {

    private final AbsenceRepository absenceRepository;
    private final BonusRepository bonusRepository;
    private final EmployeeService employeeService;

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @GetMapping("/absences")
    public String listAbsences(Model model) {
        model.addAttribute("absences", absenceRepository.findAll());
        return "hr/absences_list";
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @GetMapping("/absences/edit")
    public String editAbsence(@RequestParam(required = false) Integer id, Model model) {
        Absence absence = (id != null) ? absenceRepository.findById(id).orElse(new Absence()) : new Absence();
        model.addAttribute("absence", absence);
        model.addAttribute("employees", employeeService.findAll());
        return "hr/absences_form";
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @PostMapping("/absences/save")
    public String saveAbsence(@ModelAttribute Absence absence) {
        absenceRepository.save(absence);
        return "redirect:/hr/absences";
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @GetMapping("/absences/delete/{id}")
    public String deleteAbsence(@PathVariable Integer id) {
        absenceRepository.deleteById(id);
        return "redirect:/hr/absences";
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @GetMapping("/bonuses")
    public String listBonuses(Model model) {
        model.addAttribute("bonuses", bonusRepository.findAll());
        return "hr/bonuses_list";
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @GetMapping("/bonuses/edit")
    public String editBonus(@RequestParam(required = false) Integer id, Model model) {
        Bonus bonus = (id != null) ? bonusRepository.findById(id).orElse(new Bonus()) : new Bonus();
        model.addAttribute("bonus", bonus);
        model.addAttribute("employees", employeeService.findAll());
        return "hr/bonuses_form";
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @PostMapping("/bonuses/save")
    public String saveBonus(@ModelAttribute Bonus bonus) {
        bonusRepository.save(bonus);
        return "redirect:/hr/bonuses";
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @GetMapping("/bonuses/delete/{id}")
    public String deleteBonus(@PathVariable Integer id) {
        bonusRepository.deleteById(id);
        return "redirect:/hr/bonuses";
    }
}