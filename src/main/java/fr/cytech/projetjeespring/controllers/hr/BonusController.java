package fr.cytech.projetjeespring.controllers.hr;

import fr.cytech.projetjeespring.dtos.BonusFormDTO;
import fr.cytech.projetjeespring.entities.Bonus;
import fr.cytech.projetjeespring.mappers.BonusMapper;
import fr.cytech.projetjeespring.repositories.BonusRepository;
import fr.cytech.projetjeespring.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bonuses")
@RequiredArgsConstructor
public class BonusController {

    // TODO: FAIRE UN SERVICE!!!
    private final BonusRepository bonusRepository;
    private final EmployeeService employeeService;
    private final BonusMapper bonusMapper;

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @GetMapping
    public String listAllBonuses(Model model) {
        model.addAttribute("bonuses", bonusRepository.findAll());
        model.addAttribute("pageTitle", "All Bonuses (HR)");
        return "bonuses/list";
    }

    @GetMapping("/my-bonuses")
    public String listMyBonuses(Authentication auth, Model model) {
        Integer myId = Integer.parseInt(auth.getName());
        // Flemme de l'implémenter correctement
        // TODO: changer le service pour pas avoir à faire ca.
        var myBonuses = bonusRepository.findAll().stream()
                .filter(b -> b.getEmployee().getId().equals(myId))
                .toList();

        model.addAttribute("bonuses", myBonuses);
        model.addAttribute("pageTitle", "My Bonuses");
        return "bonuses/list";
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @GetMapping("/edit")
    public String editBonus(@RequestParam(required = false) Integer id, Model model) {
        BonusFormDTO dto;
        if (id != null) {
            Bonus existing = bonusRepository.findById(id).orElseThrow();
            dto = bonusMapper.toDto(existing);
        } else {
            dto = new BonusFormDTO();
        }

        model.addAttribute("bonusForm", dto);
        model.addAttribute("employees", employeeService.findAll());
        return "bonuses/form";
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @PostMapping("/save")
    public String saveBonus(@Valid @ModelAttribute("bonusForm") BonusFormDTO dto,
                            BindingResult result,
                            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("employees", employeeService.findAll());
            return "bonuses/form";
        }

        Bonus bonus;
        if (dto.getId() != null) {
            bonus = bonusRepository.findById(dto.getId()).orElseThrow();
            bonusMapper.updateEntityFromDto(dto, bonus);
        } else {
            bonus = bonusMapper.toEntity(dto);
        }

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