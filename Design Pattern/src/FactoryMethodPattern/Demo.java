package FactoryMethodPattern;

public class Demo {
    public static void main(String[] args) {
        Factory miFactory = new MiFactory();
        Factory IFactory = new IFactory();
        MiPhone miPhone = (MiPhone) miFactory.makePhone();
        IPhone iPhone = (IPhone) IFactory.makePhone();
        MAC mac = (MAC) IFactory.makePC();
        PC mipc = miFactory.makePC();
        mipc.make();
    }
}
