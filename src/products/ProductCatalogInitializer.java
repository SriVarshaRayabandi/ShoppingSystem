package products;

// This class is responsible for initializing the product catalog with predefined products
public class ProductCatalogInitializer {
    // Method to create and fill a ProductCatalog with initial products
    public static ProductCatalog initializeCatalog() {
        // Create a new ProductCatalog instance
        ProductCatalog catalog = new ProductCatalog();


        catalog.addProduct(ProductFactory.createProduct("Electronics", "1", "Laptop", 999.99));
        catalog.addProduct(ProductFactory.createProduct("Electronics", "2", "Smartphone", 699.99));
        catalog.addProduct(ProductFactory.createProduct("Electronics", "3", "Tablet", 499.99));
        catalog.addProduct(ProductFactory.createProduct("Electronics", "4", "Smart Watch", 199.99));
        catalog.addProduct(ProductFactory.createProduct("Electronics", "5", "E-Reader", 129.99));
        catalog.addProduct(ProductFactory.createProduct("Electronics", "6", "Wireless Headphones", 59.99));

        catalog.addProduct(ProductFactory.createProduct("Clothing", "7", "T-Shirt", 29.99));
        catalog.addProduct(ProductFactory.createProduct("Clothing", "8", "Jeans", 39.99));
        catalog.addProduct(ProductFactory.createProduct("Clothing", "9", "Hoodie", 49.99));
        catalog.addProduct(ProductFactory.createProduct("Clothing", "10", "Sneakers", 89.99));
        catalog.addProduct(ProductFactory.createProduct("Clothing", "11", "Baseball Cap", 19.99));
        catalog.addProduct(ProductFactory.createProduct("Clothing", "12", "Jacket", 99.99));

        // Return the filled catalog
        return catalog;
    }
}