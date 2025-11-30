package fr.cytech.projetjeespring.controllers.payroll;

import fr.cytech.projetjeespring.dtos.PayrollDeductionFormDTO;
import fr.cytech.projetjeespring.entities.Employee;
import fr.cytech.projetjeespring.entities.PayrollDeduction;
import fr.cytech.projetjeespring.mappers.PayrollMapper;
import fr.cytech.projetjeespring.models.PayslipResultDTO;
import fr.cytech.projetjeespring.repositories.PayrollDeductionRepository;
import fr.cytech.projetjeespring.services.EmployeeService;
import fr.cytech.projetjeespring.services.PayrollService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/payroll")
@RequiredArgsConstructor
public class PayrollController {

    private final PayrollDeductionRepository deductionRepository;
    private final PayrollService payrollService;
    private final EmployeeService employeeService;
    private final PayrollMapper payrollMapper;

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @GetMapping("/deductions")
    public String listDeductions(Model model) {
        model.addAttribute("deductions", deductionRepository.findAll());
        return "payroll/deductions_list";
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @GetMapping("/deductions/edit")
    public String editDeduction(@RequestParam(required = false) Integer id, Model model) {
        PayrollDeductionFormDTO dto;
        if (id != null) {
            PayrollDeduction existing = deductionRepository.findById(id).orElseThrow();
            dto = payrollMapper.toDto(existing);
        } else {
            dto = new PayrollDeductionFormDTO();
        }
        model.addAttribute("deductionForm", dto);
        return "payroll/deductions_form";
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @PostMapping("/deductions/save")
    public String saveDeduction(@Valid @ModelAttribute("deductionForm") PayrollDeductionFormDTO dto,
                                BindingResult result) {
        if (result.hasErrors()) {
            return "payroll/deductions_form";
        }
        PayrollDeduction entity;
        if (dto.getId() != null) {
            entity = deductionRepository.findById(dto.getId()).orElseThrow();
            payrollMapper.updateEntityFromDto(dto, entity);
        } else {
            entity = payrollMapper.toEntity(dto);
        }
        deductionRepository.save(entity);
        return "redirect:/payroll/deductions";
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @GetMapping("/generate")
    public String generatorForm(@RequestParam(required = false) Integer employeeId, Model model) {
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("selectedEmployeeId", employeeId);
        return "payroll/generator_form";
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @PostMapping("/generate")
    public String generateResult(
            @RequestParam Integer employeeId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            Model model) {

        Employee emp = employeeService.findById(employeeId);
        PayslipResultDTO payslip = payrollService.generatePayslip(emp, startDate, endDate);

        model.addAttribute("payslip", payslip);
        return "payroll/payslip_view";
    }

    @GetMapping("/my-payslip")
    public String myPayslipForm(Authentication auth, Model model) {
        Integer myId = Integer.parseInt(auth.getName());
        model.addAttribute("myId", myId);
        return "payroll/my_generator_form";
    }

    @PostMapping("/my-payslip")
    public String generateMyPayslip(
            Authentication auth,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            Model model) {

        Integer myId = Integer.parseInt(auth.getName());
        Employee me = employeeService.findById(myId);

        PayslipResultDTO payslip = payrollService.generatePayslip(me, startDate, endDate);
        model.addAttribute("payslip", payslip);
        return "payroll/payslip_view";
    }
}