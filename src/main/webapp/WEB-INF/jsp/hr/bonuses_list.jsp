<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<body>
<h1>Award Bonus</h1>
<form action="/hr/bonuses/save" method="post">
    <input type="hidden" name="id" value="${bonus.id}" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    Employee:
    <select name="employee.id" required>
        <c:forEach items="${employees}" var="emp">
            <option value="${emp.id}" ${bonus.employee.id == emp.id ? 'selected' : ''}>
                    ${emp.firstName} ${emp.lastName}
            </option>
        </c:forEach>
    </select><br/>

    Amount: <input type="number" step="0.01" name="amount" value="${bonus.amount}" required /><br/>

    Reason: <textarea name="reason">${bonus.reason}</textarea><br/>

    Award Date (Month matters): <input type="date" name="awardDate" value="${bonus.awardDate}" required /><br/>

    <button type="submit">Save</button>
</form>
</body>
</html>