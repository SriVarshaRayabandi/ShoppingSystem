package products;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProductFactoryTest {

    @Test
    public void testCreateProduct() {
        Product electronics = ProductFactory.createProduct("Electronics", "1", "Laptop", 999.99);
        assertTrue(electronics instanceof Electronics);
        assertEquals("Laptop", electronics.getName());

        Product clothing = ProductFactory.createProduct("Clothing", "2", "T-Shirt", 19.99);
        assertTrue(clothing instanceof Clothing);
        assertEquals("T-Shirt", clothing.getName());
    }
}