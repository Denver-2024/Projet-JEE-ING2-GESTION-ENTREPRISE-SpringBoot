<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<body>
<h1>Generate Payslip</h1>
<a href="/payroll/deductions">Back to Configuration</a>

<form action="/payroll/generate" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <h3>1. Select Employee</h3>
    <select name="employeeId" required>
        <c:forEach items="${employees}" var="emp">
            <option value="${emp.id}">${emp.firstName} ${emp.lastName}</option>
        </c:forEach>
    </select>

    <h3>2. Select Period</h3>
    From: <input type="date" name="startDate" required />
    To: <input type="date" name="endDate" required />

    <br/><br/>
    <button type="submit">Generate</button>
</form>
</body>
</html>