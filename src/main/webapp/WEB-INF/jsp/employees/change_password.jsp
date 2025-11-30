<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Change Password</title>
    <style>
        .error { color: red; font-size: 0.9em; }
        .form-group { margin-bottom: 15px; }
        label { display: block; font-weight: bold; margin-bottom: 5px; }
    </style>
</head>
<body>
<div style="border-bottom: 1px solid #ccc; margin-bottom: 10px;">
    <a href="/">Dashboard</a> | <a href="/employees/profile">My Profile</a>
</div>

<h1>Change Password</h1>

<form:form method="post" action="/employees/change-password" modelAttribute="passwordForm">

    <div class="form-group">
        <label>Current Password:</label>
        <form:password path="oldPassword" />
        <form:errors path="oldPassword" cssClass="error" />
    </div>

    <div class="form-group">
        <label>New Password:</label>
        <form:password path="newPassword" />
        <form:errors path="newPassword" cssClass="error" />
    </div>

    <div class="form-group">
        <label>Confirm New Password:</label>
        <form:password path="confirmPassword" />
        <form:errors path="confirmPassword" cssClass="error" />
    </div>

    <button type="submit">Update Password</button>
    <a href="/employees/profile" style="margin-left: 10px;">Cancel</a>
</form:form>

</body>
</html>