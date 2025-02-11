package model;

import java.io.Serializable;

public class Shipping implements Serializable {
    private int orderId;
    private String address;
    private String status;

    public Shipping(int orderId, String address, String status) {
        this.orderId = orderId;
        this.address = address;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Shipping{" + "orderId=" + orderId + ", address='" + address + "', status='" + status + "'}";
    }
}
