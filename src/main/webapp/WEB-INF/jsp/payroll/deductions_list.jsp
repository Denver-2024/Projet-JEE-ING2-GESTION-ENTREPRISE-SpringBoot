<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<body>
<h1>Payroll Configuration (Taxes & Spendings)</h1>
<a href="/">Home</a> |
<sec:authorize access="hasAnyRole('HR', 'ADMIN')">
    <a href="/payroll/deductions/edit">Create New Deduction</a>
</sec:authorize>
| <a href="/payroll/generate">Generate Payslip</a>

<table border="1" cellpadding="5">
    <tr>
        <th>Name</th>
        <th>Type</th>
        <th>Amount</th>
        <th>Description</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${deductions}" var="d">
        <tr>
            <td>${d.name}</td>
            <td>${d.type}</td>
            <td>${d.amount}</td>
            <td>${d.description}</td>
            <td>
                <sec:authorize access="hasAnyRole('HR', 'ADMIN')">
                    <a href="/payroll/deductions/edit?id=${d.id}">Edit</a>
                    <a href="/payroll/deductions/delete/${d.id}">Delete</a>
                </sec:authorize>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>