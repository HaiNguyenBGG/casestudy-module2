package model;

import java.io.Serializable;

public class Payment extends Transaction implements Serializable {
    private Order order;
    private String paymentMethod;

    public static final String STATUS_PENDING = "Chưa thanh toán";
    public static final String STATUS_PAID = "Đã thanh toán";
    public static final String STATUS_REFUNDED = "Đã hoàn tiền";

    public Payment(int id, Order order, double amount, String paymentMethod) {
        super(id, amount, STATUS_PENDING);
        this.order = order;
        this.paymentMethod = paymentMethod;
    }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

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
