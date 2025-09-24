package com.studinsta.servlet;

import com.studinsta.dao.PostDAO;
import com.studinsta.model.Post;
import com.studinsta.model.User;
import com.studinsta.util.S3Util;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;

@WebServlet("/post")
@MultipartConfig
public class PostServlet extends HttpServlet {
    private PostDAO postDAO = new PostDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("image");
        String caption = request.getParameter("caption");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Save file temporarily
        String fileName = filePart.getSubmittedFileName();
        File tempFile = File.createTempFile("upload-", fileName);
        filePart.write(tempFile.getAbsolutePath());

        // Upload to S3
        String imageUrl = S3Util.uploadFile(tempFile);

        // Save to DB
        Post post = new Post(user.getId(), imageUrl, caption);
        if (imageUrl != null && postDAO.savePost(post)) {
            response.sendRedirect("feed.jsp");
        } else {
            response.sendRedirect("post.jsp?error=1");
        }

        tempFile.delete();
    }
}
