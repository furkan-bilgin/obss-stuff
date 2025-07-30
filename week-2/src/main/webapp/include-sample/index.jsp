<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index of Sample</title>
</head>
<body>
    <!-- include header.jsp statically -->
    <%@ include file="header.jsp" %>
    <!-- include message.jsp dynamically -->
    <jsp:include page="message.jsp" />
    <!-- get and print date from RequestDispatcher.include() -->
    <%
        var rd = request.getRequestDispatcher("/include-sample/time-servlet");
        rd.include(request, response);
        var date = request.getAttribute("date");
    %>
    Current date: <%= date %>

</body>
</html>
