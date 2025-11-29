<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<body>
<h1>Report Absence</h1>
<form action="/hr/absences/save" method="post">
    <input type="hidden" name="id" value="${absence.id}" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    Employee:
    <select name="employee.id" required>
        <c:forEach items="${employees}" var="emp">
            <option value="${emp.id}" ${absence.employee.id == emp.id ? 'selected' : ''}>
                    ${emp.firstName} ${emp.lastName}
            </option>
        </c:forEach>
    </select><br/>

    Start Date: <input type="date" name="startDate" value="${absence.startDate}" required /><br/>
    End Date: <input type="date" name="endDate" value="${absence.endDate}" /> (Leave empty if ongoing)<br/>

    Reason: <textarea name="reason">${absence.reason}</textarea><br/>

    Expected?
    <input type="checkbox" name="expected" value="true" ${absence.expected ? 'checked' : ''} /> Yes<br/>

    Paid Leave?
    <input type="checkbox" name="paidLeave" value="true" ${absence.paidLeave ? 'checked' : ''} /> Yes<br/>

    <button type="submit">Save</button>
</form>
</body>
</html>