package dao.admin;

import dao.DBConnection;
import dao.IDao;
import model.admin.Customer;
import model.admin.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductDAO implements IDao<Product> {

    @Override
    public boolean add(Product obj) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql ="INSERT INTO products (name,category_id,description,quantityInStock,price,discount,img,status)" +
                " VALUES(?,?,?,?,?,?,?,?);";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, obj.getName());
        pstmt.setInt(2,obj.getCategory());
        pstmt.setString(3, obj.getDesc());
        pstmt.setInt(4,obj.getQty());
        pstmt.setDouble(5,obj.getPrice());
        pstmt.setDouble(6,obj.getDiscount());
        pstmt.setString(7, obj.getImg());
        pstmt.setInt(8, obj.getStatus());
        if(pstmt.executeUpdate()>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(int id) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "DELETE FROM products WHERE id=?;";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1,id);
        if (pstmt.executeUpdate()>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean edit(Product obj) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "UPDATE products SET name=?, category_id=?,description=?,quantityInStock=?,price=?,discount=?,img=?,updated_at=? " +
                "WHERE id=?;";
        System.out.println(obj.toString());
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,obj.getName());
        pstmt.setInt(2,obj.getCategory());
        pstmt.setString(3,obj.getDesc());
        pstmt.setInt(4,obj.getQty());
        pstmt.setDouble(5,obj.getPrice());
        pstmt.setDouble(6,obj.getDiscount());
        pstmt.setString(7,obj.getImg());
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        pstmt.setTimestamp(8, timestamp);
        pstmt.setInt(9,obj.getId());

        if(pstmt.executeUpdate()>0){
            return true;
        }
        return false;
    }
    public Product extractProductResultSet(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setCategory(rs.getInt("category_id"));
        product.setDesc(rs.getString("description"));
        product.setQty(rs.getInt("quantityInStock"));
        product.setPrice(rs.getDouble("price"));
        product.setDiscount(rs.getDouble("discount"));
        product.setImg(rs.getString("img"));
        product.setCreated(rs.getDate("created_at"));
        return product;
    }

    @Override
    public List<Product> getRecords(int currentPage, double recordsPerPage) throws SQLException {
        Connection connection = DBConnection.getConnection();
        List<Product> productList = new ArrayList<>();
        int start= (int) (currentPage * recordsPerPage-recordsPerPage);
        String sql ="SELECT * FROM products ORDER BY id LIMIT ?, ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1,start);
        pstmt.setInt(2, (int) recordsPerPage);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setCategory(rs.getInt("category_id"));
            product.setDesc(rs.getString("description"));
            product.setQty(rs.getInt("quantityInStock"));
            product.setPrice(rs.getDouble("price"));
            product.setDiscount(rs.getDouble("discount"));
            product.setImg(rs.getString("img"));
            product.setCreated(rs.getDate("created_at"));
            System.out.println(product);
            productList.add(product);
        }
        return productList;
    }

    @Override
    public List<Product> findByName(String search, int currentPage, double recordsPerPage) {
        Connection connection = DBConnection.getConnection();
        List<Product> products = new ArrayList<>();
        try {
            int start = (int) ((currentPage - 1) * recordsPerPage);
            String query = "SELECT * FROM products WHERE products.name LIKE ? ORDER BY products.name DESC LIMIT ?, ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, '%' + search + '%');
            stmt.setInt(2, start);
            stmt.setInt(3, (int) recordsPerPage);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = extractProductResultSet(rs);
                products.add(product);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product findById(int id) throws SQLException {
        Product product = new Product();

        Connection connection = DBConnection.getConnection();
        String sql="SELECT * FROM products WHERE id=?;";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1,id);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
        product = extractProductResultSet(rs);
        }
        return product;
    }

    public int getNumberOfRows() {
        int numOfRows = 0;
        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );
            String query = "SELECT COUNT(*) as total FROM products";
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
