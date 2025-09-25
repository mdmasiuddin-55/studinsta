<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.studinsta.model.User"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.studinsta.util.DBConnection"%>
<%
    User user = (User) session.getAttribute("user");
    if(user == null){
        response.sendRedirect("login.jsp");
    }
    int postId = Integer.parseInt(request.getParameter("postId"));
    Connection con = DBConnection.getConnection();
%>
<html>
<head>
    <title>Comments - StudInsta</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <script src="js/script.js"></script>
</head>
<body>
    PreparedStatement ps = con.prepareStatement(
        "SELECT p.id, p.image_url, p.caption, u.name FROM posts p JOIN users u ON p.user_id=u.id WHERE p.id=?"
    );
    ps.setInt(1, postId);
    ResultSet rs = ps.executeQuery();
    if(rs.next()){
        String author = rs.getString("name");
        String imageUrl = rs.getString("image_url");
        String caption = rs.getString("caption");
%>
    <h3>Post by <%= author %></h3>
    <img src="<%= imageUrl %>" width="400"/><br/>
    <p><%= caption %></p>
    <hr/>
    <!-- Comment Form -->
    <form action="comment" method="post">
        <input type="hidden" name="postId" value="<%= postId %>"/>
        <input type="text" name="content" placeholder="Add a comment" required/>
        <button type="submit">Comment</button>
    </form>
    <!-- Display comments -->
    <h4>Comments:</h4>
<%
        PreparedStatement cps = con.prepareStatement(
            "SELECT c.content, u.name FROM comments c JOIN users u ON c.user_id=u.id WHERE c.post_id=? ORDER BY c.created_at ASC"
        );
        cps.setInt(1, postId);
        ResultSet crs = cps.executeQuery();
        while(crs.next()){
            String commentAuthor = crs.getString("name");
            String commentContent = crs.getString("content");
%>
        <p><b><%= commentAuthor %></b>: <%= commentContent %></p>
<%
        }
    } else {
%>
    <p>Post not found.</p>
<%
    }
%>
    <p><a href="feed.jsp">Back to Feed</a></p>
</body>
</html>
