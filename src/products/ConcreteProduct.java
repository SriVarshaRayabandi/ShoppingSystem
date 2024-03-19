package products;

public class ConcreteProduct implements Product {
    private String id;
    private String name;
    private double price;

    public ConcreteProduct(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String getId() { return id; }

    @Override
    public String getName() { return name; }

    @Override
    public double getPrice() { return price; }
}
