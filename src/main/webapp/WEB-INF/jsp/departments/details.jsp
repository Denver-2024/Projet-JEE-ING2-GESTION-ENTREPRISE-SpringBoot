<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>${department.name} - Details</title>
</head>
<body>
<div style="border-bottom: 1px solid #ccc; margin-bottom: 10px;">
    <a href="/departments">Back to List</a>
</div>

<h1>${department.name}</h1>
<p><em>${department.description}</em></p>

<h3>Head of Department</h3>
<p>
    <c:if test="${department.head != null}">
        <a href="/employees/profile?id=${department.head.email}">
                ${department.head.firstName} ${department.head.lastName}
        </a>
    </c:if>
    <c:if test="${department.head == null}">VACANT</c:if>
</p>

<hr/>

<div style="display: flex; gap: 50px;">
    <div>
        <h3>Employees (${department.employees.size()})</h3>
        <ul>
            <c:forEach items="${department.employees}" var="emp">
                <li>
                    <a href="/employees/profile?id=${emp.id}">
                            ${emp.firstName} ${emp.lastName}
                    </a>
                    <small>(${emp.type})</small>
                </li>
            </c:forEach>
        </ul>
    </div>

    <div>
        <h3>Projects (${department.projects.size()})</h3>
        <ul>
            <c:forEach items="${department.projects}" var="proj">
                <li>
                    <b>${proj.name}</b> [${proj.state}]
                    <br/><small>${proj.description}</small>
                    <sec:authorize access="hasRole('MANAGER')">
                        <br/><a href="/projects/edit?id=${proj.id}">[Manage]</a>
                    </sec:authorize>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>