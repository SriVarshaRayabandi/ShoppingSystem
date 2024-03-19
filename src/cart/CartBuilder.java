package cart;

import products.Product; // Import statemment to import the Product class

public class CartBuilder { // Declaring  class named CartBuilder
    private final ShoppingCart cart; // Declaring of a  final instance variable 'cart' of type ShoppingCart

    public CartBuilder() { // Building a constructor for cartbuilder
        this.cart = ShoppingCart.getInstance(); // Assign the singleton instance of ShoppingCart to the cart variable
    }

    public CartBuilder addProduct(Product product, int quantity) { // Declaring a method named addProduct to add products to the shopping cart with a specified quantity
        for (int i = 0; i < quantity; i++) { // Looping quantity times
            cart.addProduct(product); // Adding the specific product to the shopping cart quantity times
        }
        return this; // Return the CartBuilderr object itself for method chaining
    }

    public CartBuilder removeProduct(Product product) { // Declarig  a method named removeProduct
        cart.removeProduct(product); // Remove the specified product from the shopping cart
        return this; // Return the CartBuilder object for method chaining
    }

    public ShoppingCart build() { // Declaring a method  build to finalize and return the built shopping cart
        return cart; // Return cart.
    }
}
