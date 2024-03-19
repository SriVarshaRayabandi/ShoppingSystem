package gui; // Declaring this file is part of the gui package

import cart.ShoppingCart; // Importing the ShoppingCart class from the cart package
import products.Product; // Importing the Product class from the products package
import products.ProductCatalog; // Importing the ProductCatalog class from the products package

import javax.swing.*; // Importing the Swing library for GUI components
import java.awt.*; // Importing the AWT library for basic GUI functionality

public class CatalogPanel extends JPanel { // Creating a public class named CatalogPanel which extends JPanel
    private ProductCatalog catalog; // Declaring a private instance variable named catalog of type ProductCatalog

    public CatalogPanel(ProductCatalog catalog) { // Creating a constructor for CatalogPanel which takes a ProductCatalog parameter
        this.catalog = catalog;
        initializeUI(); // Calling the initializeUI() method to set up the user interface
    }

    private void initializeUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (Product product : catalog.getProducts()) { // Iterating over each product in the catalog
            JPanel productPanel = makeProductPanel(product); // Create a panel for displaying the current product
            productPanel.setAlignmentX(Component.LEFT_ALIGNMENT); // Aligning the product panel to the left within its parent container
            add(productPanel); // Add the product panel to this CatalogPanel
        }
    }

    private JPanel makeProductPanel(Product product) {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.add(new JLabel(product.getName()));
        panel.add(new JLabel("$" + String.format("%.2f", product.getPrice())));
        JButton addButton = new JButton("Add to Cart");
        addButton.addActionListener(e -> {
            ShoppingCart.getInstance().addProduct(product);
            JOptionPane.showMessageDialog(this, product.getName() + " added to cart!");
        });
        panel.add(addButton); // Add the button to the panel
        return panel; // Return the panel containing the product information
    }
}