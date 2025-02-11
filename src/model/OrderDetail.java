package model;

import java.io.Serializable;

public class OrderDetail implements Serializable {
    private Product product;
    private int quantity;
    private double subtotal;

    public OrderDetail(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.subtotal = calculateSubtotal();
    }

    private double calculateSubtotal() {
        return product.getPrice() * quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "product=" + product + ", quantity=" + quantity + ", subtotal=" + subtotal + "}";
    }
}