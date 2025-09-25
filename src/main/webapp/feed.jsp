<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.studinsta.model.User"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.studinsta.util.DBConnection"%>
<html>
<head>
    <title>Feed - StudInsta</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <script src="js/script.js"></script>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
%>
    <h2>Feed</h2>
    <p>Logged in as: <b><%= user.getName() %></b> | <a href="post.jsp">New Post</a></p>
    <hr/>

<%
    // Fetch posts
    try (Connection con = DBConnection.getConnection()) {
        String postQuery = "SELECT p.id, p.image_url, p.caption, u.name FROM posts p JOIN users u ON p.user_id = u.id ORDER BY p.created_at DESC";
        PreparedStatement ps = con.prepareStatement(postQuery);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            int postId = rs.getInt("id");
            String imageUrl = rs.getString("image_url");
            String caption = rs.getString("caption");
            String author = rs.getString("name");
%>
    <div style="border:1px solid #ccc; padding:10px; margin-bottom:20px;">
        <p><b><%= author %></b></p>
        <img src="<%= imageUrl %>" width="300"/><br/>
        <p><%= caption %></p>

        <!-- Comment form -->
        <form action="comment" method="post">
            <input type="hidden" name="postId" value="<%= postId %>"/>
            <input type="text" name="content" placeholder="Add a comment" required/>
            <button type="submit">Comment</button>
        </form>

        <!-- Display comments -->
        <div style="margin-left:20px; margin-top:10px;">
        <%
            String commentQuery = "SELECT c.content, u.name FROM comments c JOIN users u ON c.user_id = u.id WHERE c.post_id=? ORDER BY c.created_at ASC";
            PreparedStatement cps = con.prepareStatement(commentQuery);
            cps.setInt(1, postId);
            ResultSet crs = cps.executeQuery();
            while(crs.next()) {
                String commentContent = crs.getString("content");
                String commentAuthor = crs.getString("name");
        %>
            <p><b><%= commentAuthor %></b>: <%= commentContent %></p>
        <%
            }
        %>
        </div>
    </div>
<%
        }
    } catch(Exception e) {
        out.println("<p>Error loading feed.</p>");
        e.printStackTrace();
    }
%>
</body>
</html>
