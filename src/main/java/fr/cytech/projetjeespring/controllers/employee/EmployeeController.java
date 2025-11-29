package fr.cytech.projetjeespring.controllers.employee;

import fr.cytech.projetjeespring.dtos.EmployeeFormDTO;
import fr.cytech.projetjeespring.entities.Employee;
import fr.cytech.projetjeespring.mappers.EmployeeMapper;
import fr.cytech.projetjeespring.services.DepartmentService;
import fr.cytech.projetjeespring.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/db-test/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final EmployeeMapper employeeMapper;

    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "employees/list";
    }

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
        return "redirect:/db-test/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        employeeService.delete(id);
        return "redirect:/db-test/employees";
    }
}