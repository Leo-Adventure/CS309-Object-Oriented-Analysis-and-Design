package BridgePattern;

public class client {
    public static void main(String[] args) {
        Shape tri = new Triangle();
        Color blue = new BlueColor();
        tri.setColor(blue);
        tri.draw();
    }
}
