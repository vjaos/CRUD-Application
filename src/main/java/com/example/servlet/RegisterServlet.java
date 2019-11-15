package com.example.servlet;

import com.example.entity.User;
import com.example.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/RegistrationForm.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            boolean isSaved = userService.save(new User(firstName, lastName, password, username));
            if(isSaved){
                resp.sendRedirect("/");
            }else {
                resp.sendRedirect("/register");
            }
        }catch (SQLException e){
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }
}
