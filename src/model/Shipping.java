package model;

import java.io.Serializable;

public class Shipping implements Serializable {
    private int id;
    private Order order;
    private String address;
    private String deliveryDate;
    private String status;

    public static final String STATUS_PENDING = "Đang chuẩn bị";
    public static final String STATUS_SHIPPING = "Đang giao";
    public static final String STATUS_DELIVERED = "Đã giao";

    // Constructor
    public Shipping(int id, Order order, String address, String deliveryDate) {
        this.id = id;
        this.order = order;
        this.address = address;
        this.deliveryDate = deliveryDate;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status.equals(STATUS_PENDING) || status.equals(STATUS_SHIPPING) || status.equals(STATUS_DELIVERED)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Trạng thái không hợp lệ! Chỉ được nhập: Đang chuẩn bị, Đang giao, Đã giao.");
        }
    }
    public void updateStatus() {
        if (this.status.equals(STATUS_PENDING)) {
            this.status = STATUS_SHIPPING;
        } else if (this.status.equals(STATUS_SHIPPING)) {
            this.status = STATUS_DELIVERED;
        } else {
            System.out.println("Đơn hàng đã giao, không thể cập nhật trạng thái thêm!");
        }
    }

    @Override
    public String toString() {
        return "Shipping{" +
                "ID=" + id +
                ", Đơn hàng ID=" + order.getId() +
                ", Địa chỉ='" + address + '\'' +
                ", Ngày giao hàng='" + deliveryDate + '\'' +
                ", Trạng thái='" + status + '\'' +
                '}';
    }
}
