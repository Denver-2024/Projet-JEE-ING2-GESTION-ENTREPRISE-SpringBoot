package fr.cytech.projetjeespring.models;

import fr.cytech.projetjeespring.entities.Employee;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
public class Payslip {
    private Employee employee;
    private LocalDate startDate;
    private LocalDate endDate;
    private int monthsCount;

    private BigDecimal monthlyGrossSalary;
    private BigDecimal totalGrossSalary;
    private BigDecimal totalBonuses;

    private List<String> bonusBreakdown;
    private Map<String, BigDecimal> deductionBreakdown;

    private BigDecimal totalDeductions;
    private BigDecimal netPay;
}