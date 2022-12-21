package FactoryMethodPattern;

public class MAC implements PC{
    public MAC(){
        this.make();
    }
    @Override
    public void make(){
        System.out.println("Make MAC");
    }
}
