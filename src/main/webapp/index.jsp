<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.studinsta.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if(user != null){
        response.sendRedirect("feed.jsp"); // logged-in users go to feed
    }
%>
<html>
<head>
    <title>Welcome to StudInsta</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <script src="js/script.js"></script>
</head>
<body>
    <h1>Welcome to StudInsta</h1>
    <p>
        <a href="login.jsp">Login</a> | <a href="signup.jsp">Sign Up</a>
    </p>
</body>
</html>
