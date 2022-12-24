package StrategyPattern;

public class Client {
    public static void main(String[] args) {
        Strategy vip = new VIPStrategy();
        Strategy mvp = new MVPStrategy();
        Context context = new Context(vip);
        context.pay();
        context.setStrategy(mvp);
        context.pay();
    }
}
