<%@ page import="com.furkanbilgin.week2challange.models.Contact" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: furkan
  Date: 31/07/2025
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Index Page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css">
</head>
<body>

    <main class="container">
        <h1>Welcome!</h1>
        <form method="get">
            <% var searchQuery = request.getParameter("search"); %>
            <input type="text" name="search" placeholder="Search..." value="<%= searchQuery != null ? searchQuery : "" %>" />
        </form>
        <ul>
            <li><a href="contact/create" class="button">Add New Contact</a></li>
            <li><a href="logout">Logout</a></li>
        </ul>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Contact Name</th>
                    <th>Contact Number</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% for (var contact : (List<Contact>)request.getAttribute("contacts")) { %>
                    <tr>
                        <td><%= contact.getId() %></td>
                        <td><%= contact.getContactName() %></td>
                        <td><%= contact.getContactNumber() %></td>
                        <td>
                            <a href="contact/edit?id=<%= contact.getId() %>">Edit</a>
                            <a href="contact/delete?id=<%= contact.getId() %>">Delete</a>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </main>
</body>
</html>
