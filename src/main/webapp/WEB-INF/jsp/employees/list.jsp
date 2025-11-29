<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<html>
<body>
<h1>Employees DB Test</h1>
<a href="/db-test/employees/edit">Create New Employee</a> |
<a href="/db-test/departments">Go to Departments</a> |
<a href="/db-test/projects">Go to Projects</a>

<h3>Search Filters (Match All)</h3>
<form action="/db-test/employees" method="get">
    First Name: <input type="text" name="firstName" /><br/>
    Last Name: <input type="text" name="lastName" /><br/>
    Email: <input type="text" name="email" /><br/>
    Type:
    <select name="type">
        <option value="">-- All --</option>
        <option value="JUNIOR">JUNIOR</option>
        <option value="INTERMEDIATE">INTERMEDIATE</option>
        <option value="SENIOR">SENIOR</option>
    </select><br/>
    <button type="submit">Search</button>
    <a href="/db-test/employees">Clear</a>
</form>

<hr/>
<table border="1">
    <tr>
        <th>ID</th><th>First Name</th><th>Last Name</th><th>Email</th><th>Type</th><th>Dept</th><th>Actions</th>
    </tr>
    <c:forEach items="${employees}" var="emp">
        <tr>
            <td>${emp.id}</td>
            <td>${emp.firstName}</td>
            <td>${emp.lastName}</td>
            <td>${emp.email}</td>
            <td>${emp.type}</td>
            <td>${emp.department.name}</td>
            <td>
                <a href="/db-test/employees/edit?id=${emp.id}">Edit</a>
                <a href="/db-test/employees/delete/${emp.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>