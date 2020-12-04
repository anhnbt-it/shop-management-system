package controller.admin;

import dao.CustomerDao;
import dao.DBConnection;
import dao.admin.OrderDao;
import model.admin.Customer;
import model.entities.Order;

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

@WebServlet(name = "OrderServlet", urlPatterns = "/administrator/orders")
public class OrderServlet extends HttpServlet {
    private CustomerDao customerDao;
    private OrderDao order;
    private HttpSession session;
    private int currentPage;
    private int recordsPerPage;
    @Override
    public void init() {
        Connection conn = DBConnection.getConnection();
        order = new OrderDao(conn);

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
//                    case "create":
//                        createCustomer(req, resp);
//                        break;
//                    case "store":
//                        storeCustomer(req, resp);
//                        break;
                    case "edit":
                        editOrder(req, resp);
                        break;
                    case "update":
                        updateOrder(req, resp);
                        break;
                    case "delete":
                        deleteOrder(req, resp);
                        break;
                    case "search":
                        searchOrder(req, resp);
                        break;

                    default:
                        showAllOrders(req, resp);
                }
            }
        } catch (ServletException | IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double numberOfRows = order.getNumberOfRows();

        if (req.getParameter("currentPage") != null && req.getParameter("recordsPerPage") != null) {
            currentPage = Integer.parseInt(req.getParameter("currentPage"));
            recordsPerPage = Integer.parseInt(req.getParameter("recordsPerPage"));
        }

        double countPages = Math.ceil(numberOfRows / recordsPerPage);
        List<Order> orders = this.order.getRecords(currentPage, recordsPerPage);
//        for (Order order:orders) {
//            int id = order.getCustomer_id();
////            String name = ;
//            order.setCustomerName(customerDao.findById(id).getName());
//        }
        extractOrder(req, resp, currentPage, orders.size(), recordsPerPage, (int) countPages, orders);
    }
    private void extractOrder(HttpServletRequest req, HttpServletResponse resp, int currentPage, int countOrders, int recordsPerPage, int countPages, List<Order> orders) throws ServletException, IOException {
        req.setAttribute("orders", orders);
        req.setAttribute("countPages", countPages);
        req.setAttribute("countOrders", countOrders);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("recordsPerPage", recordsPerPage);
        req.getRequestDispatcher("/admin/order/index.jsp").forward(req, resp);
    }

    private void editOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session = req.getSession();
        int id = Integer.parseInt(req.getParameter("id"));
        Order order = this.order.findById(id);
        if (order == null) {
            session.setAttribute("msg", "<div class=\"alert alert alert-danger\">Order not found.</div>");
            resp.sendRedirect("/administrator/orders?act=index");
        } else {
            req.setAttribute("order", order);
            req.getRequestDispatcher("/admin/order/edit.jsp").forward(req, resp);
        }
    }
    private void updateOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        session = req.getSession();
        List<String> message = new ArrayList<>();
        int id = Integer.parseInt(req.getParameter("id"));
        Order order = new Order();
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        order.setId(id);
        order.setOrderDate(timestamp);
        order.setStatus(req.getParameter("status"));
        order.setComment(req.getParameter("comments"));
        order.setCustomer_id(Integer.parseInt(req.getParameter("customer")));

        if (message.size() == 0) {
            if (this.order.edit(order)) {
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
        resp.sendRedirect("/administrator/orders?act=edit&id=" + id);
    }

    private void deleteOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        session = req.getSession();
        int id = Integer.parseInt(req.getParameter("id"));
        Order order = this.order.findById(id);
        if (order == null) {
            session.setAttribute("msg", "<div class=\"alert alert alert-danger\">Order not found.</div>");
        } else {
            if (this.order.remove(id)) {
                session.setAttribute("msg", "<div class=\"alert alert alert-success\">Record deleted successfully.</div>");
            } else {
                session.setAttribute("msg", "<div class=\"alert alert alert-danger\">Cannot delete or update a parent row: a foreign key constraint fails.</div>");
            }
        }
        resp.sendRedirect("/administrator/orders?act=index");
    }
    private void searchOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("query");
        double numberOfRows = this.order.getNumberOfRows();

        if (req.getParameter("currentPage") != null && req.getParameter("recordsPerPage") != null) {
            currentPage = Integer.parseInt(req.getParameter("currentPage"));
            recordsPerPage = Integer.parseInt(req.getParameter("recordsPerPage"));
        }

        double countPages = Math.ceil(numberOfRows / recordsPerPage);

        List<Order> orders = this.order.findByName(search, currentPage, recordsPerPage);
        extractOrder(req, resp, currentPage, orders.size(), recordsPerPage, (int) countPages, orders);
    }

}
