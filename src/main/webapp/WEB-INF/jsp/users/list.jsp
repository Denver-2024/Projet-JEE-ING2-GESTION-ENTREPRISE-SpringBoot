<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<body>
<h1>Application Users (Admin Only)</h1>
<a href="/db-test/employees">Back to Employee CRUD</a> |
<a href="/logout">Logout</a>

<table border="1">
    <tr>
        <th>ID (Username)</th>
        <th>Name</th>
        <th>Current Roles</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${employees}" var="emp">
        <tr>
            <td>${emp.id}</td>
            <td>${emp.firstName} ${emp.lastName}</td>
            <td>
                <c:forEach items="${emp.roles}" var="role">
                    [${role.name}]
                </c:forEach>
            </td>
            <td>
                <a href="/db-test/users/edit-roles/${emp.id}">Manage Roles</a> |
                <a href="/db-test/users/reset-pass/${emp.id}">Reset Pass</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>