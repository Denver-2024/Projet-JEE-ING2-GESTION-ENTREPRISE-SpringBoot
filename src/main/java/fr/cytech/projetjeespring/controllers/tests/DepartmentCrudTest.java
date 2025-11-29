package fr.cytech.projetjeespring.controllers.tests;

import fr.cytech.projetjeespring.entities.Department;
import fr.cytech.projetjeespring.services.DepartmentService;
import fr.cytech.projetjeespring.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/db-test/departments")
@RequiredArgsConstructor
public class DepartmentCrudTest {

    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    @GetMapping
    public String listDepartments(@ModelAttribute Department searchProbe, Model model) {
        model.addAttribute("departments", departmentService.findByExample(searchProbe));
        return "departments/list";
    }

    @GetMapping("/edit")
    public String editDepartment(@RequestParam(required = false) Integer id, Model model) {
        Department dept = (id != null) ? departmentService.findById(id) : new Department();
        model.addAttribute("department", dept);
        model.addAttribute("allEmployees", employeeService.findAll());
        return "departments/form";
    }

    @PostMapping("/save")
    public String saveDepartment(@ModelAttribute Department department) {
        departmentService.createDepartment(department, department.getHead().getId());
        return "redirect:/db-test/departments";
    }

    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Integer id) {
        departmentService.delete(id);
        return "redirect:/db-test/departments";
    }
}