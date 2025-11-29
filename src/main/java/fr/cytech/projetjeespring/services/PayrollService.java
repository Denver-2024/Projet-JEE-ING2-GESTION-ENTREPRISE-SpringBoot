package fr.cytech.projetjeespring.services;

import fr.cytech.projetjeespring.entities.*;
import fr.cytech.projetjeespring.enums.DeductionType;
import fr.cytech.projetjeespring.models.Payslip;
import fr.cytech.projetjeespring.repositories.BonusRepository;
import fr.cytech.projetjeespring.repositories.PayrollDeductionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PayrollService {

    private final BonusRepository bonusRepository;
    private final PayrollDeductionRepository deductionRepository;

    public Payslip generatePayslip(Employee employee, LocalDate startDate, LocalDate endDate) {
        Payslip payslip = new Payslip();
        payslip.setEmployee(employee);
        payslip.setStartDate(startDate);
        payslip.setEndDate(endDate);

        long monthsBetween = ChronoUnit.MONTHS.between(
                startDate.withDayOfMonth(1),
                endDate.withDayOfMonth(1)
        ) + 1;
        payslip.setMonthsCount((int) monthsBetween);

        payslip.setMonthlyGrossSalary(employee.getSalary());
        payslip.setTotalGrossSalary(employee.getSalary() * monthsBetween);

        List<Bonus> bonuses = bonusRepository.findAll().stream()
                .filter(b -> b.getEmployee().getId().equals(employee.getId()))
                .filter(b -> !b.getAwardDate().isBefore(startDate) && !b.getAwardDate().isAfter(endDate))
                .toList();

        float totalBonuses = 0;
        List<String> bonusDescs = new ArrayList<>();
        for (Bonus b : bonuses) {
            totalBonuses += b.getAmount();
            bonusDescs.add(b.getAwardDate() + ": " + b.getReason() + " (" + b.getAmount() + ")");
        }
        payslip.setTotalBonuses(totalBonuses);
        payslip.setBonusBreakdown(bonusDescs);

        List<PayrollDeduction> globalDeductions = deductionRepository.findAll();
        Map<String, Float> deductionMap = new HashMap<>();
        float totalDeductions = 0;

        for (PayrollDeduction pd : globalDeductions) {
            float amountPerMonth = 0;
            if (pd.getType() == DeductionType.FIXED) {
                amountPerMonth = pd.getAmount();
            } else {
                amountPerMonth = employee.getSalary() * (pd.getAmount() / 100.0f);
            }

            float totalForPeriod = amountPerMonth * monthsBetween;
            deductionMap.put(pd.getName() + " (" + pd.getType() + ": " + pd.getAmount() + ")", totalForPeriod);
            totalDeductions += totalForPeriod;
        }

        payslip.setDeductionBreakdown(deductionMap);
        payslip.setTotalDeductions(totalDeductions);

        payslip.setNetPay((payslip.getTotalGrossSalary() + totalBonuses) - totalDeductions);

        return payslip;
    }
}