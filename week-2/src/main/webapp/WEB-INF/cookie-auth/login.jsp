<%--
  Created by IntelliJ IDEA.
  User: furkan
  Date: 30/07/2025
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="post">
        <div>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div>
            <button type="submit">Login</button>
        </div>
        <div>
            <p style="color: red;">
                <% if (request.getAttribute("errorMessage") != null) { %>
                    <%= request.getAttribute("errorMessage")  %>
                <% } %>
            </p>
        </div>
    </form>
</body>
</html>
