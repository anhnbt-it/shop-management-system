package model.entities;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private Timestamp orderDate;
    private String status;
    private String comment;
    private int customer_id;
    private List<OrderDetail> list;
    private double totalOrder;
    private String customerName;

    public Order() {
    }

    public Order(Timestamp orderDate, String status, String comment) {
        this.orderDate = orderDate;
        this.status = status;
        this.comment = comment;
    }

    public Order(int id, Timestamp orderDate, String status) {
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
    }

    public Order(int id, Timestamp orderDate, String status, String comment, int customer_id) {
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
        this.comment = comment;
        this.customer_id = customer_id;
    }

    public Order(int id, Timestamp orderDate, String status, String comment, int customer_id, double totalOrder) {
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
        this.comment = comment;
        this.customer_id = customer_id;
        this.totalOrder = totalOrder;
    }

    public Order(int id, Timestamp orderDate, String status, String comment, int customer_id, String customerName) {
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
        this.comment = comment;
        this.customer_id = customer_id;
        this.customerName = customerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<OrderDetail> getList() {
        return list;
    }

    public void setList(List<OrderDetail> list) {
        this.list = list;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public double getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(double totalOrder) {
        this.totalOrder = totalOrder;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
