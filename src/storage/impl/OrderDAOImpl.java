package storage.impl;

import model.Order;
import storage.dao.OrderDAO;
import storage.fileHandler.BinaryHandler;
import storage.fileHandler.CSVHandler;

import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private static final String FILE_PATH = "data/orders.dat";
    private static final String CSV_PATH = "data/orders.csv";
    private List<Order> orders;

    public OrderDAOImpl() {
        this.orders = loadOrders();
    }

    @Override
    public List<Order> getAll() {
        return orders;
    }

    @Override
    public Order getById(int id) {
        return orders.stream().filter(o -> o.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void save(Order order) {
        orders.add(order);
        saveOrders();
        saveOrdersToCSV();
    }

    @Override
    public void update(Order order) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == order.getId()) {
                orders.set(i, order);
                saveOrders();
                saveOrdersToCSV();
                return;
            }
        }
    }

    @Override
    public void delete(int id) {
        orders.removeIf(o -> o.getId() == id);
        saveOrders();
        saveOrdersToCSV();
    }

    private List<Order> loadOrders() {
        List<Order> data = BinaryHandler.readFromFile(FILE_PATH);
        return (data != null) ? data : new ArrayList<>();
    }

    public void saveOrders() {
        BinaryHandler.writeToFile(FILE_PATH, orders);
    }

    public void saveOrdersToCSV() {
        System.out.println("Đang lưu đơn hàng vào CSV...");
        CSVHandler.writeToCSV(CSV_PATH, orders,
                o -> o.getId() + "," + o.getCustomer().getName() + "," + o.getTotalPrice() + "," + o.getOrderDate() + "," + o.getPayment().getStatus() + "," + o.getShipping().getStatus(),
                "ID,Customer Name,Total Price,Order Date,Payment Status,Shipping Status");
    }
}
