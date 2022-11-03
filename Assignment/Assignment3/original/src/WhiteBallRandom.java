import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WhiteBallRandom extends Ball implements Subject<Ball>{
    private List<Ball> observerList = new ArrayList<>();
    public WhiteBallRandom(int xSpeed, int ySpeed, int ballSize) {
        this.color = Color.WHITE;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.ballSize = ballSize;

        this.visible = true;
        this.x = (int) (Math.random() * 580);
        this.y = (int) (Math.random() * 580);
        count++;

        this.setSize(ballSize, ballSize);
    }

    @Override
    public void update(char ch, MainPanel.GameStatus gameStatus){}


    @Override
    public void registerObserver(Ball ball) {
        observerList.add(ball);
    }

    @Override
    public void removeObserver(Ball ball) {
        observerList.remove(ball);
    }

    @Override
    public void notifyObservers(char keyChar) {

    }

    public void clearAll(){
        observerList = new ArrayList<>();
    }

    @Override
    public void notifyObservers() {
        for(Ball ball: observerList){
            ball.changeIntersect(this);
        }
    }
    @Override
    public void changeIntersect(Ball another){

    }
}
