package products;

public class ProductFactory {
    public static Product createProduct(String type, String id, String name, double price, String attribute) {
        switch (type) {
            case "Electronics":
                return new Electronics(id, name, price, attribute); // attribute could be warranty period
            case "Clothing":
                return new Clothing(id, name, price, attribute); // attribute could be size
            default:
                return new ConcreteProduct(id, name, price); // Fallback for general products
        }
    }
}