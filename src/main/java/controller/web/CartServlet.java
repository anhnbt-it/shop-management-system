package controller.web;

import dao.OrderDao;
import dao.ProductDao;
import model.admin.Customer;
import model.entities.Order;
import model.entities.OrderDetail;
import model.entities.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "CartServlet", urlPatterns = "/cart")
public class CartServlet extends HttpServlet {
    private ProductDao productDao = new ProductDao();
    private OrderDao orderDao = new OrderDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doAddAjax(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                doAddAjax(request, response);
                break;
            case "payment":
                doInsertOrder(request, response);
                break;
            case "view":
                showCart(request, response);
                break;
            case "del":
                delOrderDetail(request, response);
                break;
            default:
                goPage(request, response);
        }

    }

    protected void goPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        if (page.equals("product")) {
            request.getRequestDispatcher("/products").forward(request, response);
        } else if (page.equals("home")) {
            request.getRequestDispatcher("/home").forward(request, response);
        } else {
            request.getRequestDispatcher("/products").forward(request, response);
        }
    }

//    protected void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if (request.getParameter("id") != null) {
//            int id = Integer.parseInt(request.getParameter("id"));
//            int quantity = 1;
//            Product product = productDao.selectProduct(id);
//            if (product != null) {
//                if (request.getParameter("quantity") != null) {
//                    quantity = Integer.parseInt(request.getParameter("quantity"));
//                }
//            }
//            HttpSession session = request.getSession();
//            if (session.getAttribute("order") == null) {
//                double sum = 0;
//                Order order = new Order();
//                List<OrderDetail> listOrderDetails = new ArrayList<>();
//                OrderDetail orderDetail = new OrderDetail();
//                orderDetail.setProduct(product);
//                orderDetail.setQuantity(quantity);
//                orderDetail.setPrice(product.getRealPrice());
//                orderDetail.setTotal(product.getRealPrice() * quantity);
//                listOrderDetails.add(orderDetail);
//                for (OrderDetail orderDetail1 : listOrderDetails) {
//                    sum += orderDetail1.getTotal();
//                }
//                order.setTotalOrder(sum);
//                order.setList(listOrderDetails);
//                session.setAttribute("order", order);
//            } else {
//                double sum = 0;
//                Order order = (Order) session.getAttribute("order");
//                List<OrderDetail> listOrderDetails = order.getList();
//                boolean checkExist = false;
//
//                for (OrderDetail orderDetail : listOrderDetails) {
//                    if (orderDetail.getProduct().getId() == product.getId()) {
//                        System.err.println("So luong stock: " + product.getQuantity());
//                        orderDetail.setQuantity(orderDetail.getQuantity() + 1);
//                        orderDetail.setTotal(product.getRealPrice() * orderDetail.getQuantity());
//                        checkExist = true;
//
//                    }
//                }
//                if (!checkExist) {
//                    OrderDetail orderDetail = new OrderDetail();
//                    orderDetail.setQuantity(quantity);
//                    orderDetail.setProduct(product);
//                    orderDetail.setPrice(product.getRealPrice());
//                    orderDetail.setTotal(product.getRealPrice() * orderDetail.getQuantity());
//                    listOrderDetails.add(orderDetail);
//                    order.setList(listOrderDetails);
//                }
//                for (OrderDetail orderDetail1 : listOrderDetails) {
//                    sum += orderDetail1.getTotal();
//                }
//                order.setTotalOrder(sum);
//                session.setAttribute("order", order);
//            }
//        }
//        goPage(request, response);
//    }


    protected void doAddAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            int quantity = 1;
            Product product = productDao.selectProduct(id);
            if (product != null) {
                if (request.getParameter("quantity") != null) {
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                }
            }
            if (session.getAttribute("order") == null) {
                double sum = 0;
                Order order = new Order();
                List<OrderDetail> listOrderDetails = new ArrayList<>();
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(product);
                orderDetail.setQuantity(quantity);
                orderDetail.setPrice(product.getRealPrice());
                orderDetail.setTotal(product.getRealPrice() * quantity);
                listOrderDetails.add(orderDetail);
                for (OrderDetail orderDetail1 : listOrderDetails) {
                    sum += orderDetail1.getTotal();
                }
                order.setTotalOrder(sum);
                order.setList(listOrderDetails);
                session.setAttribute("order", order);
            } else {
                double sum = 0;
                Order order = (Order) session.getAttribute("order");
                List<OrderDetail> listOrderDetails = order.getList();
                boolean checkExist = false;

                for (OrderDetail orderDetail : listOrderDetails) {
                    if (orderDetail.getProduct().getId() == product.getId()) {
                        System.err.println("So luong stock: " + product.getQuantity());
                        orderDetail.setQuantity(orderDetail.getQuantity() + 1);
                        orderDetail.setTotal(product.getRealPrice() * orderDetail.getQuantity());
                        checkExist = true;
                    }
                }
                if (!checkExist) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setQuantity(quantity);
                    orderDetail.setProduct(product);
                    orderDetail.setPrice(product.getRealPrice());
                    orderDetail.setTotal(product.getRealPrice() * orderDetail.getQuantity());
                    listOrderDetails.add(orderDetail);
                    order.setList(listOrderDetails);
                }
                for (OrderDetail orderDetail1 : listOrderDetails) {
                    sum += orderDetail1.getTotal();
                }
                order.setTotalOrder(sum);
                session.setAttribute("order", order);
            }
        }
        response.setContentType("text/plain");
        PrintWriter writer = response.getWriter();
        Order order = (Order) session.getAttribute("order");
        writer.print(order.getList().size());
    }

    protected void doInsertOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        ArrayList<Product> products = productDao.selectAllProduct();
        Customer customer = null;
        boolean canInsert = true;
        if (session.getAttribute("user")==null) {
            System.err.println("Phai dang nhap");
            canInsert = false;
        } else {
            customer = (Customer) session.getAttribute("user");
        }
        for (OrderDetail orderDetail: order.getList()) {
            int id = orderDetail.getProduct().getId();
            Product product = productDao.selectProduct(id);
            if (orderDetail.getQuantity()>product.getQuantity()) {
                System.err.println("Stock da het hang");
                canInsert=false;
            }
        }
         if(canInsert) {
             try {
                 order.setCustomer_id(customer.getId());
                 orderDao.insertOrder(order);
                 int orderId = orderDao.selectTopIdOrder().getId();
                 for (OrderDetail orderDetail: order.getList()) {
                     int id = orderDetail.getProduct().getId();
                     Product product = productDao.selectProduct(id);
                     int newQuantityInStock = product.getQuantity()-orderDetail.getQuantity();
                     product.setQuantity(newQuantityInStock);
                    boolean isSuccess = orderDao.updateProductQuantity(product);
                    System.out.println("Status:" + isSuccess);
                    orderDao.insertOrderDetail(orderId, id, orderDetail.getQuantity(), product.getRealPrice());
                 }
                 session.removeAttribute("order");
                 System.err.println("Dat hang thanh cong");

             } catch (SQLException throwables) {
                 System.err.println("Dat hang that bai");
                 throwables.printStackTrace();
             }
         }
//        Customer customer= (Customer) session.getAttribute("user");
//        if (customer!=null && order!=null) {
//            order.setCustomer_id(customer.getId());
//            try {
//                orderDao.insertOrder(order);
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
        request.getRequestDispatcher("/home").forward(request, response);
    }

    protected void showCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        request.getRequestDispatcher("/web/cart.jsp").forward(request, response);
    }

    protected void delOrderDetail(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        int id = Integer.parseInt(request.getParameter("id"));
        for (int i = 0; i<order.getList().size(); i++) {
            if (id == order.getList().get(i).getProduct().getId()) {
                double totalOrder = order.getTotalOrder() -  order.getList().get(i).getTotal();
                order.setTotalOrder(totalOrder);
                order.getList().remove(order.getList().get(i));
            }
        }
        session.setAttribute("order", order);
        try {
            request.getRequestDispatcher("/web/cart.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
