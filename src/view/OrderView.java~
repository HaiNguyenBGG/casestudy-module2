package view;

import facade.StoreFacade;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderView {
    private final StoreFacade storeFacade = StoreFacade.getInstance();
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            System.out.println("\n===== Quản lý Đơn Hàng =====");
            System.out.println("1. Tạo đơn hàng");
            System.out.println("2. Xóa đơn hàng");
            System.out.println("3. Hiển thị danh sách đơn hàng");
            System.out.println("4. Sửa đơn hàng");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createOrder();
                case 2 -> removeOrder();
                case 3 -> displayOrders();
                case 4 -> editOrder();
                case 0 -> {
                    System.out.println("Thoát chương trình.");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private void editOrder() {
        System.out.print("Nhập ID đơn hàng cần sửa: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();

        Order order = storeFacade.getAllOrders().stream()
                .filter(o -> o.getId() == orderId)
                .findFirst()
                .orElse(null);

        if (order == null) {
            System.out.println("Không tìm thấy đơn hàng!");
            return;
        }

        System.out.println("Đơn hàng hiện tại:");
        System.out.println(order);

        System.out.print("Nhập ID sản phẩm cần sửa: ");
        int productId = scanner.nextInt();
        scanner.nextLine();

        OrderDetail orderDetail = order.getOrderDetails().stream()
                .filter(od -> od.getProduct().getId() == productId)
                .findFirst()
                .orElse(null);

        if (orderDetail == null) {
            System.out.println("Không tìm thấy sản phẩm trong đơn hàng!");
            return;
        }

        System.out.print("Nhập số lượng mới: ");
        int newQuantity = scanner.nextInt();
        scanner.nextLine();

        if (newQuantity <= 0) {
            System.out.println("Số lượng phải lớn hơn 0!");
            return;
        }

        orderDetail.setQuantity(newQuantity);
        order.setOrderDetails(order.getOrderDetails());

        storeFacade.updateOrder(order);

        System.out.println("Đơn hàng đã được cập nhật!");
    }


    private void createOrder() {
        System.out.print("Nhập ID đơn hàng: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nhập ID khách hàng: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        Customer customer = storeFacade.getAllCustomers().stream()
                .filter(c -> c.getId() == customerId)
                .findFirst()
                .orElse(null);

        if (customer == null) {
            System.out.println("Không tìm thấy khách hàng!");
            return;
        }

        List<OrderDetail> orderDetails = new ArrayList<>();
        while (true) {
            System.out.print("Nhập ID sản phẩm (0 để dừng): ");
            int productId = scanner.nextInt();
            scanner.nextLine();
            if (productId == 0) break;

            Product product = storeFacade.getAllProducts().stream()
                    .filter(p -> p.getId() == productId)
                    .findFirst()
                    .orElse(null);

            if (product == null) {
                System.out.println("Không tìm thấy sản phẩm với ID: " + productId);
                continue;
            }

            System.out.print("Nhập số lượng: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            if (product.getStock() < quantity) {
                System.out.println("Không đủ hàng trong kho! Số lượng còn lại: " + product.getStock());
                continue;
            }

            product.decreaseStock(quantity);
            orderDetails.add(new OrderDetail(productId, null, product, quantity));
        }

        System.out.print("Nhập ngày đặt hàng (yyyy-MM-dd): ");
        String orderDate = scanner.nextLine();

        System.out.print("Nhập phương thức thanh toán (Tiền mặt/Chuyển khoản): ");
        String paymentMethod = scanner.nextLine();
        Payment payment = new Payment(id, null, orderDetails.stream().mapToDouble(OrderDetail::getSubtotal).sum(), paymentMethod);

        System.out.print("Nhập địa chỉ giao hàng: ");
        String shippingAddress = scanner.nextLine();
        Shipping shipping = new Shipping(id, null, shippingAddress, "Đang chuẩn bị");

        storeFacade.createOrder(id, customer, orderDetails, orderDate, payment, shipping);
        System.out.println("Đơn hàng đã được tạo thành công!");
    }

    private void removeOrder() {
        System.out.print("Nhập ID đơn hàng cần xóa: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        storeFacade.removeOrder(id);
        System.out.println("Đơn hàng đã được xóa thành công!");
    }

    private void displayOrders() {
        List<Order> orders = storeFacade.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("Không có đơn hàng nào trong hệ thống.");
        } else {
            orders.forEach(System.out::println);
        }
    }
}
