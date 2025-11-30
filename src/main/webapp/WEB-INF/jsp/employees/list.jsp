<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Employee Directory</title>
    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>

    <script>
        $(document).ready(function() {
            $('.searchable-select').select2({
                placeholder: "Select an option",
                allowClear: true,
                width: '200px'
            });
        });
    </script>
</head>
<body>

<div style="border-bottom: 1px solid #ccc; margin-bottom: 10px;">
    <a href="/">Dashboard</a> |
    <a href="/employees/profile">My Profile</a> |
    <form action="/logout" method="post" style="display:inline;">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit">Logout</button>
    </form>
</div>

<h1>Employee Directory</h1>

<div style="background: #f0f0f0; padding: 10px; margin-bottom: 20px;">
    <h3>Search Filters</h3>
    <form action="/employees" method="get">
        <input type="text" name="query" value="${currentQuery}" placeholder="Name or Email..." />

        Department:
        <select name="department.id" class="searchable-select">
            <option value="">-- All --</option>
            <c:forEach items="${departments}" var="dept">
                <option value="${dept.id}" ${currentDeptId == dept.id ? 'selected' : ''}>
                        ${dept.name}
                </option>
            </c:forEach>
        </select>

        <button type="submit">Search</button>
        <a href="/employees">Clear</a>
    </form>
</div>


<sec:authorize access="hasAnyRole('ADMIN', 'HR')">
    <p><a href="/employees/edit"><b>+ Register New Employee</b></a></p>
</sec:authorize>

<table border="1" cellpadding="5" style="width: 100%;">
    <thead>
    <tr>
        <th>Name</th>
        <th>Department</th>
        <th>Email</th>
        <th>Role/Type</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${employees}" var="emp">
        <tr>
            <td>
                    ${emp.firstName} ${emp.lastName}
            </td>
            <td>${emp.departmentName}</td>
            <td><a href="mailto:${emp.email}">${emp.email}</a></td>
            <td>${emp.type}</td>
            <td>
                <sec:authorize access="hasAnyRole('ADMIN', 'HR')">
                    <a href="/payroll/generate?employeeId=${emp.id}">[Payslip]</a>
                </sec:authorize>

                <sec:authorize access="hasAnyRole('ADMIN', 'HR')">
                    <a href="/employees/edit?id=${emp.id}">Edit</a>
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN')">
                    <a href="/employees/delete/${emp.id}" onclick="return confirm('Are you sure?');">Delete</a>
                </sec:authorize>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:if test="${empty employees}">
    <p>No employees found matching criteria.</p>
</c:if>
</body>
</html>