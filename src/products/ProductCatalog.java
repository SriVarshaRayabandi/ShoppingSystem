package products;

// Importing necessary classes for handling collections
import java.util.ArrayList;
import java.util.List;

// This class represents a catalog of products in a store
public class ProductCatalog {

    private List<Product> products = new ArrayList<>();    // A list to store the products

    // Method to add a product to the catalog
    public void addProduct(Product product) {
        products.add(product); // Adds the specified product to the list
    }

    // Method to get all products in the catalog
    public List<Product> getProducts() {
        return new ArrayList<>(products); // Returns a new list containing all products
    }
}