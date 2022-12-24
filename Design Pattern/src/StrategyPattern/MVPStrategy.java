package StrategyPattern;

public class MVPStrategy extends Strategy{
    @Override
    public void pay() {
        System.out.println("I'm MVP, I will store more 20 credits");
    }
}
