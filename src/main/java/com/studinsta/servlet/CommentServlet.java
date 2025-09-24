package com.studinsta.servlet;

import com.studinsta.dao.CommentDAO;
import com.studinsta.model.Comment;
import com.studinsta.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
    private CommentDAO commentDAO = new CommentDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int postId = Integer.parseInt(request.getParameter("postId"));
        String content = request.getParameter("content");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Comment comment = new Comment(postId, user.getId(), content);
        if (commentDAO.saveComment(comment)) {
            response.sendRedirect("feed.jsp");
        } else {
            response.sendRedirect("feed.jsp?error=1");
        }
    }
}
