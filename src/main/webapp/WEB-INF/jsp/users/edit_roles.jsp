<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<body>
<h1>Manage Roles for ${employee.firstName} ${employee.lastName}</h1>

<form action="/db-test/users/save-roles" method="post">
    <input type="hidden" name="employeeId" value="${employee.id}" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <h3>Check roles to assign:</h3>
    <c:forEach items="${allRoles}" var="role">
        <c:set var="hasRole" value="false" />
        <c:forEach items="${employee.roles}" var="empRole">
            <c:if test="${empRole.name eq role.name}">
                <c:set var="hasRole" value="true" />
            </c:if>
        </c:forEach>

        <div>
            <input type="checkbox" name="roleNames" value="${role.name}"
                ${hasRole ? 'checked' : ''} />
                ${role.name}
        </div>
    </c:forEach>
    <br/>
    <button type="submit">Save Roles</button>
</form>
<br/>
<a href="/db-test/users">Cancel</a>
</body>
</html>