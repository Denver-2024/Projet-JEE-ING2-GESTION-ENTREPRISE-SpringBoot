<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>${empty departmentForm.id ? 'Create' : 'Edit'} Department</title>
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
    <a href="/">Dashboard</a> | <a href="/departments">Back to Departments</a>
</div>

<h1>${empty departmentForm.id ? 'Create' : 'Edit'} Department</h1>

<form:form method="post" action="/departments/save" modelAttribute="departmentForm">
    <form:hidden path="id" />

    <label>Department Name:</label><br/>
    <form:input path="name" />
    <form:errors path="name" cssClass="error" /><br/><br/>

    <label>Description:</label><br/>
    <form:textarea path="description" /><br/><br/>

    <label>Head of Department:</label><br/>
    <form:select path="headEmployeeId" cssClass="searchable-select">
        <form:option value="">-- Select Head --</form:option>
        <c:forEach items="${allEmployees}" var="emp">
            <form:option value="${emp.id}">
                ${emp.firstName} ${emp.lastName} (ID: ${emp.id})
            </form:option>
        </c:forEach>
    </form:select>
    <br/><br/>

    <button type="submit">Save Department</button>
    <a href="/departments">Cancel</a>
</form:form>
</body>
</html>