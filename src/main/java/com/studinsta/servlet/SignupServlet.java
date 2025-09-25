package com.studinsta.servlet;

import com.studinsta.dao.UserDAO;
import com.studinsta.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(username, email, password);

        if (userDAO.registerUser(user)) {
            response.sendRedirect("login.jsp"); // redirect to login after successful signup
        } else {
            response.sendRedirect("signup.jsp?error=1");
        }
    }
}
