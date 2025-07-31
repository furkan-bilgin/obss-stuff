<%@ page import="com.furkanbilgin.week2challange.models.Contact" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Contact</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css">
</head>
<body>
<main class="container">
    <article>
        <hgroup>
            <h2>Edit Contact</h2>
        </hgroup>
        <form method="post">
            <% var contact = (Contact)request.getAttribute("contact"); %>
            <label for="contactName">Contact Name</label>
            <input type="text" id="contactName" name="contactName" value="<%= contact.getContactName() %>" required>

            <label for="contactNumber">Contact Number</label>
            <input type="text" id="contactNumber" name="contactNumber" value="<%= contact.getContactNumber() %>" required>

            <button type="submit">Update Contact</button>
        </form>
    </article>
</main>
</body>
</html>