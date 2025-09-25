<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up - StudInsta</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <script src="js/script.js"></script>
</head>
<body>
    <h2>Sign Up</h2>
        <label>Email:</label><br/>
        <input type="email" name="email" required/><br/><br/>
        <label>Password:</label><br/>
        <input type="password" name="password" required/><br/><br/>
        <button type="submit">Sign Up</button>
    </form>
    <br/>
    <p>Already have an account? <a href="login.jsp">Login here</a></p>
    <% if(request.getParameter("error") != null) { %>
        <p style="color:red;">Signup failed. Email may already exist.</p>
    <% } %>
</body>
</html>
