package fr.cytech.projetjeespring.controllers.tests;

import fr.cytech.projetjeespring.entities.Employee;
import fr.cytech.projetjeespring.entities.Role;
import fr.cytech.projetjeespring.repositories.EmployeeRepository;
import fr.cytech.projetjeespring.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/db-test/users")
@RequiredArgsConstructor
public class UserRoleTestController {

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "users/list";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit-roles/{id}")
    public String editUserRoles(@PathVariable Integer id, Model model) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        List<Role> allRoles = roleRepository.findAll();

        model.addAttribute("employee", employee);
        model.addAttribute("allRoles", allRoles);
        return "users/edit_roles";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save-roles")
    public String saveUserRoles(@RequestParam Integer employeeId, @RequestParam(required = false) List<String> roleNames) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();

        employee.getRoles().clear();
        if (roleNames != null) {
            for (String roleName : roleNames) {
                Optional<Role> role = roleRepository.findById(roleName);
                role.ifPresent(value -> employee.getRoles().add(value));
            }
        }

        employeeRepository.save(employee);
        return "redirect:/db-test/users";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/reset-pass/{id}")
    public String resetPassword(@PathVariable Integer id) {
        Employee emp = employeeRepository.findById(id).orElseThrow();
        emp.setPassword(passwordEncoder.encode("password")); // Default pass
        employeeRepository.save(emp);
        return "redirect:/db-test/users";
    }
}