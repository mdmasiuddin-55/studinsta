<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.studinsta.model.User" %>
<%
    User user = (User) session.getAttribute("user");
%>
<html>
<head>
    <title>New Post - StudInsta</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <script src="js/script.js"></script>
</head>
<body>
    <h2>Create a New Post</h2>
        <textarea name="caption" rows="3" cols="30"></textarea><br/><br/>
        <label>Image:</label><br/>
        <input type="file" name="image" accept="image/*" required/><br/><br/>
        <button type="submit">Upload Post</button>
    </form>
    <% if(request.getParameter("error") != null) { %>
        <p style="color:red;">Failed to upload post.</p>
    <% } %>
</body>
</html>
