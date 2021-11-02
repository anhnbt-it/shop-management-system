package com.codegym.dao;

import com.codegym.model.entities.Order;
import com.codegym.model.entities.Product;
import com.codegym.utilities.DBConnection;

import java.sql.*;

public class OrderDao implements iOrderDao{
    private final DBConnection dbConnection = new DBConnection();
    private static final String INSERT_ORDERS_SQL = "insert into orders" + "(customer_id) values" +"(?);";
    private static final String INSERT_ORDERDETAILS_SQL = "insert into orderdetails" + "(order_id, product_id, quantityOrdered, priceEach) values" +"(?,?,?,?);";
    private static final String UPDATE_NEW_QUANTITY_SQL = "update products set quantityInStock = ? where id=?";
    private static final String SELECT_ORDER_MAX = "select * from orders order by id desc limit 1";
    @Override
    public void insertOrder(Order order) throws SQLException {
        try (Connection connection = DBConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDERS_SQL)) {
            preparedStatement.setInt(1, order.getCustomer_id());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Order selectTopIdOrder() {
        Order order = null;
        try (Connection connection = DBConnection.getConnection();PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_MAX)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Timestamp orderDate = rs.getTimestamp("orderDate");
//                Date orderDate = new Date(rs.getTimestamp("orderDate").getTime());
                String status = rs.getString("status");
                order = new Order(id, orderDate, status);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return order;
    }

    public void insertOrderDetail(int orderId, int productId, int quantity, double price) throws SQLException {
        try (Connection connection = DBConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDERDETAILS_SQL)) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, productId);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setDouble(4, price);
            System.out.println("orderdetail: " + preparedStatement);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean updateProductQuantity(Product product) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_NEW_QUANTITY_SQL);) {
            statement.setInt(1, product.getQuantity());
            statement.setInt(2, product.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
