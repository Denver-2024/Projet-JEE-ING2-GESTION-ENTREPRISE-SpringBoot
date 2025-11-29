<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>${isMyProjects ? 'My Team Projects' : 'All Projects'}</title>
</head>
<body>
<div style="border-bottom: 1px solid #ccc; margin-bottom: 10px;">
    <a href="/">Dashboard</a> |
    <a href="/projects">All Projects</a> |
    <a href="/projects/my-projects">My Department's Projects</a>
</div>

<h1>${isMyProjects ? 'My Team Projects' : 'All Projects Directory'}</h1>

<c:if test="${not empty message}">
    <p style="color:orange;">${message}</p>
</c:if>

<sec:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
    <p><a href="/projects/edit"><b>+ Create New Project</b></a></p>
</sec:authorize>

<table border="1" cellpadding="5" style="width: 100%;">
    <thead>
    <tr>
        <th>Project Name</th>
        <th>State</th>
        <th>Department</th>
        <th>Description</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${projects}" var="proj">
        <tr>
            <td><b>${proj.name}</b></td>

            <td style="color: ${proj.state == 'CANCELED' ? 'red' : (proj.state == 'FINISHED' ? 'green' : 'blue')}">
                    ${proj.state}
            </td>

            <td>${proj.departmentName}</td>
            <td>${proj.description}</td>
            <td>
                <sec:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
                    <a href="/projects/edit?id=${proj.id}">Edit</a>
                    <a href="/projects/delete/${proj.id}" onclick="return confirm('Delete project?');">Delete</a>
                </sec:authorize>
                <sec:authorize access="!hasAnyRole('MANAGER', 'ADMIN')">
                    <span style="color:#aaa;">View Only</span>
                </sec:authorize>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:if test="${empty projects}">
    <p>No projects found.</p>
</c:if>
</body>
</html>