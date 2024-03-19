package order;

import cart.ShoppingCart;
import gui.Logger;
import products.Product;

import java.util.HashMap; // Importing the HashMap class from the java.util package
import java.util.Map; // Importing the Map interface from the java.util package
import java.util.UUID; // Importing the UUID class from the java.util package

public class OrderProcessingService implements IOrderProcessingService { // Declare a class named OrderProcessingService which implements the IOrderProcessingService interface

    private Map<String, Order> orders = new HashMap<>(); // Declaring a private instance variable named orders of type Map<String Order

    @Override
    public Order createOrder() {
        ShoppingCart cart = ShoppingCart.getInstance(); // Get the singletonn instance of the ShoppingCart
        Map<Product, Integer> products = cart.getProducts(); // Get the products
        if (products.isEmpty()) { // Checking if the ShoppingCart is empty
            return null; // Return null
        }
        String orderId = UUID.randomUUID().toString(); // Generating a unique orderId using UUID
        Order order = new Order(products, orderId); // Creating a new Order object with the products and orderId
        orders.put(orderId, order); // Adding the order to the orders map with its orderId as the key
        cart.clear();
        Logger.log("Order created: " + orderId); // Log the creation of the order
        return order; // Returning the created order
    }

    @Override
    public void finalizeOrder(String orderId) { // Declareing a method named finalizeOrder() which takes a String parameter orderId and does not return any value
        Order order = orders.get(orderId); // Get the order from the orders map using orderId
        if (order != null) {
            order.setStatus("Completed"); // Set the status of the order to Completed
            Logger.log("Order finalized: " + orderId); // Logg the finalization of the order
        }
    }
}
