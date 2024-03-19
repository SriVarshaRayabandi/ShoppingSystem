package products;

// Defines a Product interface for all types of products in a store
public interface Product {
    // Method to get the product's unique ID
    String getId();

    // Method to get the product's name
    String getName();

    // Method to get the product's price
    double getPrice();
}