package com.codegym.dao;

import com.codegym.model.entities.Order;

import java.sql.SQLException;

public interface iOrderDao {
    void insertOrder(Order order) throws SQLException;
}
