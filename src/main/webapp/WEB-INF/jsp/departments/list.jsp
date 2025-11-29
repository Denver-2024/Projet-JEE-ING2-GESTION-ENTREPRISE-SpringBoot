<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Department Directory</title>
</head>
<body>
<div style="border-bottom: 1px solid #ccc; margin-bottom: 10px;">
    <a href="/">Dashboard</a> |
    <sec:authorize access="hasRole('MANAGER')">
        <a href="/departments/my-department">My Department</a> |
    </sec:authorize>
    <a href="/employees">Employee Directory</a>
</div>

<h1>Departments</h1>

<sec:authorize access="hasRole('ADMIN')">
    <p><a href="/departments/edit"><b>+ Create New Department</b></a></p>
</sec:authorize>

<table border="1" cellpadding="5" style="width: 100%;">
    <thead>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Head of Department</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${departments}" var="dept">
        <tr>
            <td><b><a href="/departments/${dept.id}">${dept.name}</a></b></td>
            <td>${dept.description}</td>
            <td>
                <c:if test="${not empty dept.headEmail}">
                    <a href="mailto:${dept.headEmail}">${dept.headName} (${dept.headEmail})</a>
                </c:if>
                <c:if test="${empty dept.headEmail}">VACANT</c:if>
            </td>
            <td>
                <sec:authorize access="hasRole('ADMIN')">
                    <a href="/departments/edit?id=${dept.id}">Edit</a>
                    <a href="/departments/delete/${dept.id}" onclick="return confirm('Delete?');">Delete</a>
                </sec:authorize>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>