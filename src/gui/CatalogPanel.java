package gui;

import cart.ShoppingCart;
import products.Product;
import products.ProductCatalog;

import javax.swing.*;
import java.awt.*;

public class CatalogPanel extends JPanel {
    private ProductCatalog catalog;

    public CatalogPanel(ProductCatalog catalog) {
        this.catalog = catalog;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Adjust as needed
        for (Product product : catalog.getProducts()) {
            JPanel productPanel = makeProductPanel(product);
            productPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            add(productPanel);
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
        panel.add(addButton);
        return panel;
    }
}