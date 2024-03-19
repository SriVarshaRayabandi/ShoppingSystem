package gui; // Declaring this file is part of the gui package

import cart.ShoppingCart; // Import the ShoppingCart class from the cart package
import products.Product; // Import the Product class from the products package
import products.ProductCatalog; // Import the ProductCatalog class from the products package

import javax.swing.*; // Import the Swing library for GUI components
import java.awt.*; // Import the AWT library for basic GUI functionality

public class CatalogPanel extends JPanel { // Create a public class named CatalogPanel which extends JPanel
    private ProductCatalog catalog; // Declare a private instance variable named "catalog" of type ProductCatalog.

    public CatalogPanel(ProductCatalog catalog) { // Create a constructor for CatalogPanel which takes a ProductCatalog parameter.
        this.catalog = catalog; // Initialize the "catalog" instance variable with the provided ProductCatalog object.
        initializeUI(); // Call the initializeUI() method to set up the user interface.
    }

    private void initializeUI() { // Define a private method named initializeUI() to set up the user interface.
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Set the layout manager of this panel to BoxLayout with a vertical axis.
        for (Product product : catalog.getProducts()) { // Iterate over each product in the catalog.
            JPanel productPanel = makeProductPanel(product); // Create a panel for displaying the current product.
            productPanel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align the product panel to the left within its parent container.
            add(productPanel); // Add the product panel to this CatalogPanel.
        }
    }

    private JPanel makeProductPanel(Product product) { // Define a private method named makeProductPanel() which takes a Product parameter and returns a JPanel.
        JPanel panel = new JPanel(new GridLayout(1, 3)); // Create a new JPanel with a grid layout of 1 row and 3 columns.
        panel.add(new JLabel(product.getName())); // Add a JLabel displaying the name of the product to the panel.
        panel.add(new JLabel("$" + String.format("%.2f", product.getPrice()))); // Add a JLabel displaying the price of the product to the panel.
        JButton addButton = new JButton("Add to Cart"); // Create a JButton with the label "Add to Cart".
        addButton.addActionListener(e -> { // Add an action listener to the button which executes the following code when clicked:
            ShoppingCart.getInstance().addProduct(product); // Add the current product to the shopping cart.
            JOptionPane.showMessageDialog(this, product.getName() + " added to cart!"); // Show a message dialog indicating that the product has been added to the cart.
        });
        panel.add(addButton); // Add the button to the panel.
        return panel; // Return the panel containing the product information.
    }
}
