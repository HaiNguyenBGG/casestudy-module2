package view;

import manager.ProductManager;

import model.Customer;

public class MainView {
    public static void main(String[] args) {
        ProductManager productManager = new ProductManager();

        // Thêm sản phẩm mới
        productManager.addProduct(1, "Bánh ChocoPie", 20000, 50);
        productManager.addProduct(2, "Sữa Vinamilk", 30000, 30);

        // Xem danh sách sản phẩm
        productManager.viewProducts();

        // Xóa sản phẩm
        productManager.removeProduct(1);
        productManager.viewProducts();
    }
}
