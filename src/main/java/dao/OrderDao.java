package dao;

import model.entities.Order;
import model.entities.OrderDetail;
import model.entities.Product;

import java.sql.*;

public class OrderDao implements iOrderDao{
    private final DBConnection dbConnection = new DBConnection();
    private static final String INSERT_ORDERS_SQL = "insert into orders" + "(customer_id) values" +"(?);";
    private static final String INSERT_ORDERDETAILS_SQL = "insert into orderdetais" + "(order_id, product_id, quantityOrdered, priceEach) values" +"(?,?,?,?);";
    private static final String UPDATE_NEW_QUANTITY_SQL = "update products set quantityInStock = ? where id=?";
    private static final String SELECT_ORDER_MAX = "select * from products order by id desc limit 1";
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
//            preparedStatement.setInt(1,id);
//            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Timestamp orderDate = rs.getTimestamp("orderDate");
                String status = rs.getString("status");
                order = new Order(id, orderDate, status);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return order;
    }

    public void insertOrderDetail(OrderDetail orderDetail, Order order) throws SQLException {
        try (Connection connection = DBConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDERDETAILS_SQL)) {
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setInt(2, orderDetail.getProduct().getId());
            preparedStatement.setInt(3, orderDetail.getQuantity());
            preparedStatement.setDouble(4, orderDetail.getProduct().getRealPrice());
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
