package com.example.servlet;


import com.example.entity.Stuff;
import com.example.entity.User;
import com.example.service.StuffService;
import com.example.service.UserService;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;


public class CRUDServlet extends HttpServlet {
    private StuffService stuffService = StuffService.getInstance();
    private UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mapping = req.getServletPath();
        try {
            switch (mapping) {
                case "/new":
                    showNewForm(req, resp);
                    break;
                case "/insert":
                    insertStuff(req, resp);
                    break;
                case "/edit":
                    showEditForm(req, resp);
                    break;
                case "/update":
                    doUpdate(req, resp);
                    break;
                case "/delete":
                    deleteStuff(req, resp);
                    break;
                default:
                    showListStuff(req, resp);
                    break;
            }
        } catch (SQLException sqle) {
            System.err.println("SQL Exception: " + sqle.getMessage());
        }

    }

    private void doUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        Stuff stuff = new Stuff(id, name, description, quantity);
        stuffService.update(stuff);
        resp.sendRedirect("list");
    }


    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Optional<Stuff> existingStuff = stuffService.find(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/StuffForm.jsp");
        existingStuff.ifPresent(s -> req.setAttribute("stuff", s));
        dispatcher.forward(req, resp);
    }

    private void deleteStuff(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        Stuff stuff = new Stuff(id);
        stuffService.delete(stuff);
        resp.sendRedirect("list");
    }


    private void insertStuff(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        Stuff stuff = new Stuff(name, description, quantity);
        stuffService.save(stuff);
        resp.sendRedirect("list");
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/StuffForm.jsp");
        dispatcher.forward(req, resp);
    }


    private void showListStuff(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/StuffList.jsp");
        List<Stuff> listStuff = stuffService.findAll();
        req.setAttribute("listStuff", listStuff);
        dispatcher.forward(req, resp);
    }

}
