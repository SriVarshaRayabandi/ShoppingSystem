package products;

import java.util.ArrayList;
import java.util.List;

public class ProductCatalog {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }
}
