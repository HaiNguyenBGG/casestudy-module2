package model;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class Order extends Transaction implements Serializable {
    private Customer customer;
    private List<OrderDetail> orderDetails;
    private String orderDate;
    private Payment payment;
    private Shipping shipping;

    public Order(int id, Customer customer, List<OrderDetail> orderDetails, String orderDate, Payment payment, Shipping shipping) {
        super(id, calculateTotalPrice(orderDetails), "Đang xử lý");
        this.customer = customer;
        this.orderDetails = orderDetails;
        this.orderDate = orderDate;
        this.payment = payment;
        this.shipping = shipping;
    }

    private static double calculateTotalPrice(List<OrderDetail> orderDetails) {
        return orderDetails.stream().mapToDouble(OrderDetail::getSubtotal).sum();
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
        this.amount = calculateTotalPrice(orderDetails);
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
        String orderDetailsStr = orderDetails.stream()
                .map(od -> "Sản phẩm: " + (od.getProduct() != null ? od.getProduct().getName() : "Không xác định") +
                        " | ID: " + od.getProduct().getId() +
                        " | Số lượng: " + od.getQuantity() +
                        " | Giá/SP: " + od.getProduct().getPrice() +
                        " | Tổng: " + od.getSubtotal())
                .collect(Collectors.joining("\n"));

        return "\nĐơn hàng ID: " + id +
                "\nKhách hàng: " + customer.getName() +
                "\nNgày đặt hàng: " + orderDate +
                "\n" + orderDetailsStr +
                "\nTổng tiền: " + amount +
                "\nThanh toán: " + (payment.isPaid() ? "Đã thanh toán" : "Chưa thanh toán") +
                "\nTrạng thái giao hàng: " + shipping.getStatus() +
                "\nĐịa chỉ giao hàng: " + shipping.getAddress() +
                "\n──────────────────────────────";
    }

    public double getTotalPrice() {
        return this.amount;
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetails.add(orderDetail);
        this.amount = calculateTotalPrice(orderDetails);
    }

    public void removeOrderDetail(int id) {
        orderDetails.removeIf(od -> od.getId() == id);
    }
}
