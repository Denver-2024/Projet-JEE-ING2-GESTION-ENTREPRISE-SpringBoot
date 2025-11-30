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

    <c:if test="${param.passwordChanged == 'true'}">
        <div style="background-color: #d4edda; color: #155724; padding: 10px; margin-bottom: 15px; border: 1px solid #c3e6cb;">
            Password updated successfully!
        </div>
    </c:if>

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

    <hr/>
    <a href="/employees/change-password" style="background: #eee; padding: 5px 10px; text-decoration: none; border: 1px solid #ccc;">
        Change My Password
    </a>
</div>
</body>
</html>