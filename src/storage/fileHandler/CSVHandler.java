package storage.fileHandler;

import java.io.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CSVHandler {
    public static <T> void writeToCSV(String filePath, List<T> data, Function<T, String> toCSVFormat, String header) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(header + "\n");
            for (T item : data) {
                writer.write(toCSVFormat.apply(item) + "\n");
            }
            System.out.println("Đã lưu dữ liệu vào CSV: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> readFromCSV(String filePath, Function<String[], T> parseCSV) {
        List<T> data = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            data = reader.lines().skip(1)
                    .map(line -> line.split(","))
                    .map(parseCSV)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
