package manager;

import storage.impl.ProductDAOImpl;
import model.Product;

import java.util.List;

public class ProductManager {
    private List<Product> products;
    private final ProductDAOImpl productDAO = new ProductDAOImpl();

    public ProductManager() {
        this.products = productDAO.getAllProducts();
    }

    public void addProduct(int id, String name, double price, int stock) {
        if (price < 0) {
            throw new IllegalArgumentException("Giá sản phẩm không thể âm!");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("Số lượng tồn kho không thể âm!");
        }
        products.add(new Product(id, name, price, stock));
        productDAO.saveProducts();
        productDAO.saveProductsToCSV();
        System.out.println("Sản phẩm đã được thêm thành công!");
    }

    public void removeProduct(int id) {
        products.removeIf(p -> p.getId() == id);
        productDAO.saveProducts();
        productDAO.saveProductsToCSV();
        System.out.println("Sản phẩm đã được xóa!");
    }


    public void viewProducts() {
        if (products.isEmpty()) {
            System.out.println("Không có sản phẩm nào.");
        } else {
            products.forEach(System.out::println);
        }
    }
}
