package FactoryPattern;

public class IFactory implements Factory{
    @Override
    public Phone makePhone(){
        return new IPhone();
    }
    @Override
    public PC makePC(){
        return new MAC();
    }

}
