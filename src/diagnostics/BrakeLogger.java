package diagnostics;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Simple logger for diagnosing and recording brake control system execution processes.
 */
public class BrakeLogger {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public void logInfo(String message) {
        print("INFO", message);
    }

    public void logWarning(String message) {
        print("WARN", message);
    }

    public void logError(String message) {
        print("ERROR", message);
    }

    private void print(String level, String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.out.printf("[%s] [%s] %s%n", timestamp, level, message);
    }
}
