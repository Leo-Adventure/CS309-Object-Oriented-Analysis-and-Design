package FlyweightedPattern;

public class ColorChess extends Chess {

    public ColorChess(String color){
        super(color);
    }

    @Override
    public void print(){
        System.out.println(this.color + " Chess is at (" + this.x + ", " + this.y + ");");
    }

}
