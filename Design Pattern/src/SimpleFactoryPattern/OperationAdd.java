package SimpleFactoryPattern;

public class OperationAdd extends Operation{
    @Override
    public double getResult(){
        return getNumber1() + getNumber2();
    }
}
