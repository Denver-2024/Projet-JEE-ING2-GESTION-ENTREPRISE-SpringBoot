package fr.cytech.projetjeespring.services;

import fr.cytech.projetjeespring.entities.*;
import fr.cytech.projetjeespring.enums.DeductionType;
import fr.cytech.projetjeespring.models.PayslipResultDTO;
import fr.cytech.projetjeespring.repositories.AbsenceRepository;
import fr.cytech.projetjeespring.repositories.BonusRepository;
import fr.cytech.projetjeespring.repositories.PayrollDeductionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PayrollService {

    private final BonusRepository bonusRepository;
    private final PayrollDeductionRepository deductionRepository;
    private final AbsenceRepository absenceRepository;

    public PayslipResultDTO generatePayslip(Employee employee, LocalDate startDate, LocalDate endDate) {
        PayslipResultDTO payslip = new PayslipResultDTO();
        payslip.setEmployee(employee);
        payslip.setStartDate(startDate);
        payslip.setEndDate(endDate);

        List<Absence> unpaidAbsences = absenceRepository.findAll().stream()
                .filter(a -> a.getEmployee().getId().equals(employee.getId()))
                .filter(Absence::isPaidLeave)
                .filter(a -> !a.isPaidLeave())
                .toList();

        BigDecimal accumulatedBaseSalary = BigDecimal.ZERO;
        BigDecimal accumulatedDeduction = BigDecimal.ZERO;
        int daysCovered = 0;
        int unpaidDays = 0;

        LocalDate current = startDate;
        while (!current.isAfter(endDate)) {
            daysCovered++;

            int daysInMonth = current.lengthOfMonth();
            BigDecimal dailyRate = employee.getSalary().divide(new BigDecimal(daysInMonth), 4, RoundingMode.HALF_UP);

            boolean isUnpaid = false;
            for (Absence abs : unpaidAbsences) {
                if (!current.isBefore(abs.getStartDate()) &&
                        (abs.getEndDate() == null || !current.isAfter(abs.getEndDate()))) {
                    isUnpaid = true;
                    break;
                }
            }

            accumulatedBaseSalary = accumulatedBaseSalary.add(dailyRate);

            if (isUnpaid) {
                accumulatedDeduction = accumulatedDeduction.add(dailyRate);
                unpaidDays++;
            }

            current = current.plusDays(1);
        }

        payslip.setTotalDaysCovered(daysCovered);
        payslip.setUnpaidAbsenceDays(unpaidDays);
        payslip.setBaseSalaryForPeriod(accumulatedBaseSalary.setScale(2, RoundingMode.HALF_UP));
        payslip.setAbsenceDeduction(accumulatedDeduction.setScale(2, RoundingMode.HALF_UP));

        BigDecimal gross = payslip.getBaseSalaryForPeriod().subtract(payslip.getAbsenceDeduction());
        payslip.setGrossSalary(gross);

        List<Bonus> bonuses = bonusRepository.findAll().stream()
                .filter(b -> b.getEmployee().getId().equals(employee.getId()))
                .filter(b -> !b.getAwardDate().isBefore(startDate) && !b.getAwardDate().isAfter(endDate))
                .toList();

        BigDecimal totalBonuses = BigDecimal.ZERO;
        List<String> bonusDescs = new ArrayList<>();
        for (Bonus b : bonuses) {
            totalBonuses = totalBonuses.add(b.getAmount());
            bonusDescs.add(b.getAwardDate() + ": " + b.getReason() + " (" + b.getAmount() + " EUR)");
        }
        payslip.setTotalBonuses(totalBonuses);
        payslip.setBonusBreakdown(bonusDescs);

        BigDecimal taxableIncome = gross.add(totalBonuses);

        List<PayrollDeduction> globalDeductions = deductionRepository.findAll();
        Map<String, BigDecimal> deductionMap = new HashMap<>();
        BigDecimal totalTax = BigDecimal.ZERO;

        for (PayrollDeduction pd : globalDeductions) {
            BigDecimal taxAmount;
            if (pd.getType() == DeductionType.FIXED) {
                BigDecimal ratio = new BigDecimal(daysCovered).divide(new BigDecimal(30), 4, RoundingMode.HALF_UP);
                taxAmount = pd.getAmount().multiply(ratio);
            } else {
                BigDecimal percentage = pd.getAmount().divide(new BigDecimal("100"), 4, RoundingMode.HALF_UP);
                taxAmount = taxableIncome.multiply(percentage);
            }

            taxAmount = taxAmount.setScale(2, RoundingMode.HALF_UP);
            deductionMap.put(pd.getName(), taxAmount);
            totalTax = totalTax.add(taxAmount);
        }

        payslip.setDeductionBreakdown(deductionMap);
        payslip.setTotalDeductions(totalTax);

        payslip.setNetPay(taxableIncome.subtract(totalTax));

        return payslip;
    }
}