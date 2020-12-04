package controller.admin;

import dao.admin.ProductDAO;
import model.admin.Customer;
import model.admin.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AdminProductServlet",urlPatterns = "/administrator/products")
public class AdminProductServlet extends HttpServlet {
    private int currentPage =1;
    private int recordsPerPage= 4;
    ProductDAO productDAO = new ProductDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = (request.getParameter("act") !=null) ? request.getParameter("act") : "";
        switch (action){
            case "store":
                createProduct(request,response);
                break;
            case "update":
                updateProduct(request,response);
                break;
            case "search":
                searchProduct(request,response);
                break;
        }

    }

    private void searchProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("query");
        double numberOfRows = productDAO.getNumberOfRows();

        if (req.getParameter("currentPage") != null && req.getParameter("recordsPerPage") != null) {
            currentPage = Integer.parseInt(req.getParameter("currentPage"));
            recordsPerPage = Integer.parseInt(req.getParameter("recordsPerPage"));
        }

        double countPages = Math.ceil(numberOfRows / recordsPerPage);

        List<Product> products = productDAO.findByName(search, currentPage, recordsPerPage);
        extractProduct(req, resp, currentPage, products.size(), recordsPerPage, (int) countPages, products);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Product product = getProduct(request);
        HttpSession session = request.getSession();
        try {
            if (productDAO.edit(product)){
                System.out.println("thanh cong");
                session.setAttribute("msg","<div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">\n" +
                        "  <strong>Success!</strong> Edit done.\n" +
                        "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                        "    <span aria-hidden=\"true\">&times;</span>\n" +
                        "  </button>\n" +
                        "</div>");
            }else{
                System.out.println("co loi xay ra");
                session.setAttribute("msg","<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\">\n" +
                        "  <strong>Error!</strong> Try again.\n" +
                        "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                        "    <span aria-hidden=\"true\">&times;</span>\n" +
                        "  </button>\n" +
                        "</div>");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        response.sendRedirect("/administrator/products?act=edit&id="+product.getId());
    }

    private Product getProduct(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int category = Integer.parseInt(request.getParameter("category"));
        String desc= request.getParameter("desc");
        double price = Double.parseDouble(request.getParameter("price"));
        int qty = Integer.parseInt(request.getParameter("qty"));
        double discount = Double.parseDouble(request.getParameter("discount"));
        int status = Integer.parseInt(request.getParameter("status"));
        String img= "huutho.jpg";
        Product product= new Product(id,name, category,desc, qty,price,discount,img,status);
        return product;
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        int category = Integer.parseInt(request.getParameter("category"));
        String desc =request.getParameter("desc");
        double price = Double.parseDouble(request.getParameter("price"));
        int qty = Integer.parseInt(request.getParameter("qty"));
        double discount = Double.parseDouble(request.getParameter("discount"));
        int status = Integer.parseInt(request.getParameter("status"));
        String img= "huutho.jpg";
        Product product= new Product(name, category, desc, qty,price,discount,img,status);
        try {
            if (productDAO.add(product)){
                System.out.println("thanh cong");
                request.setAttribute("msg","<div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">\n" +
                        "  <strong>Success!</strong> Create done.\n" +
                        "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                        "    <span aria-hidden=\"true\">&times;</span>\n" +
                        "  </button>\n" +
                        "</div>");
                request.getRequestDispatcher("/admin/product/create.jsp").forward(request,response);
            }else{
                System.out.println("co loi xay ra");
                request.setAttribute("msg","<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\">\n" +
                        "  <strong>Error!</strong> Try again.\n" +
                        "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                        "    <span aria-hidden=\"true\">&times;</span>\n" +
                        "  </button>\n" +
                        "</div>");
                request.getRequestDispatcher("/admin/product/create.jsp").forward(request,response);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = (request.getParameter("act") !=null ) ? request.getParameter("act") : "";
        switch (action){
            case "create":
                showCreateProduct(request,response);
                break;
            case "edit":
                try {
                    showEditProduct(request,response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "delete":
                try {
                    deleteProduct(request,response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            default:
                try {
                    showListProduct(request,response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
        }


    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDAO.findById(id);
        if(product != null){
            if(productDAO.remove(id)){
                session.setAttribute("msg","<div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">\n" +
                        "  <strong>Success!</strong> Delete done.\n" +
                        "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                        "    <span aria-hidden=\"true\">&times;</span>\n" +
                        "  </button>\n" +
                        "</div>");
            }else{
                session.setAttribute("msg","<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\">\n" +
                        "  <strong>Error!</strong> Try again.\n" +
                        "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                        "    <span aria-hidden=\"true\">&times;</span>\n" +
                        "  </button>\n" +
                        "</div>");
            }
        }else{
            session.setAttribute("msg","<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\">\n" +
                    "  <strong>Error!</strong> Try again.\n" +
                    "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                    "    <span aria-hidden=\"true\">&times;</span>\n" +
                    "  </button>\n" +
                    "</div>");
        }
        response.sendRedirect("/administrator/products");
    }

    private void showEditProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();

        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDAO.findById(id);
        if(product!=null){
            request.setAttribute("product",product);
            request.getRequestDispatcher("/admin/product/edit.jsp").forward(request,response);

        }else {
            session.setAttribute("msg","<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\">\n" +
                    "  <strong>Success!</strong> No product.\n" +
                    "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                    "    <span aria-hidden=\"true\">&times;</span>\n" +
                    "  </button>\n" +
                    "</div>");
            response.sendRedirect("/administrator/products");
        }
    }

//    private void showListProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int currentPage = (request.getParameter("currentPage") != null) ? Integer.parseInt(request.getParameter("currentPage")) : 1;
//        int recordsPerPage = (request.getParameter("recordsPerPage") != null) ? Integer.parseInt(request.getParameter("recordsPerPage")) : 4;
//        List<Product> productList = null;
//        try {
//            productList = productDAO.getRecords(currentPage,recordsPerPage);
//            System.out.println(productList);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        request.setAttribute("products",productList);
//        int rows = productDAO.getNumberOfRows();
//        int nOfPages = (int) Math.floor(rows / recordsPerPage);
//        request.setAttribute("countUsers", rows);
//        request.setAttribute("noOfPages", nOfPages);
//        request.setAttribute("currentPage", currentPage);
//        request.setAttribute("recordsPerPage", recordsPerPage);
//        request.getRequestDispatcher("/admin/product/index.jsp").forward(request,response);
//    }
public void showListProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
    double numberOfRows = productDAO.getNumberOfRows();

    if (req.getParameter("currentPage") != null && req.getParameter("recordsPerPage") != null) {
         currentPage = Integer.parseInt(req.getParameter("currentPage"));
         recordsPerPage = Integer.parseInt(req.getParameter("recordsPerPage"));
    }

    double countPages = Math.ceil(numberOfRows / recordsPerPage);
    List<Product> products = productDAO.getRecords(currentPage, recordsPerPage);
    System.out.println(products);
    extractProduct(req, resp, currentPage, products.size(), recordsPerPage, (int) countPages, products);
}

    private void extractProduct(HttpServletRequest req, HttpServletResponse resp, int currentPage, int countProducts, int recordsPerPage, int countPages, List<Product> products) throws ServletException, IOException {
        req.setAttribute("products", products);
        req.setAttribute("countPages", countPages);
        req.setAttribute("countProducts", countProducts);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("recordsPerPage", recordsPerPage);
        req.getRequestDispatcher("/admin/product/index.jsp").forward(req, resp);
    }


    private void showCreateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/admin/product/create.jsp").forward(request,response);

    }

}
