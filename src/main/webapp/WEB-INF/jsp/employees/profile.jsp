<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>My Profile</title>
</head>
<body>
<div style="border-bottom: 1px solid #ccc; margin-bottom: 10px;">
    <a href="/">Dashboard</a> | <a href="/employees">Directory</a>
</div>

<h1>My Profile</h1>

<div style="border: 1px solid #ddd; padding: 20px; width: 50%;">
    <h2>${employee.firstName} ${employee.lastName}</h2>
    <p><strong>Employee ID:</strong> ${employee.id}</p>
    <p><strong>Email:</strong> ${employee.email}</p>
    <p><strong>Department:</strong> ${employee.department != null ? employee.department.name : 'None'}</p>

    <hr/>

    <p><strong>Job Type:</strong> ${employee.type}</p>
    <p><strong>Address:</strong> ${employee.address}</p>

    <p><strong>Current Salary:</strong>
        <fmt:formatNumber value="${employee.salary}" type="currency" currencySymbol="EUR"/>
    </p>

    <h3>My Roles</h3>
    <ul>
        <c:forEach items="${employee.roles}" var="role">
            <li>${role.name}</li>
        </c:forEach>
    </ul>
</div>
</body>
</html>