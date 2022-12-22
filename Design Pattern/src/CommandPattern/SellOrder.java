package CommandPattern;

public class SellOrder extends Order{
    public Stock stock;
    public SellOrder(Stock stock){
        this.stock = stock;
    }
    @Override
    public void execute() {
        stock.sell();
    }
}
