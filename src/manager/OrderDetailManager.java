package manager;

import storage.dao.OrderDetailDAO;
import storage.impl.OrderDetailDAOImpl;
import model.OrderDetail;
import model.Order;
import model.Product;
import storage.utils.LogService;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailManager {
    private final List<OrderDetail> orderDetails;
    private final OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();

    public OrderDetailManager() {
        this.orderDetails = new ArrayList<>(orderDetailDAO.loadOrderDetails());
    }

    public void saveOrderDetails() {
        orderDetailDAO.saveOrderDetails(orderDetails);
    }

    public void addOrderDetail(int id, Order order, Product product, int quantity) {
        try {
            if (orderDetails.stream().anyMatch(od -> od.getId() == id)) {
                throw new IllegalArgumentException("ID chi tiết đơn hàng đã tồn tại! Hãy nhập ID khác.");
            }
            if (product.getStock() < quantity) {
                throw new IllegalArgumentException("Không đủ hàng trong kho! Còn lại: " + product.getStock());
            }
            product.setStock(product.getStock() - quantity);
            OrderDetail orderDetail = new OrderDetail(id, order, product, quantity);
            order.addOrderDetail(orderDetail);
            orderDetails.add(orderDetail);
            orderDetailDAO.saveOrderDetails(orderDetails);

            LogService.log("Đã thêm chi tiết đơn hàng: " + product.getName() + " | SL: " + quantity);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeOrderDetail(int id) {
        OrderDetail orderDetail = orderDetails.stream()
                .filter(od -> od.getId() == id)
                .findFirst()
                .orElse(null);
        if (orderDetail != null) {
            orderDetail.getOrder().removeOrderDetail(id);
            orderDetails.remove(orderDetail);
            orderDetailDAO.saveOrderDetails(orderDetails);
            System.out.println("Chi tiết đơn hàng đã được xóa và tổng giá trị cập nhật.");
        } else {
            System.out.println("Không tìm thấy chi tiết đơn hàng.");
        }
    }

    public void updateOrderDetail(OrderDetail orderDetail) {
        for (int i = 0; i < orderDetails.size(); i++) {
            if (orderDetails.get(i).getId() == orderDetail.getId()) {
                orderDetails.set(i, orderDetail);
                orderDetailDAO.saveOrderDetails(orderDetails);
                System.out.println("Đã cập nhật chi tiết đơn hàng.");
                return;
            }
        }
        System.out.println("Không tìm thấy chi tiết đơn hàng cần cập nhật.");
    }

    public List<OrderDetail> getAllOrderDetails() {
        return orderDetails;
    }
}
