package products;

// This class represents a basic product in a store
public class ConcreteProduct implements Product {
    private String id;
    private String name;
    private double price;

    // Constructor to set up a new product with its details
    public ConcreteProduct(String id, String name, double price) {
        this.id = id; // Save the provided id to the product
        this.name = name; // Save the provided name to the product
        this.price = price; // Save the provided price to the product
    }

    // Returns the product's ID
    @Override
    public String getId() { return id; }

    // Returns the product's name
    @Override
    public String getName() { return name; }

    // Returns the product's price
    @Override
    public double getPrice() { return price; }
}