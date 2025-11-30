<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Payroll Deductions</title>
</head>
<body>
<div style="border-bottom: 1px solid #ccc; margin-bottom: 10px;">
    <a href="/">Dashboard</a> |
    <a href="/payroll/generate">Generate Payslip</a>
</div>

<h1>Global Payroll Deductions</h1>
<p>These taxes and fees are applied to <b>every</b> payslip generated.</p>

<sec:authorize access="hasAnyRole('HR', 'ADMIN')">
    <p><a href="/payroll/deductions/edit"><b>+ Create New Deduction</b></a></p>
</sec:authorize>

<table border="1" cellpadding="5" style="width: 100%;">
    <thead>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Type</th>
        <th>Amount / Value</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${deductions}" var="d">
        <tr>
            <td><b>${d.name}</b></td>
            <td>${d.description}</td>
            <td>${d.type}</td>
            <td>
                <c:choose>
                    <c:when test="${d.type == 'FIXED'}">
                        <fmt:formatNumber value="${d.amount}" type="currency" currencySymbol="EUR"/>
                    </c:when>
                    <c:when test="${d.type == 'PERCENTAGE'}">
                        ${d.amount}%
                    </c:when>
                </c:choose>
            </td>
            <td>
                <sec:authorize access="hasAnyRole('HR', 'ADMIN')">
                    <a href="/payroll/deductions/edit?id=${d.id}">Edit</a>
                    <a href="/payroll/deductions/delete/${d.id}" onclick="return confirm('Delete this deduction?');">Delete</a>
                </sec:authorize>
                <sec:authorize access="!hasAnyRole('HR', 'ADMIN')">
                    <span style="color:#aaa;">View Only</span>
                </sec:authorize>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:if test="${empty deductions}">
    <p>No global deductions configured.</p>
</c:if>
</body>
</html>