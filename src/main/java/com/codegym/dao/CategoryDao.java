package com.codegym.dao;

import com.codegym.model.entities.Categories;
import com.codegym.utilities.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    public static final String SELECT_ALL_CATEGORY="select * from categories";
    public static final String INSERT_INTO_CATEGORY="insert into categories(name,description,type_id) values (?,?,?)";
    public static final String SELECT_CATEGORY_BY_ID="select id,name,description,created_at,type_id from categories where id=?";
    public List<Categories> getAllCategories(){
        List<Categories> categories=new ArrayList<>();
        Connection connection= DBConnection.getConnection();
        PreparedStatement statement=null;
        try{
            statement=connection.prepareStatement(SELECT_ALL_CATEGORY);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                int id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                String description=resultSet.getString("description");
                Timestamp created_at=resultSet.getTimestamp("created_at");
                int type_id=resultSet.getInt("type_id");
                Categories category=new Categories(id,name,description,created_at,type_id);
                categories.add(category);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return categories;
    }
    public void add(Categories category){
        try{
            Connection connection=DBConnection.getConnection();
            PreparedStatement statement=connection.prepareStatement(INSERT_INTO_CATEGORY);
            statement.setString(1,category.getName());
            statement.setString(2,category.getDescription());
            statement.setInt(3,category.getType_id());
            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Categories searchByID(int id){
        Categories category = null;
        try{
            Connection connection=DBConnection.getConnection();
            PreparedStatement statement=connection.prepareStatement(SELECT_CATEGORY_BY_ID);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String name=rs.getString("name");
                String description=rs.getString("description");
                Timestamp created_at=rs.getTimestamp("created_at");
                int type_id=rs.getInt("type_id");
                category=new Categories(id,name,description,created_at,type_id);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return category;
    }
    public static final String DELETE_CATEGORY ="delete from categories where id=?";
    public boolean delete(int id){
        boolean rowdelete=false;
        try {
            Connection connection=DBConnection.getConnection();
            PreparedStatement statement=connection.prepareStatement(DELETE_CATEGORY);
            statement.setInt(1,id);
            rowdelete=statement.executeUpdate()>0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return rowdelete;
    }
    private static final String UPDATE_CATEGORIES_SQL = "update categories set name = ?, description =?,type_id=? where id = ?;";
    public boolean update(Categories category){
        boolean updateCategory=false;
        try{
            Connection connection=DBConnection.getConnection();
            PreparedStatement statement=connection.prepareStatement(UPDATE_CATEGORIES_SQL);
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.setInt(3,category.getType_id());
            statement.setInt(4,category.getId());
            updateCategory= statement.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return updateCategory;
    }
    public static final String SELECT_DOGorCAT="SELECT * FROM categories WHERE type_id=?";
    public List<Categories> selectCatorDog(int type_id){
        List<Categories> categories=new ArrayList<>();
        try{
            Connection connection=DBConnection.getConnection();
            PreparedStatement statement=connection.prepareStatement(SELECT_DOGorCAT);
            statement.setInt(1,type_id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id=rs.getInt("id");
                String name=rs.getString("name");
                String description=rs.getString("description");
                Timestamp created_at=rs.getTimestamp("created_at");
                Categories category=new Categories(id,name,description,created_at,type_id);
                categories.add(category);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return categories;
    }
}
