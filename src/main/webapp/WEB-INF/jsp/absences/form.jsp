<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>${empty absenceForm.id ? 'Report' : 'Edit'} Absence</title>
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
    <a href="/">Dashboard</a> | <a href="/absences">Back to Absences</a>
</div>

<h1>${empty absenceForm.id ? 'Report' : 'Edit'} Absence</h1>

<form:form method="post" action="/absences/save" modelAttribute="absenceForm">
    <form:hidden path="id" />

    <label>Employee:</label><br/>
    <form:select path="employeeId" cssClass="searchable-select">
        <form:option value="">-- Select Employee --</form:option>
        <c:forEach items="${employees}" var="emp">
            <form:option value="${emp.id}">${emp.firstName} ${emp.lastName}</form:option>
        </c:forEach>
    </form:select>
    <form:errors path="employeeId" cssClass="error" /><br/><br/>

    <label>Start Date:</label><br/>
    <form:input path="startDate" type="date" />
    <form:errors path="startDate" cssClass="error" /><br/><br/>

    <label>End Date (Empty if ongoing):</label><br/>
    <form:input path="endDate" type="date" /><br/><br/>

    <label>Reason:</label><br/>
    <form:textarea path="reason" rows="3" cols="40"/><br/><br/>

    <label>Details:</label><br/>
    <form:checkbox path="expected" /> Expected?<br/>
    <form:checkbox path="paidLeave" /> Paid Leave?<br/><br/>

    <button type="submit">Save Absence</button>
    <a href="/absences">Cancel</a>
</form:form>
</body>
</html>