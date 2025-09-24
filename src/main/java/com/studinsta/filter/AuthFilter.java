package com.studinsta.filter;

import com.studinsta.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/feed.jsp", "/post.jsp", "/comment"})
public class AuthFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization if needed
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            // Not logged in, redirect to login page
            res.sendRedirect("login.jsp");
        } else {
            // User logged in, continue
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
        // Cleanup if needed
    }
}
