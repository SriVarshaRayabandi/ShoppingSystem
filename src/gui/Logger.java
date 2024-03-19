package gui;

// Importing necessary Java classes for file writing and handling dates
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    // Defining a constant formatter for dates to ensure all dates in logs are uniform
    // This pattern includes year, month, day, hour, minute, and second.
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Defining the location of the log file as a constant. This is where all log messages will be saved
    private static final String LOG_FILE = "logs.txt";

    // Define a public static method log that takes a string message as input
    public static void log(String message) {
        // Get the current date and time format it using the defined formatter and store it in timestamp
        String timestamp = LocalDateTime.now().format(formatter);

        // Combine the timestamp and the message to create the full log message
        String logMessage = timestamp + " - " + message;

        // Print the log message to the console
        System.out.println(logMessage);

        try (
                // Create a PrintWriter that appends to the file without overwriting existing content
                PrintWriter out = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            // Write the log message to the file
            out.println(logMessage);
        } catch (IOException e) {
            // If an error occurs during file writing printing an error message to the console
            System.err.println("An error occurred while writing to the log file: " + e.getMessage());
        }
    }
}