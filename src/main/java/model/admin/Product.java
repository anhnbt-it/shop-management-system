package model.admin;

import java.util.Date;

public class Product {
    private int id;
    private String name;
    private int category;
    private String desc;
    private int qty;
    private double price;
    private double discount;
    private String img;
    private int status;
    private Date created;
    private Date updated;

    public Product() {
    }

    public Product(String name, int category, String desc, int qty, double price, double discount, String img, int status) {
        this.name = name;
        this.category = category;
        this.desc = desc;
        this.qty = qty;
        this.price = price;
        this.discount = discount;
        this.img = img;
        this.status = status;
    }

    public Product(int id, String name, int category, String desc, int qty, double price, double discount, String img, int status) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.desc = desc;
        this.qty = qty;
        this.price = price;
        this.discount = discount;
        this.img = img;
        this.status = status;
    }

    public Product(int id, String name, int category, String desc, int qty, double price, double discount, String img, int status, Date created, Date updated) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.desc = desc;
        this.qty = qty;
        this.price = price;
        this.discount = discount;
        this.img = img;
        this.status = status;
        this.created = created;
        this.updated = updated;
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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", desc='" + desc + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                ", discount=" + discount +
                ", img='" + img + '\'' +
                ", status=" + status +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
