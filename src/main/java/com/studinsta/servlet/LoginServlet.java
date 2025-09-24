package com.studinsta.servlet;

import com.studinsta.dao.UserDAO;
import com.studinsta.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userDAO.loginUser(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user); // store user in session
            response.sendRedirect("feed.jsp");   // redirect to feed page
        } else {
            response.sendRedirect("login.jsp?error=1");
        }
    }
}
