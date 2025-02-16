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
            System.out.println("0. Quay lại");
            System.out.print("Chọn chức năng: ");

            int choice = getIntInput();
            switch (choice) {
                case 1 -> addProduct();
                case 2 -> removeProduct();
                case 3 -> displayProducts();
                case 0 -> {
                    System.out.println("Thoát chương trình.");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập lại.");
            }
        }
    }

    private void addProduct() {
        int id;
        String name;
        double price;
        int stock;

        while (true) {
            System.out.print("Nhập ID sản phẩm: ");
            id = getIntInput();
            if (!storeFacade.productExists(id)) {
                break;
            }
            System.out.println("ID sản phẩm đã tồn tại! Vui lòng nhập ID khác.");
        }

        System.out.print("Nhập tên sản phẩm: ");
        name = scanner.nextLine();

        while (true) {
            System.out.print("Nhập giá sản phẩm: ");
            price = getDoubleInput();
            if (price > 0) {
                break;
            }
            System.out.println("Giá sản phẩm phải lớn hơn 0. Vui lòng nhập lại.");
        }

        while (true) {
            System.out.print("Nhập số lượng trong kho: ");
            stock = getIntInput();
            if (stock >= 0) {
                break;
            }
            System.out.println("Số lượng không được âm. Vui lòng nhập lại.");
        }

        storeFacade.addProduct(id, name, price, stock);
        System.out.println("Sản phẩm đã được thêm thành công!");
    }

    private void removeProduct() {
        int id;
        while (true) {
            System.out.print("Nhập ID sản phẩm cần xóa: ");
            id = getIntInput();

            if (storeFacade.productExists(id)) {
                break;
            }
            System.out.println("Không tìm thấy sản phẩm có ID: " + id + ". Vui lòng nhập lại.");
        }

        System.out.print("Bạn có chắc chắn muốn xóa sản phẩm này? (Y/N): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        if (confirm.equals("y")) {
            storeFacade.removeProduct(id);
            System.out.println("Sản phẩm đã được xóa thành công!");
        } else {
            System.out.println("Hủy xóa sản phẩm.");
        }
    }

    private void displayProducts() {
        List<Product> products = storeFacade.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("Không có sản phẩm nào trong hệ thống.");
        } else {
            System.out.println("\nDanh sách sản phẩm:");
            products.forEach(System.out::println);
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

    private double getDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Lỗi: Vui lòng nhập số thực hợp lệ: ");
            }
        }
    }
}
