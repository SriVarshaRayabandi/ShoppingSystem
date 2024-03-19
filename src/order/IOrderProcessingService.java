package order;

public interface IOrderProcessingService {
    Order createOrder();
    void finalizeOrder(String orderId);
}
