package dao.admin;

import dao.CustomerDao;
import dao.DBConnection;
import dao.IDao;
import model.admin.Customer;
import model.entities.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDao implements IDao<Order> {
    private final Connection conn;
    private CustomerDao customerDao;

    public OrderDao(Connection conn) {
        this.conn = conn;
        customerDao= new CustomerDao(conn);
    }

    @Override
    public boolean add(Order obj) throws SQLException {
        return false;
    }



    //    @Override
//    public boolean add(Customer obj) {
//        int result = 0;
//        try {
//            String query = "INSERT INTO orders (name, email, password, phone" +
//                    ", address, gender, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
//            PreparedStatement stmt = conn.prepareStatement(query);
//            stmt.setString(1, obj.getName());
//            stmt.setString(2, obj.getEmail());
//            stmt.setString(3, obj.getPassword());
//            stmt.setString(4, obj.getPhone());
//            stmt.setString(5, obj.getAddress());
//            stmt.setInt(6, obj.getGender());
//            stmt.setInt(7, obj.getStatus());
//            result = stmt.executeUpdate();
//            stmt.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result > 0;
//    }
//
    @Override
    public boolean remove(int id) {
        int result = 0;
        try {
            String query = "DELETE FROM orders WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public boolean edit(Order order) throws SQLException {
        int result = 0;
        PreparedStatement stmt;
        String query;
        try {
                query = "UPDATE orders SET orderDate = ?, status = ?, comments = ?, customer_id = ?" +
                        " WHERE id = ?";
                stmt = conn.prepareStatement(query);
                stmt.setTimestamp(1, order.getOrderDate());
                stmt.setString(2, order.getStatus());
                stmt.setString(3, order.getComment());
                stmt.setInt(4, order.getCustomer_id());
                stmt.setInt(5, order.getId());
            result = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public List<Order> getRecords(int currentPage, double recordsPerPage) {
        List<Order> orders = new ArrayList<>();
        try {
            int start = (int) ((currentPage - 1) * recordsPerPage);
            String query = "SELECT * FROM orders ORDER BY id ASC LIMIT ?, ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, start);
            stmt.setInt(2, (int) recordsPerPage);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Order order = extractOrderResultset(rs);
                orders.add(order);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }


    @Override
    public Order findById(int id) {
        Order order = null;
        try {
            String query = "SELECT * FROM orders WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                order = extractOrderResultset(rs);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public List<Order> findByName(String search, int currentPage, double recordsPerPage) {
        int searchValue = Integer.parseInt(search);
        List<Order> orders = new ArrayList<>();
        try {
            int start = (int) ((currentPage - 1) * recordsPerPage);
            String query = "SELECT * FROM orders WHERE customer_id = ? ORDER BY orderDate DESC LIMIT ?, ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,  searchValue);
            stmt.setInt(2, start);
            stmt.setInt(3, (int) recordsPerPage);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Order order = extractOrderResultset(rs);
                orders.add(order);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

//    @Override
//    public List<Order> findByName(String search, int currentPage, double recordsPerPage) {
//        List<Order> orders = new ArrayList<>();
//        try {
//            int start = (int) ((currentPage - 1) * recordsPerPage);
//            String query = "SELECT * FROM orders WHERE customers.name LIKE ? ORDER BY customers.name DESC LIMIT ?, ?";
//            PreparedStatement stmt = conn.prepareStatement(query);
//            stmt.setString(1, '%' + search + '%');
//            stmt.setInt(2, start);
//            stmt.setInt(3, (int) recordsPerPage);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                Customer customer = extractUserResultset(rs);
//                orders.add(customer);
//            }
//            rs.close();
//            stmt.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return orders;
//    }


//    public boolean checkExistEmail(String email) {
//        try {
//            String query = "SELECT email FROM customers WHERE customers.email = ?";
//            PreparedStatement stmt = conn.prepareStatement(query);
//            stmt.setString(1, email);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                return true;
//            }
//            rs.close();
//            stmt.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    public Customer getLoggedCustomer(String email, String password) {
//        Customer customer = null;
//        try (Connection conn = DBConnection.getConnection()) {
//            String query = "SELECT * FROM customers WHERE email = ? AND password = ?";
//            PreparedStatement pstmt = conn.prepareStatement(query);
//            pstmt.setString(1, email);
//            pstmt.setString(2, password);
//            ResultSet rs = pstmt.executeQuery();
//            if (rs.next()) {
//                customer = extractUserResultset(rs);
//            }
//            rs.close();
//            pstmt.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return customer;
//    }
//
    public Order extractOrderResultset(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setOrderDate(rs.getTimestamp("orderDate"));
        order.setStatus(rs.getString("status"));
        String comments;
        if (rs.getString("comments")==null) {
            comments = "";
        } else {
            comments = rs.getString("comments");
        }
        order.setComment(comments);
        order.setCustomer_id(rs.getInt("customer_id"));
        int id = rs.getInt("customer_id");
        String name = customerDao.findById(id).getName();
        order.setCustomerName(name);
        return order;
    }

    public int getNumberOfRows() {
        int numOfRows = 0;
        try {
            Statement stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );
            String query = "SELECT COUNT(*) FROM orders";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                numOfRows = rs.getInt(1);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numOfRows;
    }

}
