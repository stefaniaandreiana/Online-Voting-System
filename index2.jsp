<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Rezultatele Alegerilor</title>
</head>
<body>
    <h1>Rezultatele Alegerilor</h1>

    <table>
        <tr>
            <th>Partid</th>
            <th>Număr Voturi</th>
        </tr>
        <c:forEach var="party" items="${parties}">
            <tr>
                <td>${party.name}</td>
                <td>${party.voteCount}</td>
            </tr>
        </c:forEach>
    </table>

    <script>
        setInterval(function() {
            location.reload(); // Reîncarcă pagina pentru a vedea actualizările în timp real
        }, 5000); // Actualizează la fiecare 5 secunde
    </script>
</body>
</html>
