<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<body>
<h1>
    Welcome,
    <sec:authentication property="principal.firstName" />
    <sec:authentication property="principal.lastName" />!
</h1>

<p>Logged in as ID: <sec:authentication property="principal.username"/></p>

<h3>Your Roles:</h3>
<ul>
    <sec:authorize access="hasRole('ADMIN')"><li>ADMINISTRATOR</li></sec:authorize>
    <sec:authorize access="hasRole('HR')"><li>HR</li></sec:authorize>
    <sec:authorize access="hasRole('MANAGER')"><li>DEPARTMENT HEAD</li></sec:authorize>
    <sec:authorize access="hasRole('EMPLOYEE')"><li>EMPLOYEE</li></sec:authorize>
</ul>

<hr/>
<h3>Navigation</h3>
<ul>
    <li><a href="/db-test/employees">Manage Employees</a></li>
    <li><a href="/db-test/departments">Manage Departments</a></li>
    <li><a href="/db-test/projects">Manage Projects</a></li>

    <sec:authorize access="hasRole('ADMIN')">
        <li><a href="/db-test/users">Admin: User & Role Management</a></li>
    </sec:authorize>
</ul>

<hr/>
<form action="/logout" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button type="submit">Logout</button>
</form>
</body>
</html>