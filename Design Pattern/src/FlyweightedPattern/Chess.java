package FlyweightedPattern;

public abstract class Chess {
    public int x;
    public int y;
    protected String color;
    public Chess(String color){
        this.color = color;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public abstract void print();
}
