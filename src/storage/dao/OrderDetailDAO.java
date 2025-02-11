package storage.dao;

import model.OrderDetail;

import java.util.List;

public interface OrderDetailDAO {
    List<OrderDetail> getOrderDetailByOrderId(int orderId);

    OrderDetail getOrderDetailById(int id);

    void insertOrderDetail(OrderDetail orderDetail);

    void updateOrderDetail(OrderDetail orderDetail);

    void deleteOrderDetail(int id);
}
