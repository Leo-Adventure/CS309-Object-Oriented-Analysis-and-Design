package CommandPattern;

public class BuyOrder extends Order{
    public Stock stock;
    public BuyOrder(Stock stock){
        this.stock = stock;
    }
    @Override
    public void execute() {
        stock.buy();
    }
}
