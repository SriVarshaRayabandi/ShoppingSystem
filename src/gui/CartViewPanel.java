package gui;

import cart.ShoppingCart;
import order.IOrderProcessingService;
import order.Order;
import products.Product;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class CartViewPanel extends JPanel {
    private IOrderProcessingService orderProcessingService;

    public CartViewPanel(IOrderProcessingService orderProcessingService) {
        this.orderProcessingService = orderProcessingService;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        refreshCartView();
    }

    private void refreshCartView() {
        removeAll();
        Map<Product, Integer> products = ShoppingCart.getInstance().getProducts();
        double total = 0;

        if (products.isEmpty()) {
            add(new JLabel("Your cart is empty."));
        } else {
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                Product product = entry.getKey();
                Integer quantity = entry.getValue();
                JPanel productPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JLabel productLabel = new JLabel(quantity + "x " + product.getName() + " - $" + String.format("%.2f", product.getPrice()));
                JButton removeButton = new JButton("Remove");
                removeButton.addActionListener(e -> {
                    ShoppingCart.getInstance().removeProduct(product);
                    refreshCartView(); // Refresh the cart view after removing an item
                });
                productPanel.add(productLabel);
                productPanel.add(removeButton);
                add(productPanel);

                total += product.getPrice() * quantity;
            }

            add(new JLabel(String.format("Total: $%.2f", total)));

            // Add Clear Cart button
            JButton clearCartButton = new JButton("Clear Cart");
            clearCartButton.addActionListener(e -> {
                ShoppingCart.getInstance().clear();
                refreshCartView(); // Refresh the cart view after clearing the cart
            });
            add(clearCartButton);

            JButton checkoutButton = new JButton("Checkout");
            checkoutButton.addActionListener(e -> {
                Order order = orderProcessingService.createOrder();
                if (order != null) {
                    Frame owner = (Frame) SwingUtilities.getWindowAncestor(this).getOwner();
                    MockPayment mockPayment = new MockPayment(owner, orderProcessingService, order.getOrderId());
                    mockPayment.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Your cart is empty.", "Checkout Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            add(checkoutButton);
        }

        revalidate();
        repaint();
    }
}