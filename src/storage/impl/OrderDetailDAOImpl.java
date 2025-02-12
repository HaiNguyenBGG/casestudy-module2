package storage.impl;

import model.OrderDetail;
import storage.dao.OrderDetailDAO;
import storage.fileHandler.BinaryHandler;
import storage.fileHandler.CSVHandler;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    private static final String FILE_PATH = "data/orderDetails.dat";
    private static final String CSV_PATH = "data/orderDetails.csv";
    private List<OrderDetail> orderDetails;

    public OrderDetailDAOImpl() {
        this.orderDetails = loadOrderDetails();
    }

    @Override
    public List<OrderDetail> getAll() {
        return orderDetails;
    }

    @Override
    public OrderDetail getById(int id) {
        return orderDetails.stream().filter(od -> od.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void save(OrderDetail orderDetail) {
        orderDetails.add(orderDetail);
        saveOrderDetails();
        saveOrderDetailsToCSV();
    }

    @Override
    public void update(OrderDetail orderDetail) {
        for (int i = 0; i < orderDetails.size(); i++) {
            if (orderDetails.get(i).getId() == orderDetail.getId()) {
                orderDetails.set(i, orderDetail);
                saveOrderDetails();
                saveOrderDetailsToCSV();
                return;
            }
        }
    }

    @Override
    public void delete(int id) {
        orderDetails.removeIf(od -> od.getId() == id);
        saveOrderDetails();
        saveOrderDetailsToCSV();
    }

    private List<OrderDetail> loadOrderDetails() {
        List<OrderDetail> data = BinaryHandler.readFromFile(FILE_PATH);
        return (data != null) ? data : new ArrayList<>();
    }

    public void saveOrderDetails() {
        BinaryHandler.writeToFile(FILE_PATH, orderDetails);
    }

    public void saveOrderDetailsToCSV() {
        System.out.println("Đang lưu chi tiết đơn hàng vào CSV...");
        CSVHandler.writeToCSV(CSV_PATH, orderDetails,
                od -> od.getId() + "," + od.getOrderId() + "," + od.getProduct().getName() + "," + od.getQuantity() + "," + od.getPrice(),
                "ID,Order ID,Product Name,Quantity,Price");
    }
}
