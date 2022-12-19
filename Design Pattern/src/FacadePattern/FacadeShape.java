package FacadePattern;

public class FacadeShape {
    private Circle circle;
    private Triangle triangle;
    public FacadeShape(){
        this.circle = new Circle();
        this.triangle = new Triangle();
    }
    public void drawCircle(){
        circle.draw();
    }
    public void drawTriangle(){
        triangle.draw();
    }
}
