<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<body>
<h1>Edit/Create Employee</h1>
<form action="/db-test/employees/save" method="post">
    <input type="hidden" name="id" value="${employee.id}" />

    First Name: <input type="text" name="firstName" value="${employee.firstName}" required /><br/>
    Last Name: <input type="text" name="lastName" value="${employee.lastName}" required /><br/>
    Address: <input type="text" name="address" value="${employee.address}" /><br/>
    Email: <input type="email" name="email" value="${employee.email}" required /><br/>

    Gender: <input type="text" name="gender" value="${employee.gender}" /><br/>

    Type:
    <select name="type">
        <option value="JUNIOR" ${employee.type == 'JUNIOR' ? 'selected' : ''}>JUNIOR</option>
        <option value="INTERMEDIATE" ${employee.type == 'INTERMEDIATE' ? 'selected' : ''}>INTERMEDIATE</option>
        <option value="SENIOR" ${employee.type == 'SENIOR' ? 'selected' : ''}>SENIOR</option>
    </select><br/>

    Salary: <input type="number" step="0.01" name="salary" value="${employee.salary}" required /><br/>
    Password: <input type="password" name="password" value="${employee.password}" required /><br/>

    Department:
    <select name="department.id">
        <option value="">-- None --</option>
        <c:forEach items="${departments}" var="dept">
            <option value="${dept.id}" ${employee.department.id == dept.id ? 'selected' : ''}>
                    ${dept.name}
            </option>
        </c:forEach>
    </select><br/>

    <button type="submit">Save</button>
</form>
</body>
</html>