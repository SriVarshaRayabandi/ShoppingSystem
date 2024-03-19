package order; // Declare order package

import products.Product;

import java.util.Map;

public class Order { // Declare a class named Order
    private Map<Product, Integer> products; // Declare a private instance variable named products of type Map<Product, Integer
    private double totalCost; // Declaring a private instance variable named "totalCost" of type double
    private String orderId; // Declaring a private instance variable named "orderId" of type String
    private String status; // Declaring a private instance variable named "status" of type String

    public Order(Map<Product, Integer> products, String orderId) { // Declaring a constructor for the Order class which takes a Map<Product Integer> and a String orderId as parameters
        this.products = products;
        this.orderId = orderId;
        this.totalCost = calculateTotalCost();
        this.status = "New"; // Initialize the "status" instance variable to "New" indicating that the order is new
    }

    private double calculateTotalCost() { // Declaring a private method named calculatTotalCost which callculates the total cost of the order
        return products.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue()) // Calculating the cost of each product (price * quantity) and map it to a double
                .sum(); // Summing up all the individual costss to get the total cost of the order
    }

    // Getters and Setters
    public Map<Product, Integer> getProducts() {
        return products;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getStatus() { // Declare a getter method to retrieve the status of the order.
        return status;
    }

    public void setStatus(String status) { // Declare a setter method to set the status of the order.
        this.status = status;
    }
}
