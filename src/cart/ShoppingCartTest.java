package cart;

import org.junit.Test;
import products.ConcreteProduct;
import products.Product;
import static org.junit.Assert.*;

public class ShoppingCartTest {

    @Test
    public void testAddAndRemoveProduct() {
        ShoppingCart cart = ShoppingCart.getInstance();
        cart.clear(); // Ensure the cart is empty at the start

        Product product1 = new ConcreteProduct("1", "Laptop", 999.99);
        Product product2 = new ConcreteProduct("2", "Mouse", 49.99);

        cart.addProduct(product1);
        cart.addProduct(product2);
        cart.addProduct(product2); // Add product2 twice

        assertEquals(2, cart.getProducts().size());
        assertTrue(cart.getProducts().containsKey(product2));
        assertEquals(Integer.valueOf(2), cart.getProducts().get(product2));

        cart.removeProduct(product2);
        assertEquals(Integer.valueOf(1), cart.getProducts().get(product2)); // Verify quantity is decremented

        cart.clear();
        assertTrue(cart.getProducts().isEmpty());
    }
}