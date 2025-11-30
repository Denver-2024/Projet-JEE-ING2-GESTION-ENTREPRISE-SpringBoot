<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employee Management Dashboard</title>
    <style>
        body { font-family: sans-serif; padding: 20px; }
        .dashboard-grid { display: flex; gap: 20px; flex-wrap: wrap; }
        .card {
            border: 1px solid #ccc;
            padding: 20px;
            width: 300px;
            border-radius: 8px;
            box-shadow: 2px 2px 5px rgba(0,0,0,0.1);
        }
        .card h3 { margin-top: 0; border-bottom: 2px solid #eee; padding-bottom: 10px; }
        .card ul { padding-left: 20px; }
        .card li { margin-bottom: 8px; }
        .logout-btn {
            background: #d9534f; color: white; border: none; padding: 10px 15px;
            cursor: pointer; border-radius: 4px; float: right;
        }
        .header { overflow: hidden; margin-bottom: 30px; border-bottom: 1px solid #ddd; padding-bottom: 10px; }
    </style>
</head>
<body>

<div class="header">
    <form action="/logout" method="post" style="display:inline;">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit" class="logout-btn">Logout</button>
    </form>

    <h1>Welcome, <sec:authentication property="principal.firstName" />!</h1>
    <p>
        Logged in as: <b><sec:authentication property="principal.username" /></b> |
        Roles:
        <sec:authorize access="hasRole('ADMIN')"> <span style="color:red;">ADMIN</span> </sec:authorize>
        <sec:authorize access="hasRole('HR')"> <span style="color:blue;">HR</span> </sec:authorize>
        <sec:authorize access="hasRole('MANAGER')"> <span style="color:green;">MANAGER</span> </sec:authorize>
        <sec:authorize access="hasRole('EMPLOYEE')"> <span>EMPLOYEE</span> </sec:authorize>
    </p>
</div>

<div class="dashboard-grid">

    <div class="card">
        <h3>My Space</h3>
        <ul>
            <li><a href="/employees/profile">My Profile</a></li>
            <li><a href="/absences/my-absences">My Absence History</a></li>
            <li><a href="/bonuses/my-bonuses">My Bonuses</a></li>
            <li><a href="/projects/my-projects">My Team's Projects</a></li>
            <li><a href="/payroll/my-payslip">Generate My Payslip</a></li>
            <li><a href="/employees/change-password">Change Password</a></li>
        </ul>
    </div>

    <div class="card">
        <h3>Company Directory</h3>
        <ul>
            <li><a href="/employees">Employee Directory</a></li>
            <li><a href="/departments">Departments List</a></li>
            <li><a href="/projects">Projects Overview</a></li>
        </ul>
    </div>

    <sec:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
        <div class="card" style="border-top: 5px solid green;">
            <h3>Management</h3>
            <ul>
                <li><a href="/departments/my-department">My Department Details</a></li>
                <li><a href="/projects">Manage Projects</a></li>
            </ul>
        </div>
    </sec:authorize>

    <sec:authorize access="hasAnyRole('HR', 'ADMIN')">
        <div class="card" style="border-top: 5px solid blue;">
            <h3>Human Resources</h3>
            <ul>
                <li><a href="/employees/edit">Register New Employee</a></li>
                <li><a href="/absences">Manage Absences</a></li>
                <li><a href="/bonuses">Manage Bonuses</a></li>
                <li><a href="/payroll/generate">Run Payroll</a></li>
                <li><a href="/payroll/deductions">Payroll Configuration</a></li>
            </ul>
        </div>
    </sec:authorize>

    <sec:authorize access="hasRole('ADMIN')">
        <div class="card" style="border-top: 5px solid red;">
            <h3>Administration</h3>
            <ul>
                <li><a href="/admin/users">Manage Users & Roles</a></li>
                <li><a href="/departments/edit">Create Department</a></li>
                <li><a href="/employees">Delete/Edit Employees</a></li>
            </ul>
        </div>
    </sec:authorize>

</div>

</body>
</html>