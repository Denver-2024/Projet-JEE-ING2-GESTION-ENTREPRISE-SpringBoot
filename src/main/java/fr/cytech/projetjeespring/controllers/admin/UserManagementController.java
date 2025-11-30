package fr.cytech.projetjeespring.controllers.admin;

import fr.cytech.projetjeespring.model.dtos.UserRolesFormDTO;
import fr.cytech.projetjeespring.model.entities.Employee;
import fr.cytech.projetjeespring.model.entities.Role;
import fr.cytech.projetjeespring.repositories.EmployeeRepository;
import fr.cytech.projetjeespring.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/users")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class UserManagementController {

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String listUsers(Model model) {
        // In a real app, use pagination here.
        model.addAttribute("employees", employeeRepository.findAll());
        return "users/list";
    }

    @GetMapping("/roles/{id}")
    public String editUserRoles(@PathVariable Integer id, Model model) {
        Employee emp = employeeRepository.findById(id).orElseThrow();

        UserRolesFormDTO dto = new UserRolesFormDTO();
        dto.setEmployeeId(emp.getId());
        dto.setEmployeeName(emp.getFirstName() + " " + emp.getLastName());

        dto.setSelectedRoles(emp.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList()));

        model.addAttribute("userRolesForm", dto);
        model.addAttribute("allRoles", roleRepository.findAll());
        return "users/edit_roles";
    }

    @PostMapping("/roles/save")
    public String saveUserRoles(@ModelAttribute UserRolesFormDTO dto) {
        Employee emp = employeeRepository.findById(dto.getEmployeeId()).orElseThrow();

        emp.getRoles().clear();
        if (dto.getSelectedRoles() != null) {
            for (String roleName : dto.getSelectedRoles()) {
                roleRepository.findById(roleName).ifPresent(emp.getRoles()::add);
            }
        }

        employeeRepository.save(emp);
        return "redirect:/admin/users";
    }

    @GetMapping("/reset-password/{id}")
    public String resetPassword(@PathVariable Integer id) {
        Employee emp = employeeRepository.findById(id).orElseThrow();
        emp.setPassword(passwordEncoder.encode("password"));
        employeeRepository.save(emp);
        return "redirect:/admin/users?reset=success&id=" + id;
    }
}