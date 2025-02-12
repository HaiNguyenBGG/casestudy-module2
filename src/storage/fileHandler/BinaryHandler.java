package storage.fileHandler;

import java.io.*;
import java.util.List;

public class BinaryHandler {
    public static <T> void writeToFile(String filePath, List<T> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(data);
            System.out.println("Đã lưu dữ liệu vào file Nhị phân: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> readFromFile(String filePath) {
        List<T> data = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            data = (List<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Chưa có dữ liệu trong file: " + filePath);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
}
