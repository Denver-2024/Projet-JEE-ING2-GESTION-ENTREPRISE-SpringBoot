<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<body>
<h1>Edit/Create Project</h1>
<form action="/db-test/projects/save" method="post">
    <input type="hidden" name="id" value="${project.id}" />

    Name: <input type="text" name="name" value="${project.name}" required /><br/>
    Description: <textarea name="description">${project.description}</textarea><br/>

    State:
    <select name="state">
        <option value="IN_PROGRESS" ${project.state == 'IN_PROGRESS' ? 'selected' : ''}>IN_PROGRESS</option>
        <option value="CANCELED" ${project.state == 'CANCELED' ? 'selected' : ''}>CANCELED</option>
        <option value="FINISHED" ${project.state == 'FINISHED' ? 'selected' : ''}>FINISHED</option>
    </select><br/>

    Department (Required):
    <select name="department.id" required>
        <c:forEach items="${departments}" var="dept">
            <option value="${dept.id}" ${project.department.id == dept.id ? 'selected' : ''}>
                    ${dept.name}
            </option>
        </c:forEach>
    </select><br/>

    <button type="submit">Save</button>
</form>
</body>
</html>