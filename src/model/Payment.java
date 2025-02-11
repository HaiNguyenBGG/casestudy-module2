package model;

import java.io.Serializable;

public class Payment implements Serializable {
    private int orderId;
    private String method;
    private boolean isPaid;

    public Payment(int orderId, String method, boolean isPaid) {
        this.orderId = orderId;
        this.method = method;
        this.isPaid = isPaid;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public String toString() {
        return "Payment{" + "orderId=" + orderId + ", method='" + method + "', isPaid=" + isPaid + "}";
    }
}
