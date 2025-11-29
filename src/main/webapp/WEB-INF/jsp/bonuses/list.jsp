<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>
<div style="border-bottom: 1px solid #ccc; margin-bottom: 10px;">
    <a href="/">Dashboard</a> |
    <a href="/bonuses/my-bonuses">My Bonuses</a>
    <sec:authorize access="hasAnyRole('HR', 'ADMIN')">
        | <a href="/bonuses">All Bonuses</a>
    </sec:authorize>
</div>

<h1>${pageTitle}</h1>

<sec:authorize access="hasAnyRole('HR', 'ADMIN')">
    <p><a href="/bonuses/edit"><b>+ Award New Bonus</b></a></p>
</sec:authorize>

<table border="1" cellpadding="5" style="width: 100%;">
    <thead>
    <tr>
        <th>Employee</th>
        <th>Award Date</th>
        <th>Reason</th>
        <th>Amount</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${bonuses}" var="bonus">
        <tr>
            <td>${bonus.employee.firstName} ${bonus.employee.lastName}</td>
            <td>${bonus.awardDate}</td>
            <td>${bonus.reason}</td>
            <td>
                <fmt:formatNumber value="${bonus.amount}" type="currency" currencySymbol="EUR"/>
            </td>
            <td>
                <sec:authorize access="hasAnyRole('HR', 'ADMIN')">
                    <a href="/bonuses/edit?id=${bonus.id}">Edit</a>
                    <a href="/bonuses/delete/${bonus.id}" onclick="return confirm('Delete?');">Delete</a>
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