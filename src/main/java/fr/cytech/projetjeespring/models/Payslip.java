package fr.cytech.projetjeespring.models;

import fr.cytech.projetjeespring.entities.Employee;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
public class Payslip {
    private Employee employee;
    private LocalDate startDate;
    private LocalDate endDate;
    private int monthsCount;

    private float monthlyGrossSalary;
    private float totalGrossSalary;
    private float totalBonuses;

    private List<String> bonusBreakdown;
    private Map<String, Float> deductionBreakdown;

    private float totalDeductions;
    private float netPay;
}