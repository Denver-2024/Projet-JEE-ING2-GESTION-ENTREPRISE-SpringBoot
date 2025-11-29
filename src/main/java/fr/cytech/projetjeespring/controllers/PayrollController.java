package fr.cytech.projetjeespring.controllers;

import fr.cytech.projetjeespring.entities.Employee;
import fr.cytech.projetjeespring.entities.PayrollDeduction;
import fr.cytech.projetjeespring.models.Payslip;
import fr.cytech.projetjeespring.repositories.PayrollDeductionRepository;
import fr.cytech.projetjeespring.services.EmployeeService;
import fr.cytech.projetjeespring.services.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/payroll")
@RequiredArgsConstructor
public class PayrollController {

    private final PayrollDeductionRepository deductionRepository;
    private final PayrollService payrollService;
    private final EmployeeService employeeService;

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @GetMapping("/deductions")
    public String listDeductions(Model model) {
        model.addAttribute("deductions", deductionRepository.findAll());
        return "payroll/deductions_list";
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @GetMapping("/deductions/edit")
    public String editDeduction(@RequestParam(required = false) Integer id, Model model) {
        PayrollDeduction deduction = (id != null)
                ? deductionRepository.findById(id).orElse(new PayrollDeduction())
                : new PayrollDeduction();
        model.addAttribute("deduction", deduction);
        return "payroll/deductions_form";
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @PostMapping("/deductions/save")
    public String saveDeduction(@ModelAttribute PayrollDeduction deduction) {
        deductionRepository.save(deduction);
        return "redirect:/payroll/deductions";
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @GetMapping("/deductions/delete/{id}")
    public String deleteDeduction(@PathVariable Integer id) {
        deductionRepository.deleteById(id);
        return "redirect:/payroll/deductions";
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @GetMapping("/generate")
    public String showGeneratorForm(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "payroll/generator_form";
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @PostMapping("/generate")
    public String generatePayslip(
            @RequestParam Integer employeeId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            Model model) {

        Employee emp = employeeService.findById(employeeId);
        Payslip payslip = payrollService.generatePayslip(emp, startDate, endDate);

        model.addAttribute("payslip", payslip);
        return "payroll/payslip_view";
    }
}