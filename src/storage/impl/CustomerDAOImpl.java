package storage.impl;

import storage.fileHandler.BinaryHandler;
import storage.fileHandler.CSVHandler;
import model.Customer;
import storage.dao.CustomerDAO;

import java.util.List;
import java.util.Collections;

public class CustomerDAOImpl implements CustomerDAO {
    private static final String FILE_PATH = "data/customers.dat";
    private static final String CSV_PATH = "data/customers.csv";

    @Override
    public List<Customer> loadCustomers() {
        List<Customer> customers = BinaryHandler.readFromFile(FILE_PATH);
        return (customers != null) ? customers : Collections.emptyList();
    }

    @Override
    public void saveCustomers(List<Customer> customers) {
        BinaryHandler.writeToFile(FILE_PATH, customers);
        saveCustomersToCSV(customers);
    }

    private void saveCustomersToCSV(List<Customer> customers) {
        CSVHandler.writeToCSV(CSV_PATH, customers,
                c -> c.getId() + "," + c.getName() + "," + c.getEmail() + "," + c.getPhone(),
                "ID,Name,Email,Phone");
    }

    public List<Customer> loadCustomersFromCSV() {
        return CSVHandler.readFromCSV(CSV_PATH,
                data -> new Customer(Integer.parseInt(data[0]), data[1], data[2], data[3]));
    }
}
