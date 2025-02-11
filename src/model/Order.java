package model;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private int id;
    private Customer customer;
    private List<OrderDetail> orderDetails;
    private double totalPrice;
    private String orderDate;

    public Order(int id, Customer customer, List<OrderDetail> orderDetails, String orderDate) {
        this.id = id;
        this.customer = customer;
        this.orderDetails = orderDetails;
        this.totalPrice = calculateTotalPrice();
        this.orderDate = orderDate;
    }

    private double calculateTotalPrice() {
        return orderDetails.stream().mapToDouble(OrderDetail::getSubtotal).sum();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", customer=" + customer + ", totalPrice=" + totalPrice + ", orderDate='" + orderDate + "'}";
    }
}