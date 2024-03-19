package order;

import products.Product;
import java.util.Map;

public class Order {
    private Map<Product, Integer> products;
    private double totalCost;
    private String orderId;
    private String status; // e.g., "New", "Completed", "Shipped"

    public Order(Map<Product, Integer> products, String orderId) {
        this.products = products;
        this.orderId = orderId;
        this.totalCost = calculateTotalCost();
        this.status = "New";
    }

    private double calculateTotalCost() {
        return products.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
