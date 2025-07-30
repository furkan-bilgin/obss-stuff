<%@ page import="java.util.Random" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sample JSP</title>
</head>
<body>
    <% String name = "Furkan"; %>
    <h1>Benim adım <u><%= name %></u>!</h1>
    <% int numberCount = new Random().nextInt(10); %>
    <p>Rakam sayısı: <%= numberCount %></p>
    <ul>
    <% for (int i = 1; i <= numberCount; i++) { %>
        <li>Rakam: <%= i %></li>
    <% } %>
    </ul>
</body>
</html>
