package cart; // Package declaring stating that this class belongs to the Cart package

import products.Product; //  import the 'Product' class

import java.util.HashMap; // Import  HashMap fromm the java.util package
import java.util.Map; // Import  Map from the java.util package

public class ShoppingCart { // Declarastion of a public class named ShoppingCart
    private static ShoppingCart instance; // Declaring a private static instance variable instance of type ShoppingCart
    private Map<Product, Integer> products = new HashMap<>(); // Declaring a private instance varible 'products' of type Map<Product Integer> initialized with a new HashMap

    private ShoppingCart() {} // creating a Private constuctor to prevent direct instantiation of the ShoppingCart class from outside

    public static synchronized ShoppingCart getInstance() { // Declaring a public static  method named 'getInstance' to get the SINGLETON instance of ShoppingCart
        if (instance == null) { // Check if the instance is null
            instance = new ShoppingCart(); // Creating a new instancee of ShoppingCart if it's null
        }
        return instance; // Return the singleton instance of ShoppingCart
    }

    public void addProduct(Product product) { // Declaring  a public method named addProduct to add a product to the shopping cart
        products.put(product, products.getOrDefault(product, 0) + 1); // Adding the product to the products map with its quantity increased by 1
    }

    public void removeProduct(Product product) { // Declaring  a public method named removeProduct to remove a product from the shopping cart
        products.computeIfPresent(product, (k, v) -> v > 1 ? v - 1 : null); // Use computeIfPresent to update the product quantity in the cart If quantity > 1, decrement by 1 otherwis remove the product
    }

    public void clear() { // Declaring  a public method named clear to empty the shopping cart
        products.clear(); // This will empty the shopping cart by removing all products
    }

    public Map<Product, Integer> getProducts() { // Declaring  a public method named getProducts to get a copy of the products in the shoppig cart
        return new HashMap<>(products); // Return a new HashMap containing the same products as in the 'products' map to prevent direct modification of the internal map
    }
}
