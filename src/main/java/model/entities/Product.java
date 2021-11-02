package model.entities;

import java.sql.Timestamp;

public class Product {
    private int id;
    private String name;
    private int category_id;
    private String description;
    private int quantity;
    private double price;
    private double discount;
    private String img;
    private double realPrice;

    private Timestamp createdDate;

    public Product() {
    }

    public Product(String name, int category_id, String description, int quantity, double price, double discount, String img, double realPrice, Timestamp createdDate) {
        this.name = name;
        this.category_id = category_id;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
        this.img = img;
        this.realPrice = realPrice;
        this.createdDate = createdDate;
    }

    public Product(int id, String name, int category_id, String description, int quantity, double price, double discount, String img, double realPrice, Timestamp createdDate) {
        this.id = id;
        this.name = name;
        this.category_id = category_id;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
        this.img = img;
        this.realPrice = realPrice;
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(double realPrice) {
        this.realPrice = realPrice;
    }
}
