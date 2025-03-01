package manager;

import storage.dao.ProductDAO;
import storage.impl.ProductDAOImpl;
import model.Product;
import storage.utils.LogService;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private List<Product> products;
    private final ProductDAO productDAO = new ProductDAOImpl();

    public ProductManager() {
        this.products = new ArrayList<>(productDAO.loadProducts());
    }

    public void addProduct(int id, String name, double price, int stock) {
        try {
            if (products.stream().anyMatch(p -> p.getId() == id)) {
                throw new IllegalArgumentException("ID sản phẩm đã tồn tại! Hãy nhập ID khác.");
            }

            Product product = new Product(id, name, price, stock);
            products.add(product);
            productDAO.saveProducts(products);

            LogService.log("Đã thêm sản phẩm: " + product.getName() + " | Giá: " + price + " | Số lượng: " + stock);
            System.out.println("Thêm sản phẩm thành công!");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewProducts() {
        if (products.isEmpty()) {
            System.out.println("Không có sản phẩm nào.");
        } else {
            products.forEach(System.out::println);
        }
    }

    public Product getProductById(int id) {
        return products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public void saveProducts() {
        productDAO.saveProducts(products);
    }

    public void removeProduct(int id) {
        products.removeIf(p -> p.getId() == id);
        productDAO.saveProducts(products);
    }

    public boolean productExists(int id) {
        return products.stream().anyMatch(p -> p.getId() == id);
    }
}
