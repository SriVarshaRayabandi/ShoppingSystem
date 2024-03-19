package cart;

import products.Product;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private static ShoppingCart instance;
    private Map<Product, Integer> products = new HashMap<>();

    private ShoppingCart() {}

    public static synchronized ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }

    public void addProduct(Product product) {
        products.put(product, products.getOrDefault(product, 0) + 1);
    }

    public void removeProduct(Product product) {
        if (products.containsKey(product) && products.get(product) > 1) {
            products.put(product, products.get(product) - 1);
        } else {
            products.remove(product);
        }
    }

    public void clear() {
        products.clear(); // This will empty the shopping cart
    }

    public Map<Product, Integer> getProducts() {
        return new HashMap<>(products);
    }
}