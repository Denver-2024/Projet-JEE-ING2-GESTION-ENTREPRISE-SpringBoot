package fr.cytech.projetjeespring.controllers.hr;

import fr.cytech.projetjeespring.entities.Absence;
import fr.cytech.projetjeespring.repositories.AbsenceRepository;
import fr.cytech.projetjeespring.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/absences")
@RequiredArgsConstructor
public class AbsenceController {

    private final AbsenceRepository absenceRepository;
    private final EmployeeService employeeService;

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @GetMapping
    public String listAbsences(Model model) {
        model.addAttribute("absences", absenceRepository.findAll());
        return "hr/absences_list";
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @GetMapping("/edit")
    public String editAbsence(@RequestParam(required = false) Integer id, Model model) {
        Absence absence = (id != null) ? absenceRepository.findById(id).orElse(new Absence()) : new Absence();
        model.addAttribute("absence", absence);
        model.addAttribute("employees", employeeService.findAll());
        return "hr/absences_form";
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @PostMapping("/save")
    public String saveAbsence(@ModelAttribute Absence absence) {
        absenceRepository.save(absence);
        return "redirect:/absences";
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteAbsence(@PathVariable Integer id) {
        absenceRepository.deleteById(id);
        return "redirect:/absences";
    }
}