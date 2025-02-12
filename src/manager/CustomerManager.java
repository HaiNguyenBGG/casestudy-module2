package manager;

import storage.impl.CustomerDAOImpl;
import model.Customer;

import java.util.List;
import java.util.regex.Pattern;

public class CustomerManager {
    private List<Customer> customers;
    private final CustomerDAOImpl customerDAO = new CustomerDAOImpl();
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    private static final String PHONE_REGEX = "^0[0-9]{9}$";

    public CustomerManager() {
        this.customers = customerDAO.getAll(); // Gọi getAll() thay vì loadCustomers()
    }

    public void addCustomer(int id, String name, String email, String phone) {
        if (!Pattern.matches(EMAIL_REGEX, email)) {
            throw new IllegalArgumentException("Email không hợp lệ!");
        }
        if (!Pattern.matches(PHONE_REGEX, phone)) {
            throw new IllegalArgumentException("Số điện thoại không hợp lệ! Phải có 10 chữ số và bắt đầu bằng số 0.");
        }

        customers.add(new Customer(id, name, email, phone));
        customerDAO.saveCustomers();
        customerDAO.saveCustomersToCSV();
        System.out.println("Khách hàng đã được thêm thành công!");
    }

    public void removeCustomer(int id) {
        customers.removeIf(c -> c.getId() == id);
        customerDAO.saveCustomers();
        customerDAO.saveCustomersToCSV();
        System.out.println("Khách hàng đã được xóa!");
    }

    public void viewCustomers() {
        if (customers.isEmpty()) {
            System.out.println("Không có khách hàng nào.");
        } else {
            customers.forEach(System.out::println);
        }
    }
}
