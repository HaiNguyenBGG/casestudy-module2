package manager;

import storage.dao.CustomerDAO;
import storage.impl.CustomerDAOImpl;
import model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CustomerManager {
    private List<Customer> customers;
    private final CustomerDAO customerDAO = new CustomerDAOImpl();

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    private static final String PHONE_REGEX = "^0[0-9]{9}$";

    public CustomerManager() {
        this.customers = new ArrayList<>(customerDAO.loadCustomers());
    }

    public void addCustomer(int id, String name) {
        Scanner scanner = new Scanner(System.in);
        String email, phone;

        while (true) {
            System.out.print("Nhập email: ");
            email = scanner.nextLine().trim();
            if (Pattern.matches(EMAIL_REGEX, email)) break;
            System.out.println("Email không hợp lệ! Vui lòng nhập lại.");
        }

        while (true) {
            System.out.print("Nhập số điện thoại: ");
            phone = scanner.nextLine().trim();
            if (Pattern.matches(PHONE_REGEX, phone)) break;
            System.out.println("Số điện thoại không hợp lệ! Vui lòng nhập lại.");
        }

        customers.add(new Customer(id, name, email, phone));
        customerDAO.saveCustomers(customers);
    }

    public void removeCustomer(int id) {
        customers.removeIf(c -> c.getId() == id);
        customerDAO.saveCustomers(customers);
        System.out.println("Khách hàng đã được xóa!");
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public void saveCustomers() {
        customerDAO.saveCustomers(customers);
    }
}
