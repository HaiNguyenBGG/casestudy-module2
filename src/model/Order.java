package model;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private int id;
    private Customer customer;
    private List<OrderDetail> orderDetails;
    private double totalPrice;
    private String orderDate;
    private Payment payment;
    private Shipping shipping;

    public Order(int id, Customer customer, List<OrderDetail> orderDetails, String orderDate, Payment payment, Shipping shipping) {
        this.id = id;
        this.customer = customer;
        this.orderDetails = orderDetails;
        this.orderDate = orderDate;
        this.payment = payment;
        this.shipping = shipping;
        this.totalPrice = calculateTotalPrice();
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
        this.totalPrice = calculateTotalPrice();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    @Override
    public String toString() {
        return "Order { ID=" + id +
                ", Khách hàng=" + customer.getName() +
                ", Tổng tiền=" + totalPrice +
                ", Ngày đặt hàng='" + orderDate + '\'' +
                ", Thanh toán=" + (payment.isPaid() ? "Đã thanh toán" : "Chưa thanh toán") +
                ", Giao hàng=" + shipping.getStatus() +
                ", Địa chỉ giao hàng='" + shipping.getAddress() + '\'' +
                " }";
    }
}
