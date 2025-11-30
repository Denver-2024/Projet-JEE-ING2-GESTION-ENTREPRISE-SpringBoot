<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Generate Payslip</title>
    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
    <script>
        $(document).ready(function() {
            $('.searchable-select').select2({ width: '300px' });
        });
    </script>
</head>
<body>
<div style="border-bottom: 1px solid #ccc; margin-bottom: 10px;">
    <a href="/">Dashboard</a> | <a href="/employees">Employee Directory</a>
</div>

<h1>Generate Payslip</h1>

<form action="/payroll/generate" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <h3>1. Select Employee</h3>
    <select name="employeeId" class="searchable-select">
        <c:forEach items="${employees}" var="emp">
            <option value="${emp.id}" ${selectedEmployeeId == emp.id ? 'selected' : ''}>
                    ${emp.firstName} ${emp.lastName} (ID: ${emp.id})
            </option>
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