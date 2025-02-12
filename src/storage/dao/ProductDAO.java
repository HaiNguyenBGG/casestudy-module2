package storage.dao;

import model.Product;
import java.util.List;

public interface ProductDAO {
    List<Product> loadProducts();
    void saveProducts(List<Product> products);
}
