<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<body>
<h1>Projects DB Test</h1>
<a href="/db-test/projects/edit">Create New Project</a> |
<a href="/db-test/employees">Back to Employees</a>

<h3>Search Filters</h3>
<form action="/db-test/projects" method="get">
    Name: <input type="text" name="name" /><br/>
    State:
    <select name="state">
        <option value="">-- All --</option>
        <option value="IN_PROGRESS">IN_PROGRESS</option>
        <option value="CANCELED">CANCELED</option>
        <option value="FINISHED">FINISHED</option>
    </select><br/>
    <button type="submit">Search</button>
</form>

<hr/>
<table border="1">
    <tr>
        <th>ID</th><th>Name</th><th>State</th><th>Department</th><th>Actions</th>
    </tr>
    <c:forEach items="${projects}" var="proj">
        <tr>
            <td>${proj.id}</td>
            <td>${proj.name}</td>
            <td>${proj.state}</td>
            <td>${proj.department.name}</td>
            <td>
                <a href="/db-test/projects/edit?id=${proj.id}">Edit</a>
                <a href="/db-test/projects/delete/${proj.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>