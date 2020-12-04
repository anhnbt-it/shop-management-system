package controller.admin;

import dao.CustomerDao;
import dao.DBConnection;
import model.admin.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = "/administrator/customers")
public class CustomerServlet extends HttpServlet {
    private CustomerDao customerDao;
    private HttpSession session;
    private int currentPage;
    private int recordsPerPage;

    @Override
    public void init() {
        Connection conn = DBConnection.getConnection();
        customerDao = new CustomerDao(conn);

        currentPage = 1;
        recordsPerPage = 15;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) {
        session = req.getSession();
        try {
            if (session.getAttribute("admin") == null) {
                resp.sendRedirect("/administrator/login");
            } else {
                String action = (req.getParameter("act") != null) ? req.getParameter("act") : "";
                switch (action) {
                    case "create":
                        createCustomer(req, resp);
                        break;
                    case "store":
                        storeCustomer(req, resp);
                        break;
                    case "edit":
                        editCustomer(req, resp);
                        break;
                    case "update":
                        updateCustomer(req, resp);
                        break;
                    case "delete":
                        deleteCustomer(req, resp);
                        break;
                    case "search":
                        searchCustomer(req, resp);
                        break;

                    default:
                        showAllCustomers(req, resp);
                }
            }
        } catch (SQLException | ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void createCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/admin/customer/create.jsp").forward(req, resp);
    }

    private void storeCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        session = req.getSession();
        List<String> message = new ArrayList<>();

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        int gender = Integer.parseInt(req.getParameter("gender"));

        String password = req.getParameter("password");
        String confirm = req.getParameter("confirm");

        int status = Integer.parseInt(req.getParameter("status"));

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
            customer.setStatus(status);

            if (customerDao.add(customer)) {
                session.setAttribute("msg", "<div class=\"alert alert alert-success\">New customer created successfully.</div>");
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
        resp.sendRedirect("/administrator/customers?act=create");
    }

    private void editCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session = req.getSession();
        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = customerDao.findById(id);
        if (customer == null) {
            session.setAttribute("msg", "<div class=\"alert alert alert-danger\">Customer not found.</div>");
            resp.sendRedirect("/administrator/customers?act=index");
        } else {
            req.setAttribute("customer", customer);
            req.getRequestDispatcher("/admin/customer/edit.jsp").forward(req, resp);
        }
    }

    private void updateCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        session = req.getSession();
        List<String> message = new ArrayList<>();

        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(req.getParameter("name"));
        customer.setEmail(req.getParameter("email"));
        customer.setPhone(req.getParameter("phone"));
        customer.setAddress(req.getParameter("address"));
        customer.setGender(Integer.parseInt(req.getParameter("gender")));
        customer.setStatus(Integer.parseInt(req.getParameter("status")));
        customer.setPassword(req.getParameter("password"));

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        customer.setUpdated(timestamp);
        if (customer.getPassword().length() > 0 && req.getParameter("confirm").length() > 0) {
            if (!customer.getPassword().equals(req.getParameter("confirm"))) {
                message.add("Confirm password doesn't match");
            }
        }
        Customer oldCustomer = customerDao.findById(id);
        if (customerDao.checkExistEmail(customer.getEmail()) && !oldCustomer.getEmail().equals(customer.getEmail())) {
            message.add("Email is already exists");
        }
        if (message.size() == 0) {
            if (customerDao.edit(customer)) {
                session.setAttribute("msg", "<div class=\"alert alert alert-success\">Record updated successfully.</div>");
            } else {
                session.setAttribute("msg", "<div class=\"alert alert alert-danger\">UPDATE fails.</div>");
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
        resp.sendRedirect("/administrator/customers?act=edit&id=" + id);
    }

    private void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        session = req.getSession();
        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = customerDao.findById(id);
        if (customer == null) {
            session.setAttribute("msg", "<div class=\"alert alert alert-danger\">Customer not found.</div>");
        } else {
            if (customerDao.remove(id)) {
                session.setAttribute("msg", "<div class=\"alert alert alert-success\">Record deleted successfully.</div>");
            } else {
                session.setAttribute("msg", "<div class=\"alert alert alert-danger\">Cannot delete or update a parent row: a foreign key constraint fails.</div>");
            }
        }
        resp.sendRedirect("/administrator/customers?act=index");
    }

    public void showAllCustomers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double numberOfRows = customerDao.getNumberOfRows();

        if (req.getParameter("currentPage") != null && req.getParameter("recordsPerPage") != null) {
            currentPage = Integer.parseInt(req.getParameter("currentPage"));
            recordsPerPage = Integer.parseInt(req.getParameter("recordsPerPage"));
        }

        double countPages = Math.ceil(numberOfRows / recordsPerPage);
        List<Customer> customers = customerDao.getRecords(currentPage, recordsPerPage);
        extractCustomer(req, resp, currentPage, customers.size(), recordsPerPage, (int) countPages, customers);
    }

    private void extractCustomer(HttpServletRequest req, HttpServletResponse resp, int currentPage, int countCustomers, int recordsPerPage, int countPages, List<Customer> customers) throws ServletException, IOException {
        req.setAttribute("customers", customers);
        req.setAttribute("countPages", countPages);
        req.setAttribute("countCustomers", countCustomers);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("recordsPerPage", recordsPerPage);
        req.getRequestDispatcher("/admin/customer/index.jsp").forward(req, resp);
    }

    private void searchCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("query");
        double numberOfRows = customerDao.getNumberOfRows();

        if (req.getParameter("currentPage") != null && req.getParameter("recordsPerPage") != null) {
            currentPage = Integer.parseInt(req.getParameter("currentPage"));
            recordsPerPage = Integer.parseInt(req.getParameter("recordsPerPage"));
        }

        double countPages = Math.ceil(numberOfRows / recordsPerPage);

        List<Customer> customers = customerDao.findByName(search, currentPage, recordsPerPage);
        extractCustomer(req, resp, currentPage, customers.size(), recordsPerPage, (int) countPages, customers);
    }
}
