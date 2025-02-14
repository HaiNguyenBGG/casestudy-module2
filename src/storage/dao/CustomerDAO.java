package storage.dao;

import model.Customer;

import java.util.List;

public interface CustomerDAO {
    List<Customer> loadCustomers();

    void saveCustomers(List<Customer> customers);
}
