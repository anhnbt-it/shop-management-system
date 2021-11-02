package com.codegym.controller;

import com.codegym.dao.ProductDao;
import com.codegym.dao.CategoryDao;
import com.codegym.model.entities.Categories;
import com.codegym.model.entities.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private ProductDao productDao= new ProductDao();
    private CategoryDao categoryDao=new CategoryDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        if(action==null){
            action="";
        }
        switch (action){
            case "search":
                searchProduct(request, response);
                break;
            case "order":
                sortProduct(request, response);
                break;
        }
    }



    private void sortProduct(HttpServletRequest request, HttpServletResponse response) {
        String sortByName = request.getParameter("sortByName");
        String sortByPrice = request.getParameter("sortByPrice");

        System.out.println(1);
        ArrayList<Product> products = null;
        String a = request.getParameter("idCategory");
        System.out.println("alo" + a);
        if (request.getParameter("idCategory").equals("")) {

            if (sortByName.equals("1")) {
                products =productDao.sort_Product_N_A();

            } else if (sortByName.equals("2")) {
                products = productDao.sort_Product_N_D();

            } else if (sortByPrice.equals("3")) {
                products =productDao.sort_Product_P_D();


            } else if (sortByPrice.equals("4")) {
                products =productDao.sort_Product_P_A();

            } else {
                products = productDao.selectAllProduct();
            }

        } else {
            int categoryId = 0;
            if (request.getParameter("idCategory")!=null) {
                categoryId = Integer.parseInt(request.getParameter("idCategory"));
            }

            if (sortByName.equals("1")) {
                products = productDao.sort_Product_Cate_NA(categoryId);

            } else if (sortByName.equals("2")) {
                products = productDao.sort_Product_Cate_ND(categoryId);

            } else if (sortByPrice.equals("3")) {
                products = productDao.sort_Product_Cate_PD(categoryId);

            } else if (sortByPrice.equals("4")) {
                products = productDao.sort_Product_Cate_PA(categoryId);
            } else {
                products = productDao.select_by_ID_Category(categoryId);
            }
        }
//        request.setAttribute("idCategory", request.getParameter("idCategory"));
        request.setAttribute("products", products);
        goProductJSP(request,response);

//        listProductSorts(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action==null) {
            action="";
        }
        switch (action) {
            case "view":
                showProductDetail(request, response);
                break;
            default:
                listProducts(request, response);
        }

    }

//    public void listProductSorts(HttpServletRequest request, HttpServletResponse response) {
//        int categoryId = Integer.parseInt(request.getParameter("idCategory"));
//        System.out.println(1);
//        ArrayList<Product> products = productDao.selectAllProductSort(categoryId);
//        request.setAttribute("products", products);
//
//        goProductJSP(request,response);
////        RequestDispatcher rd = request.getRequestDispatcher("web/product.jsp");
////        try {
////            rd.forward(request, response);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//
//    }

    public void listProducts(HttpServletRequest request, HttpServletResponse response) {
//        int categoryId = Integer.parseInt(request.getParameter("idCategory"));
        ArrayList<Product> products;

        if (request.getParameter("idCategory")==null) {
           products = productDao.selectAllProduct();
        } else {
            int categoryId = Integer.parseInt(request.getParameter("idCategory"));
            products = productDao.select_by_ID_Category(categoryId);
        }
        request.setAttribute("idCategory", request.getParameter("idCategory"));
        request.setAttribute("products", products);

        goProductJSP(request,response);

    }
    public void showProductDetail(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDao.selectProduct(id);
        ArrayList<Product> products= productDao.selectRelatedProduct(product.getCategory_id(), id);
        List<Categories> categoriesDog=categoryDao.selectCatorDog(1);
        request.setAttribute("categoriesDog",categoriesDog);
        List<Categories> categoriesCat=categoryDao.selectCatorDog(2);
        request.setAttribute("categoriesCat",categoriesCat);
        request.setAttribute("product", product);
        request.setAttribute("products", products);
        RequestDispatcher rd = request.getRequestDispatcher("web/productDetail.jsp");
        try {
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void searchProduct(HttpServletRequest request, HttpServletResponse response){
        String searchValue = request.getParameter("searchproduct");
        ArrayList<Product> products=productDao.search_Products(searchValue);
        request.setAttribute("products",products);
        goProductJSP(request,response);
    }


    public void goProductJSP(HttpServletRequest request, HttpServletResponse response){
        List<Categories> categoriesDog=categoryDao.selectCatorDog(1);
        request.setAttribute("categoriesDog",categoriesDog);
        List<Categories> categoriesCat=categoryDao.selectCatorDog(2);
        request.setAttribute("categoriesCat",categoriesCat);
        try{
            request.getRequestDispatcher("/web/product.jsp").forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
