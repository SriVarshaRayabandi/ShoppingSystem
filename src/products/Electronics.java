package products;

public class Electronics implements Product {
    private String id;
    private String name;
    private double price;
    private String warrantyPeriod;

    public Electronics(String id, String name, double price, String warrantyPeriod) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String getId() { return id; }

    @Override
    public String getName() { return name; }

    @Override
    public double getPrice() { return price; }

    public String getWarrantyPeriod() { return warrantyPeriod; }
}