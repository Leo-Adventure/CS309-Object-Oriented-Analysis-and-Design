package SimpleFactoryPattern;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        System.out.println("Please enter the operation: ");
        Scanner scanner = new Scanner(System.in);
        String operation = scanner.next();
        Operation oper = OperationFactory.createOperation(operation);
        System.out.println("Please enter the first number: ");
        double number1 = scanner.nextDouble();
        System.out.println("Please enter the second number: ");
        double number2 = scanner.nextDouble();
        oper.setNumber1(number1);
        oper.setNumber2(number2);
        System.out.printf("The result is %.1f", oper.getResult());

    }


}
