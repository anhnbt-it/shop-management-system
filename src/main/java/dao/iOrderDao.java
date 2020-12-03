package dao;

import model.entities.Order;

import java.sql.SQLException;

public interface iOrderDao {
    void insertOrder(Order order) throws SQLException;
}
