<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Payslip</title>
</head>
<body>
<div style="border-bottom: 1px solid #ccc; margin-bottom: 10px;">
    <a href="/">Dashboard</a>
</div>

<h1>Generate My Payslip</h1>

<form action="/payroll/my-payslip" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <h3>Select Period</h3>
    From: <input type="date" name="startDate" required />
    To: <input type="date" name="endDate" required />

    <br/><br/>
    <button type="submit">Generate</button>
</form>
</body>
</html>