package gui;

// Importing necessary classes from other packages
import authentication.AuthenticationService;
import authentication.IAuthenticationService;
import order.IOrderProcessingService;
import order.OrderProcessingService;
import products.ProductCatalog;
import products.ProductCatalogInitializer;

// Importing classes for building the GUI
import javax.swing.*;
import java.awt.*;

// Creating the main class which is a type of window (JFrame)
public class MainClass extends JFrame {
    // Using CardLayout to switch between different panels like login and ccatalog
    private CardLayout cardLayout = new CardLayout();
    // This is the main panel where we will add other panels and switch between them
    private JPanel cardPanel = new JPanel(cardLayout);
    private JButton viewCartButton;
    // Interface for order processing service allows for flexibility in implementation
    private IOrderProcessingService orderProcessingService;

    // Constructor for the main class sets up the window
    public MainClass() {
        setTitle("Shopping System"); // Set the title of the window
        setSize(800, 400); // Set the size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // To make sure the application closes when you close the window
        setLocationRelativeTo(null); // Center the window on the screen

        // Create the authentication and order processing services and assign them to interface references
        IAuthenticationService authService = new AuthenticationService();
        orderProcessingService = new OrderProcessingService();

        // Setting up the GUI components using the created services
        setupComponents(authService, orderProcessingService);

        // Setting up the view cart button but will be shown only after we login successfully
        setupViewCartButton();

        // Add the main panel and the view cart button to the window
        add(cardPanel, BorderLayout.CENTER);
        add(viewCartButton, BorderLayout.SOUTH);
    }

    // Method to set up GUI components like login and catalog panels
    private void setupComponents(IAuthenticationService authService, IOrderProcessingService orderProcessingService) {
        // Initialize the product catalog
        ProductCatalog catalog = ProductCatalogInitializer.initializeCatalog();

        // Create the login and catalog panels and add them to the card panel
        LoginPanel loginPanel = new LoginPanel(authService, this::showCatalogPanel);
        CatalogPanel catalogPanel = new CatalogPanel(catalog);

        cardPanel.add(loginPanel, "Login");
        cardPanel.add(catalogPanel, "Catalog");
    }

    // Method to set up the view cart button
    private void setupViewCartButton() {
        viewCartButton = new JButton("View Cart");
        // Add action listener to show the shopping cart when clicked
        viewCartButton.addActionListener(e -> showCartDialog());
        // Initially this button will not be shown until the user is logged in
        viewCartButton.setVisible(false);
    }

    // Method to switch to the catalog panel and make the view cart button visible
    public void showCatalogPanel() {
        cardLayout.show(cardPanel, "Catalog"); // Switch to the catalog view
        add(viewCartButton, BorderLayout.SOUTH); // Ensure the view cart button is in the layout
        viewCartButton.setVisible(true); // Now show the view cart button
    }

    // Method to display the shopping cart in a dialog window
    private void showCartDialog() {
        // Create a dialog window for the shopping cart, make it modal
        JDialog cartDialog = new JDialog(this, "Shopping Cart", true);
        // Add a new panel to the dialog for viewing the cart, using the order processing service
        cartDialog.getContentPane().add(new CartViewPanel(orderProcessingService));
        cartDialog.pack();
        cartDialog.setLocationRelativeTo(this); // Center the dialog relative to the main window
        cartDialog.setVisible(true); // Show the dialog
    }

    // The main method that starts the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainClass().setVisible(true));
    }
}
