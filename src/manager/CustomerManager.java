package manager;

import storage.dao.CustomerDAO;
import storage.impl.CustomerDAOImpl;
import model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CustomerManager {
    private List<Customer> customers;
    private final CustomerDAO customerDAO = new CustomerDAOImpl();

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    private static final String PHONE_REGEX = "^0[0-9]{9}$";

    public CustomerManager() {
        this.customers = new ArrayList<>(customerDAO.loadCustomers());
    }

    public String addCustomer(int id, String name, String email, String phone) {
        if (!Pattern.matches(EMAIL_REGEX, email)) {
            return "Email không hợp lệ!";
        }
        if (!Pattern.matches(PHONE_REGEX, phone)) {
            return "Số điện thoại không hợp lệ! Phải có 10 chữ số và bắt đầu bằng số 0.";
        }
        if (customerExists(id)) {
            return "ID khách hàng đã tồn tại!";
        }
        customers.add(new Customer(id, name, email, phone));
        customerDAO.saveCustomers(customers);
        return "Thêm khách hàng thành công!";
    }

    public String removeCustomer(int id) {
        Customer customer = getCustomerById(id);
        if (customer == null) {
            return "Không tìm thấy khách hàng với ID: " + id;
        }
        customers.remove(customer);
        customerDAO.saveCustomers(customers);
        return "Xóa khách hàng thành công!";
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }

    public Customer getCustomerById(int id) {
        return customers.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public boolean customerExists(int id) {
        return customers.stream().anyMatch(c -> c.getId() == id);
    }
    public void saveCustomers() {
        customerDAO.saveCustomers(customers);
    }
}
