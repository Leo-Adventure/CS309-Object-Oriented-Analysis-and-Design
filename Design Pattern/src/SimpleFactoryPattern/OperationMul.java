package SimpleFactoryPattern;

public class OperationMul extends Operation{
    @Override
    public double getResult(){
        return getNumber1() * getNumber2();
    }
}
