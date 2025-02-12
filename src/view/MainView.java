package view;

import facade.StoreFacade;

import java.util.Scanner;

public class MainView {
    private final StoreFacade storeFacade = StoreFacade.getInstance();
    private final CustomerView customerView = new CustomerView();
    private final OrderView orderView = new OrderView();
    private final ProductView productView = new ProductView();
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            System.out.println("\n===== HỆ THỐNG QUẢN LÝ CỬA HÀNG =====");
            System.out.println("1. Quản lý khách hàng");
            System.out.println("2. Quản lý đơn hàng");
            System.out.println("3. Quản lý sản phẩm");
            System.out.println("0. Thoát chương trình");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> customerView.showMenu();
                case 2 -> orderView.showMenu();
                case 3 -> productView.showMenu();
                case 0 -> {
                    System.out.println("Cảm ơn bạn đã sử dụng hệ thống!");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
            }
        }
    }

    public static void main(String[] args) {
        MainView mainView = new MainView();
        mainView.showMenu();
    }
}
