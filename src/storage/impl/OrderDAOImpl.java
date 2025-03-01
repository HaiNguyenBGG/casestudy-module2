package storage.impl;

import storage.fileHandler.BinaryHandler;
import storage.fileHandler.CSVHandler;
import model.Order;
import model.Customer;
import storage.dao.OrderDAO;
import storage.dao.CustomerDAO;
//import storage.impl.CustomerDAOImpl;

import java.util.List;
import java.util.Collections;

public class OrderDAOImpl implements OrderDAO {
    private static final String FILE_PATH = "data/orders.dat";
    private static final String CSV_PATH = "data/orders.csv";

    private final CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public List<Order> loadOrders() {
        List<Order> orders = BinaryHandler.readFromFile(FILE_PATH);
        return (orders != null) ? orders : Collections.emptyList();
    }

    @Override
    public void saveOrders(List<Order> orders) {
        BinaryHandler.writeToFile(FILE_PATH, orders);
        saveOrdersToCSV(orders);
    }

    private void saveOrdersToCSV(List<Order> orders) {
        CSVHandler.writeToCSV(CSV_PATH, orders,
                o -> o.getId() + "," + o.getCustomer().getId() + "," + o.getOrderDate() + "," + o.getTotalPrice(),
                "ID,CustomerID,OrderDate,TotalPrice");
    }

    public List<Order> loadOrdersFromCSV() {
        return CSVHandler.readFromCSV(CSV_PATH,
                data -> new Order(Integer.parseInt(data[0]),
                        getCustomerById(Integer.parseInt(data[1])),
                        List.of(), data[2], null, null));
    }

    private Customer getCustomerById(int id) {
        return customerDAO.loadCustomers().stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateOrder(Order order) {
        List<Order> orders = loadOrders();
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == order.getId()) {
                orders.set(i, order);
                break;
            }
        }
        saveOrders(orders);
    }

}
