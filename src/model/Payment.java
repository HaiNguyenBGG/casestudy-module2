package model;

import java.io.Serializable;

public class Payment implements Serializable {
    private int id;
    private Order order;
    private double amount;
    private String paymentMethod;
    private String status;

    public static final String STATUS_PENDING = "Chưa thanh toán";
    public static final String STATUS_PAID = "Đã thanh toán";
    public static final String STATUS_REFUNDED = "Đã hoàn tiền";

    public Payment(int id, Order order, double amount, String paymentMethod) {
        this.id = id;
        this.order = order;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = STATUS_PENDING;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("❌ Số tiền không thể nhỏ hơn 0!");
        }
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status.equals(STATUS_PENDING) || status.equals(STATUS_PAID) || status.equals(STATUS_REFUNDED)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Trạng thái không hợp lệ! Chỉ được nhập: Chưa thanh toán, Đã thanh toán, Đã hoàn tiền.");
        }
    }

    public boolean isPaid() {
        return status.equals(STATUS_PAID);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "ID=" + id +
                ", Đơn hàng ID=" + order.getId() +
                ", Số tiền=" + amount +
                ", Phương thức='" + paymentMethod + '\'' +
                ", Trạng thái='" + status + '\'' +
                '}';
    }
}
