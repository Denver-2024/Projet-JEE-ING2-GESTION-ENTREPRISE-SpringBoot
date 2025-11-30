<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Deduction</title>
    <style>.error { color: red; }</style>
</head>
<body>
<div style="border-bottom: 1px solid #ccc; margin-bottom: 10px;">
    <a href="/">Dashboard</a> | <a href="/payroll/deductions">Back to List</a>
</div>

<h1>${empty deductionForm.id ? 'Create' : 'Edit'} Tax/Deduction</h1>

<form:form method="post" action="/payroll/deductions/save" modelAttribute="deductionForm">
    <form:hidden path="id" />

    <label>Name:</label><br/>
    <form:input path="name" />
    <form:errors path="name" cssClass="error" /><br/><br/>

    <label>Description:</label><br/>
    <form:textarea path="description" /><br/><br/>

    <label>Type:</label><br/>
    <form:select path="type">
        <form:option value="FIXED">Fixed Amount (EUR)</form:option>
        <form:option value="PERCENTAGE">Percentage of Gross Salary</form:option>
    </form:select><br/><br/>

    <label>Amount:</label><br/>
    <form:input path="amount" type="number" step="0.01" />
    <small><i>(e.g., 20.00)</i></small>
    <form:errors path="amount" cssClass="error" /><br/><br/>

    <button type="submit">Save</button>
</form:form>
</body>
</html>