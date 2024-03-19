package gui;

import authentication.AuthenticationService; // Use the correct implementation class
import authentication.IAuthenticationService;
import order.IOrderProcessingService;
import order.OrderProcessingService; // Use the correct implementation class
import products.ProductCatalog;
import products.ProductCatalogInitializer;

import javax.swing.*;
import java.awt.*;

public class MainClass extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);
    private JButton viewCartButton;
    private IOrderProcessingService orderProcessingService; // Use the interface type for the field declaration

    public MainClass() {
        setTitle("Shopping System");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen

        // Initialize the services with their implementation but assign them to interface references
        IAuthenticationService authService = new AuthenticationService(); // Use the implementation class here
        orderProcessingService = new OrderProcessingService(); // Assign the implementation to the interface reference

        // Setup components using the interfaces
        setupComponents(authService, orderProcessingService);

        // Setup view cart button
        setupViewCartButton();

        add(cardPanel, BorderLayout.CENTER);
        add(viewCartButton, BorderLayout.SOUTH); // Example placement
    }

    private void setupComponents(IAuthenticationService authService, IOrderProcessingService orderProcessingService) {
        // Use the authService parameter directly, no need to redeclare it
        ProductCatalog catalog = ProductCatalogInitializer.initializeCatalog();

        LoginPanel loginPanel = new LoginPanel(authService, this::showCatalogPanel);
        CatalogPanel catalogPanel = new CatalogPanel(catalog);

        cardPanel.add(loginPanel, "Login");
        cardPanel.add(catalogPanel, "Catalog");
    }

    private void setupViewCartButton() {
        viewCartButton = new JButton("View Cart");
        viewCartButton.addActionListener(e -> showCartDialog());
        viewCartButton.setVisible(false);
    }

    private void showCartDialog() {
        // Pass the interface reference to CartViewPanel
        JDialog cartDialog = new JDialog(this, "Shopping Cart", true);
        cartDialog.getContentPane().add(new CartViewPanel(orderProcessingService));
        cartDialog.pack();
        cartDialog.setLocationRelativeTo(this);
        cartDialog.setVisible(true);
    }

    public void showCatalogPanel() {
        cardLayout.show(cardPanel, "Catalog");
        add(viewCartButton, BorderLayout.SOUTH); // Moved from constructor
        viewCartButton.setVisible(true); // Make visible upon login
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainClass().setVisible(true));
    }
}