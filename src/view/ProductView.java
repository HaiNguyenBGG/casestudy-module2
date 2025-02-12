package view;

import facade.StoreFacade;
import model.Product;

import java.util.List;
import java.util.Scanner;

public class ProductView {
    private final StoreFacade storeFacade = StoreFacade.getInstance();
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            System.out.println("\n===== Quản lý Sản Phẩm =====");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Xóa sản phẩm");
            System.out.println("3. Hiển thị danh sách sản phẩm");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> removeProduct();
                case 3 -> displayProducts();
                case 0 -> {
                    System.out.println("Thoát chương trình.");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private void addProduct() {
        System.out.print("Nhập ID sản phẩm: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nhập tên sản phẩm: ");
        String name = scanner.nextLine();
        System.out.print("Nhập giá sản phẩm: ");
        double price = scanner.nextDouble();
        System.out.print("Nhập số lượng trong kho: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        storeFacade.addProduct(id, name, price, stock);
        System.out.println("✅ Sản phẩm đã được thêm thành công!");
    }

    private void removeProduct() {
        System.out.print("Nhập ID sản phẩm cần xóa: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        storeFacade.removeOrder(id);
        System.out.println("✅ Sản phẩm đã được xóa thành công!");
    }

    private void displayProducts() {
        storeFacade.viewProducts();
    }
}
