<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Roles</title>
</head>
<body>
<div style="border-bottom: 1px solid #ccc; margin-bottom: 10px;">
    <a href="/admin/users">Back to User List</a>
</div>

<h1>Manage Roles: ${userRolesForm.employeeName}</h1>

<form:form method="post" action="/admin/users/roles/save" modelAttribute="userRolesForm">
    <form:hidden path="employeeId" />

    <h3>Select Roles</h3>
    <p><i>Note: 'ROLE_EMPLOYEE' is required for basic access.</i></p>

    <c:forEach items="${allRoles}" var="role">
        <div style="margin-bottom: 10px;">
            <form:checkbox path="selectedRoles" value="${role.name}" />
            <label><b>${role.name}</b></label>
        </div>
    </c:forEach>

    <br/>
    <button type="submit">Save Roles</button>
    <a href="/admin/users">Cancel</a>
</form:form>
</body>
</html>