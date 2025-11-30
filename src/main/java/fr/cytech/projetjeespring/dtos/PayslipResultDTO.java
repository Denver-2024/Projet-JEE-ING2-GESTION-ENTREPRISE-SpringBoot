package fr.cytech.projetjeespring.models;

import fr.cytech.projetjeespring.entities.Employee;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
public class PayslipResultDTO {
    private Employee employee;
    private LocalDate startDate;
    private LocalDate endDate;

    private int totalDaysCovered;
    private int unpaidAbsenceDays;

    private BigDecimal baseSalaryForPeriod;
    private BigDecimal absenceDeduction;
    private BigDecimal grossSalary;

    private BigDecimal totalBonuses;
    private List<String> bonusBreakdown;

    private BigDecimal totalDeductions;
    private Map<String, BigDecimal> deductionBreakdown;

    private BigDecimal netPay;
}