package model;

import storage.utils.LogService;

import java.io.Serializable;

public class Payment extends Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    private Order order;
    private String paymentMethod;

    public static final String STATUS_PENDING = "Chưa thanh toán";
    public static final String STATUS_PAID = "Đã thanh toán";

    public Payment(int id, Order order, double amount, String paymentMethod) {
        super(id, amount, determineStatus(paymentMethod));
        this.order = order;
        this.paymentMethod = paymentMethod;
    }

    private static String determineStatus(String paymentMethod) {
        return paymentMethod.equalsIgnoreCase("Chuyển khoản") ? STATUS_PAID : STATUS_PENDING;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        this.status = determineStatus(paymentMethod);
    }

    public void setStatus(String status) {
        if (status.equals(STATUS_PENDING) || status.equals(STATUS_PAID)) {
            this.status = status;

            LogService.log("Đơn hàng ID " + order.getId() + " đã cập nhật trạng thái thanh toán: " + status);
        } else {
            throw new IllegalArgumentException("Trạng thái không hợp lệ!");
        }
    }


    public boolean isPaid() {
        return status.equals(STATUS_PAID);
    }

    @Override
    public String toString() {
        return "Thanh toán: " +
                "Đơn hàng ID=" + order.getId() +
                " | Số tiền=" + amount +
                " | Phương thức='" + paymentMethod + '\'' +
                " | Trạng thái='" + status + '\'';
    }
}
