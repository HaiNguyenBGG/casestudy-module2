package manager;

import storage.dao.ProductDAO;
import storage.impl.ProductDAOImpl;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private List<Product> products;
    private final ProductDAO productDAO = new ProductDAOImpl();

    public ProductManager() {
        this.products = new ArrayList<>(productDAO.loadProducts());
    }

    public void addProduct(int id, String name, double price, int stock) {
        products.add(new Product(id, name, price, stock));
        productDAO.saveProducts(products);
        System.out.println("Đã thêm sản phẩm và lưu vào file.");
    }

    public void viewProducts() {
        if (products.isEmpty()) {
            System.out.println("Không có sản phẩm nào.");
        } else {
            products.forEach(System.out::println);
        }
    }
}
