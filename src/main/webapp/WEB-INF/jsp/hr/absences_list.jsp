<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<body>
<h1>Manage Absences</h1>
<a href="/">Home</a> |
<sec:authorize access="hasAnyRole('HR', 'ADMIN')">
    <a href="/hr/absences/edit">Report New Absence</a>
</sec:authorize>
| <a href="/hr/bonuses">Go to Bonuses</a>

<table border="1" cellpadding="5">
    <tr>
        <th>Employee</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Reason</th>
        <th>Expected?</th>
        <th>Paid?</th>
        <th>Last Update</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${absences}" var="abs">
        <tr>
            <td>${abs.employee.firstName} ${abs.employee.lastName}</td>
            <td>${abs.startDate}</td>
            <td>${abs.endDate != null ? abs.endDate : "<b>Ongoing</b>"}</td>
            <td>${abs.reason}</td>
            <td>${abs.expected ? "Yes" : "No"}</td>
            <td>${abs.paidLeave ? "Yes" : "No"}</td>
            <td>${abs.lastUpdate}</td>
            <td>
                <sec:authorize access="hasAnyRole('HR', 'ADMIN')">
                    <a href="/hr/absences/edit?id=${abs.id}">Edit</a>
                    <a href="/hr/absences/delete/${abs.id}">Delete</a>
                </sec:authorize>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>