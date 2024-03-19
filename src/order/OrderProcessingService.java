package order;

import cart.ShoppingCart;
import gui.Logger;
import products.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OrderProcessingService implements IOrderProcessingService {
    private Map<String, Order> orders = new HashMap<>();

    @Override
    public Order createOrder() {
        ShoppingCart cart = ShoppingCart.getInstance();
        Map<Product, Integer> products = cart.getProducts();
        if (products.isEmpty()) {
            return null; // Or throw an exception
        }
        String orderId = UUID.randomUUID().toString();
        Order order = new Order(products, orderId);
        orders.put(orderId, order);
        cart.clear();
        Logger.log("Order created: " + orderId);
        return order;
    }

    @Override
    public void finalizeOrder(String orderId) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.setStatus("Completed");
            Logger.log("Order finalized: " + orderId);
        }
    }
}