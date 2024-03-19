package gui;

// Importing the order processing interface
import order.IOrderProcessingService;

// Importing GUI components
import javax.swing.*;
import java.awt.*;

// A class for a mock payment dialog
public class MockPayment extends JDialog {
    // Constructor for the payment dialog
    public MockPayment(Frame owner, IOrderProcessingService orderProcessingService, String orderId) {
        super(owner, "Payment Processing", true); // Setting up the dialog window
        setSize(300, 250); // Setting the size of the dialog
        setLayout(new GridLayout(7, 2)); // Arranging components in a grid

        // Creating text fields for payment information
        JTextField cardNumberField = new JTextField();
        JTextField cardHolderField = new JTextField();
        JTextField expirationDateField = new JTextField("04/2024"); // With a preset example value
        expirationDateField.setEditable(true); // Making sure the user can edit this field
        JTextField cvvField = new JTextField();
        JTextField zipCodeField = new JTextField();

        // Adding labels and fields to the dialog in pairs
        add(new JLabel("Card Number (16 digits):"));
        add(cardNumberField);
        add(new JLabel("Card Holder Name:"));
        add(cardHolderField);
        add(new JLabel("Expiration Date (MM/yyyy):"));
        add(expirationDateField);
        add(new JLabel("CVV (3 digits):"));
        add(cvvField);
        add(new JLabel("Zip Code (6 digits):"));
        add(zipCodeField);

        // Creating a submit button and adding action when clicked
        JButton submitButton = new JButton("Submit Payment");
        submitButton.addActionListener(event -> {
            // Trimming whitespace from input for validation
            String cardNumber = cardNumberField.getText().trim();
            String cardHolder = cardHolderField.getText().trim();
            String cvv = cvvField.getText().trim();
            String zipCode = zipCodeField.getText().trim();

            // Validate the input and if okay, process payment
            String validationError = validatePayment(cardNumber, cardHolder, expirationDateField.getText(), cvv, zipCode);
            if (validationError.isEmpty()) { // If there are no errors
                orderProcessingService.finalizeOrder(orderId); // Finalize the order
                Logger.log("Payment successful for Order ID: " + orderId); // Log success
                JOptionPane.showMessageDialog(this, "Payment Successful!"); // Show success message
                dispose(); // Close the payment window
            } else {
                JOptionPane.showMessageDialog(this, validationError); // Otherwise, show the error
            }
        });

        add(new JLabel()); // Adding an empty label as a filler
        add(submitButton); // Adding the submit button
        setLocationRelativeTo(owner); // Position the dialog relative to the owner frame
    }

    // Method to validate payment details
    private String validatePayment(String cardNumber, String cardHolder, String expirationDate,
                                   String cvv, String zipCode) {
        // Checking if the card number is exactly 16 digits
        if (!cardNumber.matches("\\d{16}")) {
            return "Invalid Card Number. It must be exactly 16 digits.";
        }
        // Checking if the card holder name contains only letters and spaces
        if (!cardHolder.matches("[A-Za-z ]+")) {
            return "Invalid Card Holder Name. It should contain only letters and spaces.";
        }
        // Checking if the CVV is exactly 3 digits
        if (!cvv.matches("\\d{3}")) {
            return "Invalid CVV. It must be exactly 3 digits.";
        }
        // Checking if the zip code is 5 or 6 digits long
        if (!zipCode.matches("\\d{5,6}")) {
            return "Invalid Zip Code. It must be 5 or 6 digits long.";
        }
        return ""; // If all checks pass return an empty string indicating no errors
    }
}