package model;

import java.io.Serializable;

public class OrderDetail extends BaseEntity implements Serializable {
    private Order order;
    private Product product;
    private int quantity;
    private double subtotal;

    public OrderDetail(int id, Order order, Product product, int quantity) {
        super(id);
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.subtotal = calculateSubtotal();
    }

    private double calculateSubtotal() {
        return product.getPrice() * quantity;
    }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) {
        this.product = product;
        this.subtotal = calculateSubtotal();
    }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Số lượng phải lớn hơn 0!");
        }
        this.quantity = quantity;
        this.subtotal = calculateSubtotal();
    }

    public double getSubtotal() { return subtotal; }

    @Override
    public String toString() {
        return "Sản phẩm: " + (product != null ? product.getName() : "Không xác định") +
                " | ID: " + product.getId() +
                " | Số lượng: " + quantity +
                " | Giá/SP: " + product.getPrice() +
                " | Tổng: " + subtotal;
    }
}
