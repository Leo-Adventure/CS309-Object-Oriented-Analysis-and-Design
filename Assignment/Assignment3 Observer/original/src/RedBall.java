import java.awt.*;
import java.util.Random;



public class RedBall extends Ball{
    Random random = new Random();
    public RedBall(int xSpeed, int ySpeed, int ballSize) {
        this.color = Color.RED;
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
    public void update(char ch, MainPanel.GameStatus gameStatus){
        switch (ch) {
            case 'a' -> this.setXSpeed(-random.nextInt(3) - 1);
            case 'd' -> this.setXSpeed(random.nextInt(3) + 1);
            case 'w' -> this.setYSpeed(-random.nextInt(3) - 1);
            case 's' -> this.setYSpeed(random.nextInt(3) + 1);
        }
    }

    @Override
    public void changeIntersect(Ball another){
        this.setVisible(this.isIntersect(another));
    }


}
