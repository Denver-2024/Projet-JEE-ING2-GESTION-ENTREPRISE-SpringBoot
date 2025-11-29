<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <style>
        body { font-family: monospace; }
        .box { border: 1px solid black; padding: 20px; width: 600px; margin: 20px; }
        h2 { text-align: center; border-bottom: 1px dashed black; }
        .row { display: flex; justify-content: space-between; }
        .total { border-top: 1px solid black; font-weight: bold; margin-top: 10px; padding-top: 5px; }
    </style>
</head>
<body>
<a href="/payroll/generate">Generate Another</a> | <button onclick="window.print()">Print</button>

<div class="box">
    <h2>PAYSLIP</h2>
    <p><strong>Employee:</strong> ${payslip.employee.firstName} ${payslip.employee.lastName} (ID: ${payslip.employee.id})</p>
    <p><strong>Period:</strong> ${payslip.startDate} to ${payslip.endDate} (${payslip.monthsCount} months)</p>

    <hr/>

    <h3>EARNINGS</h3>
    <div class="row">
        <span>Base Salary (Monthly)</span>
        <span><fmt:formatNumber value="${payslip.monthlyGrossSalary}" type="currency" currencySymbol="EUR"/></span>
    </div>
    <div class="row">
        <span><strong>Total Base Salary</strong> (${payslip.monthsCount} months)</span>
        <span><strong><fmt:formatNumber value="${payslip.totalGrossSalary}" type="currency" currencySymbol="EUR"/></strong></span>
    </div>

    <br/>
    <u>Bonuses:</u>
    <c:forEach items="${payslip.bonusBreakdown}" var="b">
        <div class="row">
            <span> - ${b}</span>
        </div>
    </c:forEach>
    <div class="row">
        <span><strong>Total Bonuses</strong></span>
        <span><fmt:formatNumber value="${payslip.totalBonuses}" type="currency" currencySymbol="EUR"/></span>
    </div>

    <hr/>

    <h3>DEDUCTIONS</h3>
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

    <div class="row total">
        <span>NET PAYABLE</span>
        <span><fmt:formatNumber value="${payslip.netPay}" type="currency" currencySymbol="EUR"/></span>
    </div>
</div>
</body>
</html>