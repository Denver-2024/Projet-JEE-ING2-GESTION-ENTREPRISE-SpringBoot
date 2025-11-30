<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        body { font-family: monospace; font-size: 1.1em; }
        .box { border: 2px solid black; padding: 30px; width: 700px; margin: 20px auto; }
        h2 { text-align: center; border-bottom: 2px solid black; padding-bottom: 10px; }
        .row { display: flex; justify-content: space-between; margin-bottom: 5px; }
        .sub-row { display: flex; justify-content: space-between; margin-bottom: 5px; color: #555; padding-left: 20px; }
        .section-head { font-weight: bold; margin-top: 15px; border-bottom: 1px dashed #aaa; }
        .total { border-top: 2px solid black; font-weight: bold; margin-top: 15px; padding-top: 5px; font-size: 1.2em; }
    </style>
    <title>Payslip</title>
</head>
<body>
<div style="text-align:center;">
    <button onclick="window.print()">Print / PDF</button>
    <a href="/">Exit</a>
</div>

<div class="box">
    <h2>PAYSLIP</h2>
    <div class="row">
        <span><strong>Employee:</strong> ${payslip.employee.firstName} ${payslip.employee.lastName}</span>
        <span>ID: ${payslip.employee.id}</span>
    </div>
    <div class="row">
        <span><strong>Period:</strong> ${payslip.startDate} to ${payslip.endDate}</span>
        <span>Days Covered: ${payslip.totalDaysCovered}</span>
    </div>

    <div class="section-head">EARNINGS</div>
    <div class="row">
        <span>Base Salary</span>
        <span><fmt:formatNumber value="${payslip.baseSalaryForPeriod}" type="currency" currencySymbol="EUR"/></span>
    </div>

    <c:if test="${payslip.unpaidAbsenceDays > 0}">
        <div class="row" style="color:red;">
            <span>Unpaid Absences (${payslip.unpaidAbsenceDays} days)</span>
            <span>- <fmt:formatNumber value="${payslip.absenceDeduction}" type="currency" currencySymbol="EUR"/></span>
        </div>
    </c:if>

    <div class="row" style="font-weight:bold;">
        <span>GROSS SALARY (After Absences)</span>
        <span><fmt:formatNumber value="${payslip.grossSalary}" type="currency" currencySymbol="EUR"/></span>
    </div>

    <br/>
    <u>Bonuses:</u>
    <c:forEach items="${payslip.bonusBreakdown}" var="b">
        <div class="sub-row">
            <span>${b}</span>
        </div>
    </c:forEach>
    <div class="row">
        <span><strong>Total Bonuses</strong></span>
        <span><fmt:formatNumber value="${payslip.totalBonuses}" type="currency" currencySymbol="EUR"/></span>
    </div>

    <div class="section-head">DEDUCTIONS</div>
    <c:forEach items="${payslip.deductionBreakdown}" var="entry">
        <div class="row">
            <span>${entry.key}</span>
            <span>- <fmt:formatNumber value="${entry.value}" type="currency" currencySymbol="EUR"/></span>
        </div>
    </c:forEach>
    <div class="row">
        <span><strong>Total Deductions</strong></span>
        <span>- <fmt:formatNumber value="${payslip.totalDeductions}" type="currency" currencySymbol="EUR"/></span>
    </div>

    <div class="total row">
        <span>NET PAYABLE</span>
        <span><fmt:formatNumber value="${payslip.netPay}" type="currency" currencySymbol="EUR"/></span>
    </div>
</div>
</body>
</html>