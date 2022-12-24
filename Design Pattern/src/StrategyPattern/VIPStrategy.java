package StrategyPattern;

public class VIPStrategy extends Strategy{
    @Override
    public void pay() {
        System.out.println("I'm VIP, I will get less 20 payment");
    }
}
