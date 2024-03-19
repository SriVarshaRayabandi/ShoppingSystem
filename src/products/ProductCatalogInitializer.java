package products;

public class ProductCatalogInitializer {
    public static ProductCatalog initializeCatalog() {
        ProductCatalog catalog = new ProductCatalog();
        catalog.addProduct(ProductFactory.createProduct("Electronics", "1", "Laptop", 999.99, "2 years"));
        catalog.addProduct(ProductFactory.createProduct("Clothing", "5", "T-Shirt", 29.99, "M"));
        return catalog;
    }
}