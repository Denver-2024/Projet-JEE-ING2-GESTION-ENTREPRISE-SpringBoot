<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>${empty bonusForm.id ? 'Award' : 'Edit'} Bonus</title>
    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
    <script>
        $(document).ready(function() {
            $('.searchable-select').select2({ width: '300px' });
        });
    </script>
    <style>.error { color: red; }</style>
</head>
<body>
<div style="border-bottom: 1px solid #ccc; margin-bottom: 10px;">
    <a href="/">Dashboard</a> | <a href="/bonuses">Back to Bonuses</a>
</div>

<h1>${empty bonusForm.id ? 'Award' : 'Edit'} Bonus</h1>

<form:form method="post" action="/bonuses/save" modelAttribute="bonusForm">
    <form:hidden path="id" />

    <label>Employee:</label><br/>
    <form:select path="employeeId" cssClass="searchable-select">
        <form:option value="">-- Select Employee --</form:option>
        <c:forEach items="${employees}" var="emp">
            <form:option value="${emp.id}">${emp.firstName} ${emp.lastName}</form:option>
        </c:forEach>
    </form:select>
    <form:errors path="employeeId" cssClass="error" /><br/><br/>

    <label>Award Date:</label><br/>
    <form:input path="awardDate" type="date" />
    <form:errors path="awardDate" cssClass="error" /><br/><br/>

    <label>Amount (EUR):</label><br/>
    <form:input path="amount" type="number" step="0.01" />
    <form:errors path="amount" cssClass="error" /><br/><br/>

    <label>Reason:</label><br/>
    <form:textarea path="reason" rows="3" cols="40"/><br/><br/>

    <button type="submit">Save Bonus</button>
    <a href="/bonuses">Cancel</a>
</form:form>
</body>
</html>