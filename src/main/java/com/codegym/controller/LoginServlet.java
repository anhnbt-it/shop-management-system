package com.codegym.controller;

import com.codegym.dao.CustomerDao;
import com.codegym.model.admin.Customer;
import com.codegym.utilities.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "LoginServlet", urlPatterns = "/account/login")
public class LoginServlet extends HttpServlet {
    private CustomerDao customerDao;

    @Override
    public void init() {
        Connection conn = DBConnection.getConnection();
        customerDao = new CustomerDao(conn);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Customer customer = customerDao.getLoggedCustomer(email, password);
        if (customer != null) {
            session.setAttribute("user", customer);
            resp.sendRedirect("/home");
        } else {
            session.setAttribute("msg", "<div class=\"alert alert-danger\">Invalid username or password</div>");
            resp.sendRedirect("/account/login");
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null) {
            resp.sendRedirect("/home");
        } else {
            req.getRequestDispatcher("/web/account/login.jsp").forward(req, resp);
        }
    }
}