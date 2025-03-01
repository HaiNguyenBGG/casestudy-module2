package storage.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogService {
    private static final String LOG_FILE = "logs/app.log";
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void log(String message) {
        executor.submit(() -> {
            try (PrintWriter out = new PrintWriter(new FileWriter(LOG_FILE, true))) {
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                out.println(timestamp + " | " + message);
            } catch (IOException e) {
                System.err.println("Lỗi ghi log: " + e.getMessage());
            }
        });
    }

    public static void shutdown() {
        executor.shutdown();
    }
}
