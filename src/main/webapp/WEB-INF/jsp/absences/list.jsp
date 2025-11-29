<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>
<div style="border-bottom: 1px solid #ccc; margin-bottom: 10px;">
    <a href="/">Dashboard</a> |
    <a href="/absences/my-absences">My History</a>
    <sec:authorize access="hasAnyRole('HR', 'ADMIN')">
        | <a href="/absences">All Absences</a>
    </sec:authorize>
</div>

<h1>${pageTitle}</h1>

<sec:authorize access="hasAnyRole('HR', 'ADMIN')">
    <p><a href="/absences/edit"><b>+ Report New Absence</b></a></p>
</sec:authorize>

<table border="1" cellpadding="5" style="width: 100%;">
    <thead>
    <tr>
        <th>Employee</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Reason</th>
        <th>Paid?</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${absences}" var="abs">
        <tr>
            <td>${abs.employee.firstName} ${abs.employee.lastName}</td>
            <td>${abs.startDate}</td>
            <td>${abs.endDate != null ? abs.endDate : "<b>Ongoing</b>"}</td>
            <td>${abs.reason}</td>
            <td>${abs.paidLeave ? "Yes" : "No"}</td>
            <td>${abs.expected ? "Expected" : "Unexpected"}</td>
            <td>
                <sec:authorize access="hasAnyRole('HR', 'ADMIN')">
                    <a href="/absences/edit?id=${abs.id}">Edit</a>
                    <a href="/absences/delete/${abs.id}" onclick="return confirm('Delete?');">Delete</a>
                </sec:authorize>
                <sec:authorize access="!hasAnyRole('HR', 'ADMIN')">
                    <span style="color:#aaa;">View Only</span>
                </sec:authorize>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>