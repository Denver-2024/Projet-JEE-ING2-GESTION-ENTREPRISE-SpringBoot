package fr.cytech.projetjeespring.controllers.employee;

import fr.cytech.projetjeespring.model.dtos.EmployeeFormDTO;
import fr.cytech.projetjeespring.model.dtos.EmployeeSummaryDTO;
import fr.cytech.projetjeespring.model.dtos.PasswordChangeFormDTO;
import fr.cytech.projetjeespring.model.entities.Employee;
import fr.cytech.projetjeespring.model.mappers.EmployeeMapper;
import fr.cytech.projetjeespring.services.DepartmentService;
import fr.cytech.projetjeespring.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final EmployeeMapper employeeMapper;

    @GetMapping
    public String listEmployees(
            @RequestParam(required = false) String query,
            @RequestParam(required = false, name = "department.id") Integer deptId,
            Model model) {

        List<Employee> entities = employeeService.search(query, deptId);
        List<EmployeeSummaryDTO> dtos = employeeMapper.toSummaryDtoList(entities);
        model.addAttribute("employees", dtos);

        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("currentQuery", query);
        model.addAttribute("currentDeptId", deptId);

        return "employees/list";
    }

    @GetMapping("/profile")
    public String myProfile(Authentication authentication, Model model) {
        Integer myId = Integer.parseInt(authentication.getName());
        Employee me = employeeService.findById(myId);

        model.addAttribute("employee", me);
        return "employees/profile";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @GetMapping("/edit")
    public String editEmployee(@RequestParam(required = false) Integer id, Model model) {
        EmployeeFormDTO dto;
        if (id != null) {
            Employee existing = employeeService.findById(id);
            dto = employeeMapper.toDto(existing);
        } else {
            dto = new EmployeeFormDTO();
        }

        model.addAttribute("employeeForm", dto);
        model.addAttribute("departments", departmentService.findAll());
        return "employees/form";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("employeeForm") EmployeeFormDTO dto,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            return "employees/form";
        }

        Employee employee;
        if (dto.getId() != null) {
            employee = employeeService.findById(dto.getId());
            employeeMapper.updateEntityFromDto(dto, employee);
        } else {
            employee = employeeMapper.toEntity(dto);
        }

        employeeService.save(employee);
        return "redirect:/employees";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        employeeService.delete(id);
        return "redirect:/employees";
    }


    @GetMapping("/change-password")
    public String changePasswordForm(Model model) {
        model.addAttribute("passwordForm", new PasswordChangeFormDTO());
        return "employees/change_password";
    }

    @PostMapping("/change-password")
    public String updatePassword(@Valid @ModelAttribute("passwordForm") PasswordChangeFormDTO dto,
                                 BindingResult result,
                                 Authentication auth,
                                 Model model) {

        if (result.hasErrors()) {
            return "employees/change_password";
        }

        if (!dto.getNewPassword().equals(dto.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "error.passwordForm", "New passwords do not match");
            return "employees/change_password";
        }

        try {
            Integer myId = Integer.parseInt(auth.getName());
            employeeService.changePassword(myId, dto.getOldPassword(), dto.getNewPassword());
        } catch (BadCredentialsException e) {
            result.rejectValue("oldPassword", "error.passwordForm", "Incorrect current password");
            return "employees/change_password";
        }

        return "redirect:/employees/profile?passwordChanged=true";
    }
}