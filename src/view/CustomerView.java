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
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addCustomer();
                case 2 -> removeCustomer();
                case 3 -> displayCustomers();
                case 0 -> {
                    System.out.println("Thoát chương trình.");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private void addCustomer() {
        System.out.print("Nhập ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        List<Customer> customers = storeFacade.getAllCustomers();
        if (customers.stream().anyMatch(c -> c.getId() == id)) {
            System.out.println("ID khách hàng đã tồn tại! Vui lòng nhập ID khác.");
            return;
        }
        System.out.print("Nhập tên: ");
        String name = scanner.nextLine();
        System.out.print("Nhập email: ");
        String email = scanner.nextLine();
        System.out.print("Nhập số điện thoại: ");
        String phone = scanner.nextLine();

        storeFacade.addCustomer(id, name, email, phone);
        System.out.println("Thêm khách hàng thành công!");
    }

    private void removeCustomer() {
        System.out.print("Nhập ID khách hàng cần xóa: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        storeFacade.removeCustomer(id);
        System.out.println("Xóa khách hàng thành công!");
    }

    private void displayCustomers() {
        List<Customer> customers = storeFacade.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("Không có khách hàng nào trong hệ thống.");
        } else {
            customers.forEach(System.out::println);
        }
    }
}
