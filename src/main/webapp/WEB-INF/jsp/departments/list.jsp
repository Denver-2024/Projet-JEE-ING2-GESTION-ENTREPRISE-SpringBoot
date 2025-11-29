<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<body>
<h1>Departments DB Test</h1>
<a href="/db-test/departments/edit">Create New Department</a> |
<a href="/db-test/employees">Back to Employees</a>

<h3>Search Filters</h3>
<form action="/db-test/departments" method="get">
    Name: <input type="text" name="name" /><br/>
    <button type="submit">Search</button>
    <a href="/db-test/departments">Clear</a>
</form>

<hr/>
<table border="1">
    <tr>
        <th>ID</th><th>Name</th><th>Head</th><th>Actions</th>
    </tr>
    <c:forEach items="${departments}" var="dept">
        <tr>
            <td>${dept.id}</td>
            <td>${dept.name}</td>
            <td>${dept.head.firstName} ${dept.head.lastName}</td>
            <td>
                <a href="/db-test/departments/edit?id=${dept.id}">Edit</a>
                <a href="/db-test/departments/delete/${dept.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>