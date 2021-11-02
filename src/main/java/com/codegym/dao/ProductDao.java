package com.codegym.dao;

import com.codegym.model.entities.Product;
import com.codegym.utilities.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class ProductDao implements iProductDao {
    private final DBConnection dbConnection = new DBConnection();
    private static final String INSERT_USERS_SQL = "insert into users" + "(name,email,country) values" +"(?,?,?);";
    private static final String SELECT_PRODUCT_BY_ID = "select * from products where id =?";

    private static final String SELECT_ALL_PRODUCTS = "select * from products";
    private static final String SELECT_ALL_PRODUCTS_LIMIT = "select * from products order by discount DESC limit 0,3";
    private static final String SELECT_ALL_PRODUCTS_LIMIT_DATE = "select * from products order by created_at DESC limit 0,6";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";

    public ProductDao() {

    }

    @Override
    public Product selectProduct(int id) {
        Product product = null;
        try (Connection connection = DBConnection.getConnection();PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {
            preparedStatement.setInt(1,id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int category_id = rs.getInt("category_id");
                String description = rs.getString("description");
                int quantityInStock = rs.getInt("quantityInStock");
                double price = rs.getDouble("price");
                double discount = rs.getDouble("discount");
                double realPrice = Math.round(price - price*discount/100);
                String img = rs.getString("img");
                Timestamp createdDate = rs.getTimestamp("created_at");
                product = new Product(id, name, category_id, description, quantityInStock, price, discount, img, realPrice, createdDate);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    @Override
    public ArrayList<Product> selectAllProduct() {
        ArrayList<Product> products = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int category_id = rs.getInt("category_id");
                String description = rs.getString("description");
                int quantityInStock = rs.getInt("quantityInStock");
                double price = rs.getDouble("price");
                double discount = rs.getDouble("discount");
                double realPrice = Math.round(price - price*discount/100);
                String img = rs.getString("img");
                Timestamp createdDate = rs.getTimestamp("created_at");
                Product product = new Product(id, name, category_id, description, quantityInStock, price, discount, img, realPrice, createdDate);
                products.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    public static final String SELECT_BY_ID_CATEGORY="select * from products where category_id=?";
    public ArrayList<Product> select_by_ID_Category(int id_category){
        ArrayList<Product> products=new ArrayList<>();
        try{
            Connection connection=DBConnection.getConnection();
            PreparedStatement statement=connection.prepareStatement(SELECT_BY_ID_CATEGORY);
            statement.setInt(1,id_category);
            ResultSet rs=statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int quantityInStock = rs.getInt("quantityInStock");
                double price = rs.getDouble("price");
                double discount = rs.getDouble("discount");
                double realPrice = Math.round(price - price*discount/100);
                String img = rs.getString("img");
                Timestamp createdDate = rs.getTimestamp("created_at");
                Product product = new Product(id, name, id_category, description, quantityInStock, price, discount, img, realPrice, createdDate);
                products.add(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return products;
    }
    public static final String SEARCH_PRODUCTS="SELECT * FROM products WHERE name LIKE ?";
    public ArrayList<Product> search_Products(String names){
        ArrayList<Product> products=new ArrayList<>();
        String str="%"+names+"%";
        try{
            Connection connection= DBConnection.getConnection();
            PreparedStatement statement=connection.prepareStatement(SEARCH_PRODUCTS);
            statement.setString(1,str);
            ResultSet rs=statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int category_id = rs.getInt("category_id");
                String description = rs.getString("description");
                int quantityInStock = rs.getInt("quantityInStock");
                double price = rs.getDouble("price");
                double discount = rs.getDouble("discount");
                double realPrice = Math.round(price - price*discount/100);
                String img = rs.getString("img");
                Timestamp createdDate = rs.getTimestamp("created_at");
                Product product = new Product(id, name, category_id, description, quantityInStock, price, discount, img, realPrice, createdDate);
                products.add(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return products;
    }
    public static final String SELECT_RELATED_PRODUCT="SELECT * FROM products WHERE category_id=? AND id<>? LIMIT 4";
    public ArrayList<Product> selectRelatedProduct(int category_id,int id) {
        ArrayList  products = new ArrayList<>();
        try{
            Connection connection=DBConnection.getConnection();
            PreparedStatement statement=connection.prepareStatement(SELECT_RELATED_PRODUCT);
            statement.setInt(1,category_id);
            statement.setInt(2,id);
            ResultSet rs=statement.executeQuery();
            while (rs.next()){
                int id1=rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int quantityInStock = rs.getInt("quantityInStock");
                double price = rs.getDouble("price");
                double discount = rs.getDouble("discount");
                double realPrice = Math.round(price - price*discount/100);
                String img = rs.getString("img");
                Timestamp createdDate = rs.getTimestamp("created_at");
                Product product = new Product(id1, name, category_id, description, quantityInStock, price, discount, img, realPrice, createdDate);
                products.add(product);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return products;
    }

    public static final String SORT_PRODUCT_N_A="SELECT * FROM products order by name asc";
    public static final String SORT_PRODUCT_N_D="SELECT * FROM products order by name desc";
    public static final String SORT_PRODUCT_P_A="SELECT * FROM products order by price asc";
    public static final String SORT_PRODUCT_P_D="SELECT * FROM products order by price desc";
    public ArrayList<Product> sort_Product_N_A(){
        return sort_Product(SORT_PRODUCT_N_A);
    }
    public ArrayList<Product> sort_Product_N_D(){
        return sort_Product(SORT_PRODUCT_N_D);
    }
    public ArrayList<Product> sort_Product_P_A(){
        return sort_Product(SORT_PRODUCT_P_A);
    }
    public ArrayList<Product> sort_Product_P_D(){
        return sort_Product(SORT_PRODUCT_P_D);
    }

    public ArrayList<Product> sort_Product(String condition){
        ArrayList<Product> products=new ArrayList<>();
        try{
            Connection connection=DBConnection.getConnection();
            PreparedStatement statement=connection.prepareStatement(condition);
//            statement.setString(1,name);
//            statement.setString(2,sort);
            ResultSet rs=statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String names = rs.getString("name");
                int category_id = rs.getInt("category_id");
                String description = rs.getString("description");
                int quantityInStock = rs.getInt("quantityInStock");
                double price = rs.getDouble("price");
                double discount = rs.getDouble("discount");
                double realPrice = Math.round(price - price*discount/100);
                String img = rs.getString("img");
                Timestamp createdDate = rs.getTimestamp("created_at");
                Product product = new Product(id, names, category_id, description, quantityInStock, price, discount, img, realPrice, createdDate);
                products.add(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return products;
    }

    public static final String SORT_PRODUCT_CATE_ASC="SELECT * FROM products where category_id = ? order by name asc";
    public static final String SORT_PRODUCT_CATE_DESC="SELECT * FROM products where category_id = ? order by name desc";
    public static final String SORT_PRODUCT_CATE_P_A="SELECT * FROM products where category_id = ? order by price asc";
    public static final String SORT_PRODUCT_CATE_P_D="SELECT * FROM products where category_id = ? order by price desc";
    public ArrayList<Product> sort_Product_Cate_NA(int id){
        return sort_Product_Cate(id, SORT_PRODUCT_CATE_ASC);
    }
    public ArrayList<Product> sort_Product_Cate_ND(int id){
        return sort_Product_Cate(id, SORT_PRODUCT_CATE_DESC);
    }
    public ArrayList<Product> sort_Product_Cate_PA(int id){
        return sort_Product_Cate(id, SORT_PRODUCT_CATE_P_A);
    }
    public ArrayList<Product> sort_Product_Cate_PD(int id){
        return sort_Product_Cate(id, SORT_PRODUCT_CATE_P_D);
    }
    public ArrayList<Product> sort_Product_Cate(int id, String condition){
        ArrayList<Product> products=new ArrayList<>();
        try{
            Connection connection=DBConnection.getConnection();
            PreparedStatement statement=connection.prepareStatement(condition);
//            statement.setString(1,column);
            statement.setInt(1,id);
//            statement.setString(2,sort);
            ResultSet rs=statement.executeQuery();
            while (rs.next()){
                int idx = rs.getInt("id");
                String names = rs.getString("name");
                int category_id = rs.getInt("category_id");
                String description = rs.getString("description");
                int quantityInStock = rs.getInt("quantityInStock");
                double price = rs.getDouble("price");
                double discount = rs.getDouble("discount");
                double realPrice = Math.round(price - price*discount/100);
                String img = rs.getString("img");
                Timestamp createdDate = rs.getTimestamp("created_at");
                Product product = new Product(idx, names, category_id, description, quantityInStock, price, discount, img, realPrice, createdDate);
                products.add(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return products;
    }

    public ArrayList<Product> selectAllProductDiscount() {
        return selectAllProductWith(SELECT_ALL_PRODUCTS_LIMIT);
    }

    public ArrayList<Product> selectAllProductDate() {
        return selectAllProductWith(SELECT_ALL_PRODUCTS_LIMIT_DATE);
    }

    public ArrayList<Product> selectAllProductNew() {
         return selectAllProductWith(SELECT_ALL_PRODUCTS_LIMIT_DATE);
    }

    public ArrayList<Product> selectAllProductWith(String condition) {
        ArrayList<Product> products = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(condition)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int category_id = rs.getInt("category_id");
                String description = rs.getString("description");
                int quantityInStock = rs.getInt("quantityInStock");
                double price = rs.getDouble("price");
                double discount = rs.getDouble("discount");
                double realPrice = Math.round(price - price*discount/100);
                String img = rs.getString("img");
                Timestamp createdDate = rs.getTimestamp("created_at");

                Product product = new Product(id, name, category_id, description, quantityInStock, price, discount, img, realPrice, createdDate);
                products.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }



}
