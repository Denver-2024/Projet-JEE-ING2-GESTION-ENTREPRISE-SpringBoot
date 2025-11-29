<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>${empty employeeForm.id ? 'Create' : 'Edit'} Employee</title>
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
    <a href="/">Dashboard</a> | <a href="/employees">Back to Directory</a>
</div>

<h1>${empty employeeForm.id ? 'Register' : 'Edit'} Employee</h1>

<form:form method="post" action="/employees/save" modelAttribute="employeeForm">
    <form:hidden path="id" />

    <label>First Name:</label><br/>
    <form:input path="firstName" />
    <form:errors path="firstName" cssClass="error" /><br/><br/>

    <label>Last Name:</label><br/>
    <form:input path="lastName" />
    <form:errors path="lastName" cssClass="error" /><br/><br/>

    <label>Email:</label><br/>
    <form:input path="email" type="email" />
    <form:errors path="email" cssClass="error" /><br/><br/>

    <label>Address:</label><br/>
    <form:input path="address" /><br/><br/>

    <label>Gender:</label><br/>
    <form:input path="gender" /><br/><br/>

    <label>Job Type/Level:</label><br/>
    <form:select path="type">
        <form:option value="JUNIOR">JUNIOR</form:option>
        <form:option value="INTERMEDIATE">INTERMEDIATE</form:option>
        <form:option value="SENIOR">SENIOR</form:option>
    </form:select>
    <form:errors path="type" cssClass="error" /><br/><br/>

    <label>Salary (EUR):</label><br/>
    <form:input path="salary" type="number" step="0.01" />
    <form:errors path="salary" cssClass="error" /><br/><br/>

    <label>Password:</label><br/>
    <form:password path="password" />
    <small><i>(Leave empty to keep current password)</i></small><br/><br/>

    <label>Department:</label><br/>
    <form:select path="departmentId" cssClass="searchable-select">
        <form:option value="">-- No Department --</form:option>
        <c:forEach items="${departments}" var="dept">
            <form:option value="${dept.id}">${dept.name}</form:option>
        </c:forEach>
    </form:select>
    <br/><br/>

    <button type="submit">Save Employee</button>
    <a href="/employees">Cancel</a>
</form:form>
</body>
</html>