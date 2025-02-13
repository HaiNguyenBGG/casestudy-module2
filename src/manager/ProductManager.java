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
        Product product = new Product(id, name, price, stock);
        products.add(product);
        productDAO.saveProducts(products);
//        System.out.println("Đã thêm sản phẩm và lưu vào file.");

        LogService.log("Đã thêm sản phẩm: " + product.getName() + " | Giá: " + price + " | Số lượng: " + stock);
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
}
