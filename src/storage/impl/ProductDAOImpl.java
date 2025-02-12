package storage.impl;

import model.Product;
import storage.dao.ProductDAO;
import storage.fileHandler.BinaryHandler;
import storage.fileHandler.CSVHandler;

import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private static final String FILE_PATH = "data/products.dat";
    private static final String CSV_PATH = "data/products.csv";
    private List<Product> products;

    public ProductDAOImpl() {
        this.products = loadProducts();
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public Product getProductById(int id) {
        return products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
        saveProducts();
        saveProductsToCSV();
    }

    @Override
    public void updateProduct(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == product.getId()) {
                products.set(i, product);
                saveProducts();
                saveProductsToCSV();
                return;
            }
        }
    }

    @Override
    public void deleteProduct(int id) {
        products.removeIf(p -> p.getId() == id);
        saveProducts();
        saveProductsToCSV();
    }

    private List<Product> loadProducts() {
        List<Product> data = BinaryHandler.readFromFile(FILE_PATH);
        return (data != null) ? data : new ArrayList<>();
    }

    public void saveProducts() {
        BinaryHandler.writeToFile(FILE_PATH, products);
    }

    public void saveProductsToCSV() {
        CSVHandler.writeToCSV(CSV_PATH, products,
                p -> p.getId() + "," + p.getName() + "," + p.getPrice() + "," + p.getStock(),
                "ID,Name,Price,Stock");
    }
}
