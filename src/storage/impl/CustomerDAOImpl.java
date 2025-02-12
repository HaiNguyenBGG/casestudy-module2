package storage.impl;

import model.Customer;
import storage.dao.CustomerDAO;
import storage.fileHandler.BinaryHandler;
import storage.fileHandler.CSVHandler;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    private static final String FILE_PATH = "data/customers.dat";
    private static final String CSV_PATH = "data/customers.csv";

    private List<Customer> customers;

    public CustomerDAOImpl() {
        this.customers = loadCustomers();
    }

    @Override
    public List<Customer> getAll() {
        return customers;
    }

    @Override
    public Customer getById(int id) {
        return customers.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void save(Customer customer) {
        customers.add(customer);
        saveCustomers();
    }

    @Override
    public void update(Customer customer) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == customer.getId()) {
                customers.set(i, customer);
                saveCustomers();
                return;
            }
        }
    }

    @Override
    public void delete(int id) {
        customers.removeIf(c -> c.getId() == id);
        saveCustomers();
    }

    @Override
    public void delete(Customer customer) {
        customers.remove(customer);
        saveCustomers();
    }

    public List<Customer> loadCustomers() {
        List<Customer> data = BinaryHandler.readFromFile(FILE_PATH);
        return (data != null) ? data : new ArrayList<>();
    }

    public void saveCustomers() {
        BinaryHandler.writeToFile(FILE_PATH, customers);
    }

    public void saveCustomersToCSV() {
        CSVHandler.writeToCSV(CSV_PATH, customers,
                c -> c.getId() + "," + c.getName() + "," + c.getEmail() + "," + c.getPhone(),
                "ID,Name,Email,Phone");
    }
}
