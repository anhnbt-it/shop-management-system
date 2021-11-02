package controller.web.account;

import dao.DBConnection;
import dao.CustomerDao;
import model.admin.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RegisterServlet", urlPatterns = "/account/register")
public class RegisterServlet extends HttpServlet {
    private CustomerDao customerDao;

    @Override
    public void init() {
        Connection conn = DBConnection.getConnection();
        customerDao = new CustomerDao(conn);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        List<String> message = new ArrayList<>();

        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        int gender = Integer.parseInt(req.getParameter("gender"));

        String password = req.getParameter("password");
        String confirm = req.getParameter("confirm");

        if (!confirm.equals(password)) {
            message.add("Confirm password doesn't match");
        }
        if (customerDao.checkExistEmail(email)) {
            message.add("Email is already exists");
        }
        if (message.size() == 0) {
            Customer customer = new Customer();
            customer.setName(name);
            customer.setPhone(phone);
            customer.setEmail(email);
            customer.setAddress(address);
            customer.setGender(gender);
            customer.setPassword(password);
            customer.setStatus(1);

            if (customerDao.add(customer)) {
                session.setAttribute("msg", "<div class=\"alert alert alert-success\">Your Account Has Been Created!. <br>Congratulations! Your new account has been successfully created!</div>");
            } else {
                session.setAttribute("msg", "<div class=\"alert alert alert-danger\">INSERT fails.</div>");
            }
        } else {
            StringBuilder errors = new StringBuilder("<div class=\"alert alert-danger\">\n" +
                    "<ul>\n");
            for (String msg: message) {
                errors.append("<li>").append(msg).append("</li>\n");
            }
            errors.append("</ul>\n" + "</div>");
            session.setAttribute("msg", errors.toString());
        }
        resp.sendRedirect("/account/register");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null) {
            resp.sendRedirect("/home");
        } else {
            req.getRequestDispatcher("/web/account/register.jsp").forward(req, resp);
        }
    }
}
