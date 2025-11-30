<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Administration</title>
</head>
<body>
<div style="border-bottom: 1px solid #ccc; margin-bottom: 10px;">
    <a href="/">Dashboard</a> | <a href="/employees">Employee Directory</a>
</div>

<h1>System Users & Roles</h1>

<c:if test="${param.reset == 'success'}">
    <p style="color:green; font-weight:bold;">
        Password for User ${param.id} has been reset to 'password'.
    </p>
</c:if>

<table border="1" cellpadding="5" style="width: 100%;">
    <thead>
    <tr>
        <th>ID (Login)</th>
        <th>Name</th>
        <th>Email</th>
        <th>Active Roles</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${employees}" var="emp">
        <tr>
            <td><b>${emp.id}</b></td>
            <td>${emp.firstName} ${emp.lastName}</td>
            <td>${emp.email}</td>
            <td>
                <c:forEach items="${emp.roles}" var="role">
                            <span style="background:#eee; padding:2px 5px; border-radius:3px; font-size:0.9em;">
                                    ${role.name}
                            </span>
                </c:forEach>
            </td>
            <td>
                <a href="/admin/users/roles/${emp.id}">Manage Roles</a> |
                <a href="/admin/users/reset-password/${emp.id}"
                   onclick="return confirm('Reset password to default (password)?');"
                   style="color:red;">Reset Pass</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>