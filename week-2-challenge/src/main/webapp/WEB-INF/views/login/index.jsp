<%--
  Created by IntelliJ IDEA.
  User: furkan
  Date: 31/07/2025
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css">
</head>
<body>
<main class="container">
    <article>
        <form method="post" action="">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required/>
            <br/>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required/>
            <br/>
            <button type="submit">Login</button>
        </form>
        <% if (request.getAttribute("errorMessage") != null) { %>
        <p style="color: red;">
            <%= request.getAttribute("errorMessage") %>
        </p>
        <% } %>
    </article>
</main>
</body>
</html>
