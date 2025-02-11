package manager;

import model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private List<Product> products = new ArrayList<>();

    public void addProduct(int id, String name, double price, int stock) {
        if (price < 0) {
            throw new IllegalArgumentException("Giá sản phẩm không thể âm!");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("Số lượng tồn kho không thể âm!");
        }
        products.add(new Product(id, name, price, stock));
        System.out.println("Sản phẩm đã được thêm thành công!");
    }

    public void removeProduct(int id) {
        products.removeIf(p -> p.getId() == id);
        System.out.println("Sản phẩm đã được xóa!");
    }

    public List<Product> getAllProducts() {
        return products;
    }
}
