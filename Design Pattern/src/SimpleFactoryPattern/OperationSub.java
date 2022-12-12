package SimpleFactoryPattern;

public class OperationSub extends Operation{
    @Override
    public double getResult(){
        return getNumber1() - getNumber2();
    }
}
