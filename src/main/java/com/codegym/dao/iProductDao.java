package com.codegym.dao;

import com.codegym.model.entities.Product;

import java.util.List;

public interface iProductDao {
    Product selectProduct(int id);
    List<Product> selectAllProduct();
}
