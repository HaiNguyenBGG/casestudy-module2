package view;

import facade.StoreFacade;
import model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderView {
    private final StoreFacade storeFacade = StoreFacade.getInstance();
    private final Scanner scanner = new Scanner(System.in);
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public void showMenu() {
        while (true) {
            System.out.println("\n===== Quản lý Đơn Hàng =====");
            System.out.println("1. Tạo đơn hàng");
            System.out.println("2. Xóa đơn hàng");
            System.out.println("3. Hiển thị danh sách đơn hàng");
            System.out.println("4. Sửa đơn hàng");
            System.out.println("0. Quay lại");
            System.out.print("Chọn chức năng: ");
            int choice = getIntInput();

            switch (choice) {
                case 1 -> createOrder();
                case 2 -> removeOrder();
                case 3 -> displayOrders();
                case 4 -> editOrder();
                case 0 -> {
                    System.out.println("Thoát chương trình.");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập lại.");
            }
        }
    }

    private void createOrder() {
        System.out.print("Nhập ID đơn hàng: ");
        int id = getIntInput();

        System.out.print("Nhập ID khách hàng: ");
        int customerId = getIntInput();

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
            int productId = getIntInput();
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
            int quantity = getIntInput();

            if (product.getStock() < quantity) {
                System.out.println("Không đủ hàng trong kho! Số lượng còn lại: " + product.getStock());
                continue;
            }

            product.decreaseStock(quantity);
            orderDetails.add(new OrderDetail(productId, null, product, quantity));
        }

        String orderDate;
        while (true) {
            System.out.print("Nhập ngày đặt hàng (yyyy-MM-dd): ");
            orderDate = scanner.nextLine();

            if (isValidDate(orderDate)) {
                break;
            }
            System.out.println("Ngày không hợp lệ! Vui lòng nhập lại.");
        }

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
        int id = getIntInput();
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

    private void editOrder() {
        System.out.print("Nhập ID đơn hàng cần sửa: ");
        int orderId = getIntInput();

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
        int productId = getIntInput();

        OrderDetail orderDetail = order.getOrderDetails().stream()
                .filter(od -> od.getProduct().getId() == productId)
                .findFirst()
                .orElse(null);

        if (orderDetail == null) {
            System.out.println("Không tìm thấy sản phẩm trong đơn hàng!");
            return;
        }

        System.out.print("Nhập số lượng mới: ");
        int newQuantity = getIntInput();

        if (newQuantity <= 0) {
            System.out.println("Số lượng phải lớn hơn 0!");
            return;
        }

        orderDetail.setQuantity(newQuantity);
        storeFacade.updateOrder(order);

        System.out.println("Đơn hàng đã được cập nhật!");
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

    private boolean isValidDate(String date) {
        try {
            dateFormat.setLenient(false);
            dateFormat.parse(date);

            int month = Integer.parseInt(date.split("-")[1]);
            if (month < 1 || month > 12) {
                return false;
            }

            return true;
        } catch (ParseException | NumberFormatException e) {
            return false;
        }
    }
}
