package storage.impl;

import storage.fileHandler.BinaryHandler;
import storage.fileHandler.CSVHandler;
import model.OrderDetail;
import model.Order;
import model.Product;
import storage.dao.OrderDetailDAO;
import storage.dao.OrderDAO;
import storage.dao.ProductDAO;
//import storage.impl.OrderDAOImpl;
//import storage.impl.ProductDAOImpl;

import java.util.List;
import java.util.Collections;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    private static final String FILE_PATH = "data/order_details.dat";
    private static final String CSV_PATH = "data/order_details.csv";

    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final ProductDAO productDAO = new ProductDAOImpl();

    @Override
    public List<OrderDetail> loadOrderDetails() {
        List<OrderDetail> orderDetails = BinaryHandler.readFromFile(FILE_PATH);
        return (orderDetails != null) ? orderDetails : Collections.emptyList();
    }

    @Override
    public void saveOrderDetails(List<OrderDetail> orderDetails) {
        BinaryHandler.writeToFile(FILE_PATH, orderDetails);
        saveOrderDetailsToCSV(orderDetails);
    }

    private void saveOrderDetailsToCSV(List<OrderDetail> orderDetails) {
        CSVHandler.writeToCSV(CSV_PATH, orderDetails,
                od -> od.getId() + "," + od.getOrder().getId() + "," + od.getProduct().getId() + "," + od.getQuantity() + "," + od.getSubtotal(),
                "ID,OrderID,ProductID,Quantity,Subtotal");
    }

    @Override
    public void updateOrderDetail(OrderDetail orderDetail) {
        List<OrderDetail> orderDetails = loadOrderDetails();
        for (int i = 0; i < orderDetails.size(); i++) {
            if (orderDetails.get(i).getId() == orderDetail.getId()) {
                orderDetails.set(i, orderDetail);
                break;
            }
        }
        saveOrderDetails(orderDetails);
    }

    public List<OrderDetail> loadOrderDetailsFromCSV() {
        return CSVHandler.readFromCSV(CSV_PATH, data -> new OrderDetail(
                Integer.parseInt(data[0]),
                getOrderById(Integer.parseInt(data[1])),
                getProductById(Integer.parseInt(data[2])),
                Integer.parseInt(data[3])
        ));
    }

    private Order getOrderById(int id) {
        return orderDAO.loadOrders().stream()
                .filter(o -> o.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private Product getProductById(int id) {
        return productDAO.loadProducts().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
