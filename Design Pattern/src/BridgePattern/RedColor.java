package BridgePattern;

public class RedColor extends Color {
    @Override
    public void paint(String shape) {
        System.out.println("Red " + shape);
    }
}
