package gui; // Package declaration

import cart.ShoppingCart; // Importing statement to import the ShoppingCart class
import order.IOrderProcessingService; // Importing statement to import the IOrderProcessingService interface
import order.Order; // Import statement to importthe Order class
import products.Product; // Import statement to impor the Product class

import javax.swing.*; // Import statement to use Swing GUI components
import java.awt.*; // Import statements to use AWT GUI components
import java.util.Map; // Import to use Map from the java.util package

public class CartViewPanel extends JPanel { // Declaring a public class named CartViewPanel which extends JPanel
    private IOrderProcessingService orderProcessingService; // Declaring a private instance variable orderProcessingService of type IOrderProcessingService

    public CartViewPanel(IOrderProcessingService orderProcessingService) { // Declaring constructor with a parameter orderProcessingService
        this.orderProcessingService = orderProcessingService; // Assigning the provided value orderProcessingService to the instance variable
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Setting the layout of the panel to BoxLayout includes Y_AXIS alignment
        refreshCartView(); // Method calling to refresh the cart view on initialization
    }

    private void refreshCartView() { // Declaring of a private method named refreshCartView to updatee the cart view
        removeAll(); // Removing all components

        Map<Product, Integer> products = ShoppingCart.getInstance().getProducts(); // Get the products from the shopping cart

        double total = 0; // Initializie a variable to store the total pricee of items in the cart

        if (products.isEmpty()) { // Check if the cart is empty
            add(new JLabel("Your cart is empty.")); // Adding a label which indicates that the cart is empty
        } else { // Executed if the cart is not empty.
            for (Map.Entry<Product, Integer> entry : products.entrySet()) { // Iterating over the products inside the cart
                Product product = entry.getKey(); // Get the product
                Integer quantity = entry.getValue(); // Getting the quantity of product

                JPanel productPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Creating a panel for product display
                JLabel productLabel = new JLabel(quantity + "x " + product.getName() + " - $" + String.format("%.2f", product.getPrice())); // Create a label to display the product details
                JButton removeButton = new JButton("Remove"); // Create a button to remove thhe product
                removeButton.addActionListener(e -> { // Adding an action listener tto the remove button
                    ShoppingCart.getInstance().removeProduct(product); // Removing the product from cart
                    refreshCartView(); // Refreshing the cart after removing
                });

                productPanel.add(productLabel); // Adding the product labell to the product panel
                productPanel.add(removeButton); // Adding the remove button to the product panel
                add(productPanel); // Add the product panel to the cart view

                total += product.getPrice() * quantity; // Calculating the total price of each product
            }

            add(new JLabel(String.format("Total: $%.2f", total))); // Adding a label to display the total price of items in the cart

            JButton clearCartButton = new JButton("Clear Cart"); // Create a button to clear the cart
            clearCartButton.addActionListener(e -> { // Adding action listener to the clear cart button
                ShoppingCart.getInstance().clear(); // Clearing the cart
                refreshCartView(); // Refresh the cart view after clearing the cart
            });
            add(clearCartButton); // Add the clear cart button to the cart view

            JButton checkoutButton = new JButton("Checkout"); // Create a checkout button
            checkoutButton.addActionListener(e -> { // Adding an actionlistener to the checkout button
                Order order = orderProcessingService.createOrder(); // Creating an order using the orderr processing service
                if (order != null) { // If order creation successful
                    Frame owner = (Frame) SwingUtilities.getWindowAncestor(this).getOwner(); // Gett the owner frame of the panel
                    MockPayment mockPayment = new MockPayment(owner, orderProcessingService, order.getOrderId()); // Create a mock payment dialog
                    mockPayment.setVisible(true); // Making the mock payment dialog visible
                } else { // If the cart is empty and order creation fails
                    JOptionPane.showMessageDialog(this, "Your cart is empty.", "Checkout Error", JOptionPane.ERROR_MESSAGE); // Displaying an error message
                }
            });
            add(checkoutButton); // Adding the checkut button to the cart view
        }

        revalidate(); // Revalidating  panel to ensure proper layout
        repaint(); // Repainting     panel to reflect changes
    }
}
