package FacadePattern;

public class client {
    public static void main(String[] args) {
        FacadeShape facadeShape = new FacadeShape();
        facadeShape.drawCircle();
        facadeShape.drawTriangle();
    }
}
