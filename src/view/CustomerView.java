package view;

import facade.StoreFacade;
import model.Customer;

import java.util.List;
import java.util.Scanner;

public class CustomerView {
    private final StoreFacade storeFacade = StoreFacade.getInstance();
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            System.out.println("\n===== Quản lý Khách Hàng =====");
            System.out.println("1. Thêm khách hàng");
            System.out.println("2. Xóa khách hàng");
            System.out.println("3. Hiển thị danh sách khách hàng");
            System.out.println("0. Quay lại");
            System.out.print("Chọn chức năng: ");

            int choice = getIntInput();
            switch (choice) {
                case 1 -> addCustomer();
                case 2 -> removeCustomer();
                case 3 -> displayCustomers();
                case 0 -> {
                    System.out.println("Thoát chương trình.");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập lại.");
            }
        }
    }

    private void addCustomer() {
        int id;
        String name, email, phone;

        while (true) {
            System.out.print("Nhập ID: ");
            id = getIntInput();
            if (!storeFacade.customerExists(id)) {
                break;
            }
            System.out.println("ID đã tồn tại! Vui lòng nhập ID khác.");
        }

        System.out.print("Nhập tên: ");
        name = scanner.nextLine();

        while (true) {
            System.out.print("Nhập email: ");
            email = scanner.nextLine();
            if (isValidEmail(email)) {
                break;
            }
            System.out.println("Email không hợp lệ! Vui lòng nhập lại.");
        }

        while (true) {
            System.out.print("Nhập số điện thoại: ");
            phone = scanner.nextLine();
            if (isValidPhone(phone)) {
                break;
            }
            System.out.println("Số điện thoại không hợp lệ! Phải có 10 chữ số và bắt đầu bằng số 0.");
        }

        String result = storeFacade.addCustomer(id, name, email, phone);
        System.out.println(result);
    }

    private void removeCustomer() {
        int id;
        while (true) {
            System.out.print("Nhập ID khách hàng cần xóa: ");
            id = getIntInput();

            if (storeFacade.customerExists(id)) {
                break;
            }
            System.out.println("Không tìm thấy khách hàng có ID: " + id + ". Vui lòng nhập lại.");
        }

        System.out.print("Bạn có chắc chắn muốn xóa khách hàng này? (Y/N): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        if (confirm.equals("y")) {
            String result = storeFacade.removeCustomer(id);
            System.out.println(result);
        } else {
            System.out.println("Hủy xóa khách hàng.");
        }
    }

    private void displayCustomers() {
        List<Customer> customers = storeFacade.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("Không có khách hàng nào trong hệ thống.");
        } else {
            System.out.println("\nDanh sách khách hàng:");
            customers.forEach(System.out::println);
        }
    }

    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Lỗi: Vui lòng nhập số nguyên hợp lệ: ");
            }
        }
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
    }

    private boolean isValidPhone(String phone) {
        return phone.matches("^0[0-9]{9}$");
    }
}
