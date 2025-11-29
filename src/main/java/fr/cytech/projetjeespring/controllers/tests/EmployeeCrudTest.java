package fr.cytech.projetjeespring.controllers.tests;

import fr.cytech.projetjeespring.entities.Employee;
import fr.cytech.projetjeespring.services.DepartmentService;
import fr.cytech.projetjeespring.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/db-test/employees")
public class EmployeeCrudTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String listEmployees(@ModelAttribute Employee searchProbe, Model model) {
        model.addAttribute("employees", employeeService.findByExample(searchProbe));
        model.addAttribute("departments", departmentService.findAll());
        return "employees/list";
    }

    @GetMapping("/edit")
    public String editEmployee(@RequestParam(required = false) Integer id, Model model) {
        Employee employee = (id != null) ? employeeService.findById(id) : new Employee();
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departmentService.findAll());
        return "employees/form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee) {

        if (employee.getDepartment() != null && employee.getDepartment().getId() == null) {
            employee.setDepartment(null);
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