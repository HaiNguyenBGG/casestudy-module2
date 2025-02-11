package manager;

import model.Customer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CustomerManager {
    private List<Customer> customers = new ArrayList<>();
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    private static final String PHONE_REGEX = "^0[0-9]{9}$";

    public void addCustomer(int id, String name, String email, String phone) {
        if (!Pattern.matches(EMAIL_REGEX, email)) {
            throw new IllegalArgumentException("Email không hợp lệ!");
        }
        if (!Pattern.matches(PHONE_REGEX, phone)) {
            throw new IllegalArgumentException("Số điện thoại không hợp lệ! Phải có 10 chữ số và bắt đầu bằng số 0.");
        }
        customers.add(new Customer(id, name, email, phone));
        System.out.println("Khách hàng đã được thêm thành công!");
    }

    public void removeCustomer(int id) {
        customers.removeIf(c -> c.getId() == id);
        System.out.println("Khách hàng đã được xóa!");
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }
}
