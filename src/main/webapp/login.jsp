<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login - StudInsta</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <script src="js/script.js"></script>
</head>
<body>
    <h2>Login</h2>
        <label>Password:</label><br/>
        <input type="password" name="password" required/><br/><br/>
        <button type="submit">Login</button>
    </form>
    <br/>
    <p>Don't have an account? <a href="signup.jsp">Sign up here</a></p>
    <% if(request.getParameter("error") != null) { %>
        <p style="color:red;">Invalid email or password.</p>
    <% } %>
</body>
</html>
