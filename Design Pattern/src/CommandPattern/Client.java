package CommandPattern;

public class Client {
    public static void main(String[] args) {
        Stock stock = new Stock();
        Invoker invoker = new Invoker();
        Order order1 = new BuyOrder(stock);
        Order order2 = new SellOrder(stock);
        invoker.addOrder(order1);
        invoker.addOrder(order2);
        invoker.placeOrder();
    }
}
