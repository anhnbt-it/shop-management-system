package com.codegym.controller;

import com.codegym.dao.ProductDao;
import com.codegym.dao.CategoryDao;
import com.codegym.model.entities.Categories;
import com.codegym.model.entities.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    private ProductDao productDao= new ProductDao();
    private CategoryDao categoryDao = new CategoryDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Product> discountList = productDao.selectAllProductDiscount();
        ArrayList<Product> dateList = productDao.selectAllProductDate();
        ArrayList<Product> newList = productDao.selectAllProductNew();
        List<Categories> categoriesDog=categoryDao.selectCatorDog(1);
        List<Categories> categoriesCat=categoryDao.selectCatorDog(2);
//        System.out.printf("asvdjhs:" + discountList.size() + dateList.size() + newList.size());
        request.setAttribute("discount", discountList);
        request.setAttribute("date", dateList);
        request.setAttribute("new", newList);
        request.setAttribute("categoriesCat",categoriesCat);
        request.setAttribute("categoriesDog",categoriesDog);
        request.getRequestDispatcher("/web/home.jsp").forward(request,response);
    }

}
