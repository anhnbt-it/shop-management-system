package dao.admin;

import dao.DBConnection;
import dao.IDao;
import model.admin.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements IDao<Category> {
    @Override
    public boolean add(Category obj) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql="INSERT INTO categories (name,description,image,type_id)"+
                " VALUES(?,?,?,?);";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,obj.getName());
        pstmt.setString(2,obj.getDesc());
        pstmt.setString(3, obj.getImg());
        pstmt.setInt(4,obj.getType_id());
        if(pstmt.executeUpdate()>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(int id) throws SQLException {
        Connection connection=DBConnection.getConnection();
        String sql="DELETE FROM categories WHERE id=?;";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1,id);
        try {
            if(pstmt.executeUpdate()>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean edit(Category category) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "UPDATE categories SET name=?,description=?,image=?,type_id=? "+
                "WHERE id=?;";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,category.getName());
        pstmt.setString(2,category.getDesc());
        pstmt.setString(3,category.getImg());
        pstmt.setInt(4,category.getType_id());
        pstmt.setInt(5,category.getId());
        if(pstmt.executeUpdate()>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Category> getRecords(int currentPage, double recordsPerPage) throws SQLException {
        Connection connection = DBConnection.getConnection();
        List<Category> categoryList= new ArrayList<>();
        String sql ="SELECT * FROM categories;";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            Category category = new Category();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            category.setDesc(rs.getString("description"));
            category.setImg(rs.getString("image"));
            category.setType_id(Integer.parseInt(rs.getString("type_id")));
            categoryList.add(category);
        }
        return categoryList;
    }

    @Override
    public List<Category> findByName(String query, int currentPage, double recordsPerPage) {
        return null;
    }

    @Override
    public Category findById(int id) throws SQLException {
        Category category = new Category();
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT * FROM categories WHERE id=?;";
        PreparedStatement pstmt= connection.prepareStatement(sql);
        pstmt.setInt(1,id);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            category.setDesc(rs.getString("description"));
            category.setImg(rs.getString("image"));
            category.setType_id(rs.getInt("type_id"));
        }
        return category;
    }
}
