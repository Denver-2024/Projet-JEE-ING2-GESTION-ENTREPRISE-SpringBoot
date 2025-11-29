<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>${empty projectForm.id ? 'Create' : 'Edit'} Project</title>

    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>

    <script>
        $(document).ready(function() {
            $('.searchable-select').select2({ width: '300px' });
        });
    </script>

    <style>
        .error { color: red; font-size: 0.9em; margin-left: 10px; }
        label { font-weight: bold; margin-top: 10px; display: block; }
        .form-group { margin-bottom: 15px; }
    </style>
</head>
<body>
<div style="border-bottom: 1px solid #ccc; margin-bottom: 10px; padding-bottom: 5px;">
    <a href="/">Dashboard</a> | <a href="/projects">Back to Projects List</a>
</div>

<h1>${empty projectForm.id ? 'Create New' : 'Edit'} Project</h1>

<form:form method="post" action="/projects/save" modelAttribute="projectForm">
    <form:hidden path="id" />

    <div class="form-group">
        <label>Project Name:</label>
        <form:input path="name" style="width: 300px;" />
        <form:errors path="name" cssClass="error" />
    </div>

    <div class="form-group">
        <label>Description:</label>
        <form:textarea path="description" rows="4" style="width: 300px;" />
    </div>

    <div class="form-group">
        <label>Status:</label>
        <form:select path="state" style="width: 300px; padding: 5px;">
            <form:option value="IN_PROGRESS">In Progress</form:option>
            <form:option value="FINISHED">Finished</form:option>
            <form:option value="CANCELED">Canceled</form:option>
        </form:select>
        <form:errors path="state" cssClass="error" />
    </div>

    <div class="form-group">
        <label>Assigned Department:</label>
        <form:select path="departmentId" cssClass="searchable-select">
            <form:option value="">-- Select a Department --</form:option>
            <c:forEach items="${departments}" var="dept">
                <form:option value="${dept.id}">${dept.name}</form:option>
            </c:forEach>
        </form:select>
        <form:errors path="departmentId" cssClass="error" />
    </div>

    <br/>
    <button type="submit" style="padding: 10px 20px; cursor: pointer;">Save Project</button>
    <a href="/projects" style="margin-left: 10px;">Cancel</a>
</form:form>

</body>
</html>