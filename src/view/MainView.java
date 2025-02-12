package view;

import manager.CustomerManager;

import model.Customer;

public class MainView {
    public static void main(String[] args) {
        CustomerManager customerManager = new CustomerManager();

        // Thêm khách hàng mới
        customerManager.addCustomer(1, "Nguyễn Văn A", "nguyenvana@gmail.com", "0123456789");
        customerManager.addCustomer(2, "Trần Thị B", "tranthib@gmail.com", "0987654321");

        // In danh sách khách hàng
        System.out.println("📌 Danh sách khách hàng:");
        customerManager.getAllCustomers().forEach(System.out::println);

        // Xóa khách hàng
        customerManager.removeCustomer(1);

        // In lại danh sách
        System.out.println("📌 Danh sách sau khi xóa:");
        customerManager.getAllCustomers().forEach(System.out::println);
    }
}
