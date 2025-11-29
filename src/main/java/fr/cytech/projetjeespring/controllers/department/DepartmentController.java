package fr.cytech.projetjeespring.controllers.department;

import fr.cytech.projetjeespring.dtos.DepartmentFormDTO;
import fr.cytech.projetjeespring.dtos.DepartmentSummaryDTO;
import fr.cytech.projetjeespring.entities.Department;
import fr.cytech.projetjeespring.entities.Employee;
import fr.cytech.projetjeespring.mappers.DepartmentMapper;
import fr.cytech.projetjeespring.services.DepartmentService;
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
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    private final DepartmentMapper departmentMapper;

    @GetMapping
    public String listDepartments(Model model) {
        List<Department> entities = departmentService.findAll();
        List<DepartmentSummaryDTO> dtos = departmentMapper.toSummaryDtoList(entities);
        model.addAttribute("departments", dtos);
        return "departments/list";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/my-department")
    public String myDepartment(Authentication auth) {
        Integer myId = Integer.parseInt(auth.getName());
        Department myDept = departmentService.findByHeadId(myId);

        if (myDept != null) {
            return "redirect:/departments/" + myDept.getId();
        }
        return "redirect:/departments";
    }

    @GetMapping("/{id}")
    public String viewDepartment(@PathVariable Integer id, Model model) {
        Department dept = departmentService.findById(id);
        if (dept == null) return "redirect:/departments";

        model.addAttribute("department", dept);
        return "departments/details";
    }

    @PreAuthorize("hasRole('ADMIN')")
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

    @PreAuthorize("hasRole('ADMIN')")
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
        return "redirect:/departments";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Integer id) {
        departmentService.delete(id);
        return "redirect:/departments";
    }
}