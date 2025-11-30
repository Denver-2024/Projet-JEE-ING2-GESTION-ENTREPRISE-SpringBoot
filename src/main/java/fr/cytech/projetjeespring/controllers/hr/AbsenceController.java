package fr.cytech.projetjeespring.controllers.hr;

import fr.cytech.projetjeespring.model.dtos.AbsenceFormDTO;
import fr.cytech.projetjeespring.model.entities.Absence;
import fr.cytech.projetjeespring.model.mappers.AbsenceMapper;
import fr.cytech.projetjeespring.repositories.AbsenceRepository;
import fr.cytech.projetjeespring.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/absences")
@RequiredArgsConstructor
public class AbsenceController {

    private final AbsenceRepository absenceRepository;
    private final EmployeeService employeeService;
    private final AbsenceMapper absenceMapper;

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @GetMapping
    public String listAllAbsences(Model model) {
        model.addAttribute("absences", absenceRepository.findAll());
        model.addAttribute("pageTitle", "All Absences (HR)");
        return "absences/list";
    }

    @GetMapping("/my-absences")
    public String listMyAbsences(Authentication auth, Model model) {
        Integer myId = Integer.parseInt(auth.getName());
        List<Absence> myAbsences = absenceRepository.findByEmployeeIdOrderByStartDateDesc(myId);

        model.addAttribute("absences", myAbsences);
        model.addAttribute("pageTitle", "My Absence History");
        return "absences/list";
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @GetMapping("/edit")
    public String editAbsence(@RequestParam(required = false) Integer id, Model model) {
        AbsenceFormDTO dto;
        if (id != null) {
            Absence existing = absenceRepository.findById(id).orElseThrow();
            dto = absenceMapper.toDto(existing);
        } else {
            dto = new AbsenceFormDTO();
        }

        model.addAttribute("absenceForm", dto);
        model.addAttribute("employees", employeeService.findAll());
        return "absences/form";
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @PostMapping("/save")
    public String saveAbsence(@Valid @ModelAttribute("absenceForm") AbsenceFormDTO dto,
                              BindingResult result,
                              Model model) {
        if (result.hasErrors()) {
            model.addAttribute("employees", employeeService.findAll());
            return "absences/form";
        }

        Absence absence;
        if (dto.getId() != null) {
            absence = absenceRepository.findById(dto.getId()).orElseThrow();
            absenceMapper.updateEntityFromDto(dto, absence);
        } else {
            absence = absenceMapper.toEntity(dto);
        }

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