<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<body>
<h1>Edit/Create Department</h1>
<form action="/db-test/departments/save" method="post">
    <input type="hidden" name="id" value="${department.id}" />

    Name: <input type="text" name="name" value="${department.name}" required /><br/>
    Description: <textarea name="description">${department.description}</textarea><br/>

    Head Employee (Required):
    <select name="head.id" required>
        <c:forEach items="${allEmployees}" var="emp">
            <option value="${emp.id}" ${department.head.id == emp.id ? 'selected' : ''}>
                    ${emp.firstName} ${emp.lastName} (ID: ${emp.id})
            </option>
        </c:forEach>
    </select><br/>

    <button type="submit">Save</button>
</form>
</body>
</html>