package storage.dao;

import model.Order;
import java.util.List;

public interface OrderDAO {
    List<Order> loadOrders();
    void saveOrders(List<Order> orders);
}
