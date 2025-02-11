package storage.dao;

import model.Customer;

import java.util.List;


public interface CustomerDAO {
    List<Customer> getAll();

    Customer getById(int id);

    void save(Customer customer);

    void update(Customer customer);

    void delete(int id);

    void delete(Customer customer);
}
