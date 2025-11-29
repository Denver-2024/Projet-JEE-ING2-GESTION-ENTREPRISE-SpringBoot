package fr.cytech.projetjeespring.controllers.department;

import fr.cytech.projetjeespring.dtos.DepartmentFormDTO;
import fr.cytech.projetjeespring.entities.Department;
import fr.cytech.projetjeespring.mappers.DepartmentMapper;
import fr.cytech.projetjeespring.services.DepartmentService;
import fr.cytech.projetjeespring.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/db-test/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    private final DepartmentMapper departmentMapper;

    @GetMapping
    public String listDepartments(Model model) {
        model.addAttribute("departments", departmentService.findAll());
        return "departments/list";
    }

    @GetMapping("/edit")
    public String editDepartment(@RequestParam(required = false) Integer id, Model model) {
        DepartmentFormDTO dto;
        if (id != null) {
            Department existing = departmentService.findById(id);
            dto = departmentMapper.toDto(existing);
        } else {
            dto = new DepartmentFormDTO();
        }

        model.addAttribute("departmentForm", dto);
        model.addAttribute("allEmployees", employeeService.findAll());
        return "departments/form";
    }

    @PostMapping("/save")
    public String saveDepartment(@Valid @ModelAttribute("departmentForm") DepartmentFormDTO dto,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("allEmployees", employeeService.findAll());
            return "departments/form";
        }

        Department department;
        if (dto.getId() != null) {
            department = departmentService.findById(dto.getId());
            departmentMapper.updateEntityFromDto(dto, department);
        } else {
            department = departmentMapper.toEntity(dto);
        }

        departmentService.save(department);

        return "redirect:/db-test/departments";
    }

    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Integer id) {
        departmentService.delete(id);
        return "redirect:/db-test/departments";
    }
}