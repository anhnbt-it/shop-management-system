package controller.admin;

import dao.admin.CategoryDAO;
import model.admin.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdminCategoryServlet",urlPatterns = "/administrator/categories")
public class AdminCategoryServlet extends HttpServlet {
    CategoryDAO categoryDAO = new CategoryDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = (request.getParameter("act") !=null) ? request.getParameter("act") : "";
        switch (action){
            case "create2":
                createCategory(request, response);
                break;
            case "edit2":
                updateCategory(request,response);
                break;
            case "update2":
                getFormUpdate(request,response);
                break;
        }
    }

    private void    getFormUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CategoryDAO categoryDAO = new CategoryDAO();
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String desc = request.getParameter("desc");
        String img = request.getParameter("img");
        int type_id = Integer.parseInt(request.getParameter("type_id"));
        Category category = new Category(id,name,desc,img,type_id);
        System.out.println(category.toString());
        try {
            if(categoryDAO.edit(category)){
                System.out.println("thanh cong");
                session.setAttribute("msg","<div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">\n" +
                        "  <strong>Success!</strong> Edit done.\n" +
                        "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                        "    <span aria-hidden=\"true\">&times;</span>\n" +
                        "  </button>\n" +
                        "</div>");
            }else{
                System.out.println("that bai");
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
        response.sendRedirect("/administrator/categories");

    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response) {
        Category category = getCategory(request);
        try {
            categoryDAO.edit(category);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            request.getRequestDispatcher("/administrator/category").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private Category getCategory(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String desc = request.getParameter("desc");
        String img = request.getParameter("img");
        int type_id = Integer.parseInt(request.getParameter("type_id"));
        Category category = new Category(id,name,desc,img,type_id);
        return category;
    }

    private void createCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> listcategory = new ArrayList<>();
        HttpSession session = request.getSession();
        CategoryDAO categoryDAO = new CategoryDAO();
        String name = request.getParameter("name");
        String desc = request.getParameter("desc");
        String img = request.getParameter("img");
        int type_id = Integer.parseInt(request.getParameter("type_id"));
        Category category = new Category(name,desc,img,type_id);
        System.out.println(category.toString());
        try {
            if(categoryDAO.add(category)){
                System.out.println("thanh cong");
                listcategory=categoryDAO.getRecords(1,4);
                System.out.println(listcategory.size());
                session.setAttribute("msg","<div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">\n" +
                        "  <strong>Success!</strong> Add done.\n" +
                        "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                        "    <span aria-hidden=\"true\">&times;</span>\n" +
                        "  </button>\n" +
                        "</div>");
                request.setAttribute("listcategory",listcategory);
                request.getRequestDispatcher("/admin/category/index2.jsp").forward(request, response);
            }else{
                System.out.println("that bai");
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
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = (request.getParameter("act") !=null) ? request.getParameter("act") : "";
        switch (action){
            case "create":
                request.getRequestDispatcher("/admin/category/create2.jsp").forward(request,response);
                break;
            case "edit2":
                int id = Integer.parseInt(request.getParameter("id"));
                try {
                    Category category = categoryDAO.findById(id);
                    if(category==null){
                        System.out.println("ko ton tai");
                    }else{
                        request.setAttribute("category",category);
                        request.getRequestDispatcher("/admin/category/edit2.jsp").forward(request,response);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "delete2":
                try {
                    deleteCategory(request,response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            default:
                CategoryDAO categoryDAO = new CategoryDAO();
                List<Category> listcategory= null;
                try {
                    listcategory = categoryDAO.getRecords(1,4);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                System.out.println(listcategory.size());
                request.setAttribute("listcategory",listcategory);
                request.getRequestDispatcher("/admin/category/index2.jsp").forward(request,response);
        }
        System.out.println(action);
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = categoryDAO.findById(id);
        if(category !=null){
            if(categoryDAO.remove(id)){
                System.out.println("thanh cong");
                session.setAttribute("msg","<div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">\n" +
                        "  <strong>Success!</strong> Delete done.\n" +
                        "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                        "    <span aria-hidden=\"true\">&times;</span>\n" +
                        "  </button>\n" +
                        "</div>");

            }else{
                System.out.println("that bai");
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
        response.sendRedirect("/administrator/categories");
    }
}
