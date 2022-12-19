package BridgePattern;

public abstract class Shape {
    protected Color color;
    public void setColor(Color color){
        this.color = color;
    }
    public abstract void draw();
}
