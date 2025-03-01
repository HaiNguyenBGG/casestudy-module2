package storage.utils;

import java.util.Timer;
import java.util.TimerTask;

import manager.OrderManager;
import manager.CustomerManager;
import manager.ProductManager;

public class AutoSaveService {
    private static final long SAVE_INTERVAL = 30000;
    private final Timer timer;
    private final OrderManager orderManager;
    private final CustomerManager customerManager;
    private final ProductManager productManager;

    public AutoSaveService(OrderManager orderManager, CustomerManager customerManager, ProductManager productManager) {
        this.orderManager = orderManager;
        this.customerManager = customerManager;
        this.productManager = productManager;
        this.timer = new Timer(true);
    }

    public void startAutoSave() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                saveData();
            }
        }, SAVE_INTERVAL, SAVE_INTERVAL);
    }

    private void saveData() {
//        System.out.println("Đang tự động lưu dữ liệu...");
        orderManager.saveOrders();
        customerManager.saveCustomers();
        productManager.saveProducts();
//        System.out.println("Dữ liệu đã được lưu tự động.");
    }

    public void stopAutoSave() {
        timer.cancel();
//        System.out.println("Đã dừng tự động lưu.");
    }
}
