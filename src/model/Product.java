package model;

import java.io.Serializable;

public class Product extends BaseEntity implements Serializable {
    private String name;
    private double price;
    private int stock;

    public Product(int id, String name, double price, int stock) {
        super(id);
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', price=" + price + ", stock=" + stock + "}";
    }

    public void decreaseStock(int quantity) {
        stock -= quantity;
    }
}
