package gui;

import order.IOrderProcessingService;

import javax.swing.*;
import java.awt.*;

public class MockPayment extends JDialog {
    public MockPayment(Frame owner, IOrderProcessingService orderProcessingService, String orderId) {
        super(owner, "Payment Processing", true);
        setSize(300, 250);
        setLayout(new GridLayout(7, 2));

        JTextField cardNumberField = new JTextField();
        JTextField cardHolderField = new JTextField();
        JTextField expirationDateField = new JTextField("04/2024");
        expirationDateField.setEditable(true);
        JTextField cvvField = new JTextField();
        JTextField zipCodeField = new JTextField();

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

        JButton submitButton = new JButton("Submit Payment");
        submitButton.addActionListener(event -> {
            // Trimming whitespace from input
            String cardNumber = cardNumberField.getText().trim();
            String cardHolder = cardHolderField.getText().trim();
            String cvv = cvvField.getText().trim();
            String zipCode = zipCodeField.getText().trim();

            // Validate input and process payment
            String validationError = validatePayment(cardNumber, cardHolder, expirationDateField.getText(), cvv, zipCode);
            if (validationError.isEmpty()) {
                orderProcessingService.finalizeOrder(orderId);
                Logger.log("Payment successful for Order ID: " + orderId);
                JOptionPane.showMessageDialog(this, "Payment Successful!");
                dispose(); // Close the payment window
            } else {
                JOptionPane.showMessageDialog(this, validationError);
            }
        });

        add(new JLabel()); // Filler
        add(submitButton);
        setLocationRelativeTo(owner);
    }

    private String validatePayment(String cardNumber, String cardHolder, String expirationDate,
                                   String cvv, String zipCode) {
        if (!cardNumber.matches("\\d{16}")) {
            return "Invalid Card Number. It must be exactly 16 digits.";
        }
        if (!cardHolder.matches("[A-Za-z ]+")) {
            return "Invalid Card Holder Name. It should contain only letters and spaces.";
        }
        if (!cvv.matches("\\d{3}")) {
            return "Invalid CVV. It must be exactly 3 digits.";
        }
        if (!zipCode.matches("\\d{5,6}")) {
            return "Invalid Zip Code. It must be 5 or 6 digits long.";
        }
        return "";
    }
}