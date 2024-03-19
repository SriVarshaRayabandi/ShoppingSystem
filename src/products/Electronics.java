package products;

// This class represents electronic products in a store
public class Electronics implements Product {
    private String id;
    private String name;
    private double price;

    // Constructor to create a new electronic item with its details
    public Electronics(String id, String name, double price) {
        this.id = id; // Store the provided id
        this.name = name; // Store the provided name
        this.price = price; // Store the provided price
    }

    // Returns the id of the electronic item
    @Override
    public String getId() { return id; }

    // Returns the name of the electronic item
    @Override
    public String getName() { return name; }

    // Returns the price of the electronic item
    @Override
    public double getPrice() { return price; }

}