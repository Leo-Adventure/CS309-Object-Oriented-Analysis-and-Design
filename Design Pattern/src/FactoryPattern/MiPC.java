package FactoryPattern;

public class MiPC implements PC{
    public MiPC(){
        this.make();
    }
    @Override
    public void make(){
        System.out.println("Make MiPC");
    }
}
