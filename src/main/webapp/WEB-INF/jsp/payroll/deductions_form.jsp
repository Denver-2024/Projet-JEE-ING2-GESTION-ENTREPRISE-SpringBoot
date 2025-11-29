<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<body>
<h1>Edit Deduction</h1>
<form action="/payroll/deductions/save" method="post">
    <input type="hidden" name="id" value="${deduction.id}" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    Name: <input type="text" name="name" value="${deduction.name}" required /><br/>
    Description: <input type="text" name="description" value="${deduction.description}" /><br/>

    Type:
    <select name="type">
        <option value="FIXED" ${deduction.type == 'FIXED' ? 'selected' : ''}>Fixed Amount (Currency)</option>
        <option value="PERCENTAGE" ${deduction.type == 'PERCENTAGE' ? 'selected' : ''}>Percentage of Salary</option>
    </select><br/>

    Amount: <input type="number" step="0.01" name="amount" value="${deduction.amount}" required />
    <i>(If percentage, enter 20 for 20%)</i><br/>

    <button type="submit">Save</button>
</form>
</body>
</html>