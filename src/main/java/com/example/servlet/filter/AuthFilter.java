package com.example.servlet.filter;

import com.example.entity.User;
import com.example.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import static java.util.Objects.nonNull;


public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req  = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserService userService  = UserService.getInstance();
        HttpSession session = req.getSession();
        try {
            if (nonNull(session) && nonNull(session.getAttribute("user"))) {
                resp.sendRedirect("list");
            } else if (nonNull(username) && nonNull(password)){
                Optional<User> user = userService.find(username, password);
                if(user.isPresent()){
                    req.getSession().setAttribute("user", user);
                    resp.sendRedirect("list");
                }else {
                    resp.sendRedirect("/login");
                }
            }else {
                req.getRequestDispatcher("WEB-INF/jsp/Login.jsp").forward(req, resp);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {
    }
}
