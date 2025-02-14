package storage.dao;

import model.OrderDetail;

import java.util.List;

public interface OrderDetailDAO {
    List<OrderDetail> loadOrderDetails();

    void saveOrderDetails(List<OrderDetail> orderDetails);

    void updateOrderDetail(OrderDetail orderDetail);
}
