package FactoryMethodPattern;

public class MiFactory implements Factory{
    @Override
    public Phone makePhone(){
        return new MiPhone();
    }
    @Override
    public PC makePC(){
        return new MiPC();
    }

}
