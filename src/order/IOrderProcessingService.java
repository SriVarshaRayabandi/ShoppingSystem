package order; // Declare order package

public interface IOrderProcessingService { // Declaring an interface named IOrderProcessingService
    Order createOrder();
    void finalizeOrder(String orderId); // Declare a method named finalizeOrder() which takes a String parameter orderId
}



