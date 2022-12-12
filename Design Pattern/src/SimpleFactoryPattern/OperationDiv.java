package SimpleFactoryPattern;

public class OperationDiv extends Operation{
    @Override
    public double getResult(){
        if(getNumber2() == 0){
            throw new RuntimeException("The dividend cannot be zero");
        }
        return getNumber1() / getNumber2();
    }
}
