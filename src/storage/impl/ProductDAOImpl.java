package storage.impl;

import storage.fileHandler.BinaryHandler;
import storage.fileHandler.CSVHandler;
import model.Product;
import storage.dao.ProductDAO;

import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private static final String FILE_PATH = "data/products.dat";
    private static final String CSV_PATH = "data/products.csv";

    @Override
    public List<Product> loadProducts() {
        List<Product> products = BinaryHandler.readFromFile(FILE_PATH);
        return (products != null) ? products : List.of();
    }

    @Override
    public void saveProducts(List<Product> products) {
        BinaryHandler.writeToFile(FILE_PATH, products);
        saveProductsToCSV(products);
    }

    private void saveProductsToCSV(List<Product> products) {
        CSVHandler.writeToCSV(CSV_PATH, products,
                p -> p.getId() + "," + p.getName() + "," + p.getPrice() + "," + p.getStock(),
                "ID,Name,Price,Stock");
    }
}
