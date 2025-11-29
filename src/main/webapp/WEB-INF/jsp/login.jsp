<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login - Employee Portal</title>
</head>
<body>
<h1>Login</h1>

<c:if test="${not empty param.error}">
    <div style="color:red;">
        Invalid ID or Password.
    </div>
</c:if>

<c:if test="${not empty param.logout}">
    <div style="color:green;">
        You have been logged out.
    </div>
</c:if>

<form action="/perform_login" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div>
        <label>Employee ID:</label>
        <input type="text" name="username" required autofocus/>
    </div>
    <br/>
    <div>
        <label>Password:</label>
        <input type="password" name="password" required/>
    </div>
    <br/>
    <button type="submit">Sign In</button>
</form>

</body>
</html>