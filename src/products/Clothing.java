package products;

// This class represents clothing items in a store
public class Clothing implements Product {
    private String id; // Unique identifier for the clothing item
    private String name; // Name of the clothing item
    private double price;   // Price of the clothing item

    // Constructor to create a new clothing item with its details
    public Clothing(String id, String name, double price) {
        this.id = id; // Set the id from the parameter
        this.name = name; // Set the name from the parameter
        this.price = price; // Set the price from the parameter
    }

    // Method to get the id of the clothing item
    @Override
    public String getId() { return id; }

    // Method to get the name of the clothing item
    @Override
    public String getName() { return name; }

    // Method to get the price of the clothing item
    @Override
    public double getPrice() { return price; }

}