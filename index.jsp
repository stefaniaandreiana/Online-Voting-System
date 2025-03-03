<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<html>
<head>
    <title>Votează Acum!</title>
</head>
<body>
    <h1>Votul tău contează! Alege un partid:</h1>

    <form action="vote" method="post">
        <label for="party">Alege un partid:</label>
        <select name="party_id" id="party" required>
            <c:forEach var="party" items="${parties}">
                <option value="${party.party_id}">${party.name}</option>
            </c:forEach>
        </select>
        <button type="submit">Votează</button>
    </form>
</body>
</html>
