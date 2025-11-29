<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<body>
<h1>Manage Bonuses</h1>
<a href="/">Home</a> |
<sec:authorize access="hasAnyRole('HR', 'ADMIN')">
    <a href="/hr/bonuses/edit">Award New Bonus</a>
</sec:authorize>
| <a href="/hr/absences">Go to Absences</a>

<table border="1" cellpadding="5">
    <tr>
        <th>Employee</th>
        <th>Amount</th>
        <th>Reason</th>
        <th>Award Date</th>
        <th>Last Update</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${bonuses}" var="bonus">
        <tr>
            <td>${bonus.employee.firstName} ${bonus.employee.lastName}</td>
            <td>${bonus.amount}</td>
            <td>${bonus.reason}</td>
            <td>${bonus.awardDate}</td>
            <td>${bonus.lastUpdate}</td>
            <td>
                <sec:authorize access="hasAnyRole('HR', 'ADMIN')">
                    <a href="/hr/bonuses/edit?id=${bonus.id}">Edit</a>
                    <a href="/hr/bonuses/delete/${bonus.id}">Delete</a>
                </sec:authorize>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>