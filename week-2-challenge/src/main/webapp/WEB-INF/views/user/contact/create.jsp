<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create New Contact</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css">
</head>
<body>
<main class="container">
    <article>
        <hgroup>
            <h2>Add a New Contact</h2>
        </hgroup>
        <form method="post">
            <label for="contactName">Contact Name</label>
            <input type="text" id="contactName" name="contactName" placeholder="Enter contact name" required>

            <label for="contactNumber">Contact Number</label>
            <input type="text" id="contactNumber" name="contactNumber" placeholder="Enter contact number" required>

            <button type="submit">Add Contact</button>
        </form>
    </article>
</main>
</body>
</html>