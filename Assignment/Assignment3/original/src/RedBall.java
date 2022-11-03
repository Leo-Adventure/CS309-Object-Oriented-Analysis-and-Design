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
    public void update(char ch){
        switch (ch) {
            case 'a':
                this.setXSpeed(-random.nextInt(3) - 1);
                break;
            case 'd':
                this.setXSpeed(random.nextInt(3) + 1);
                break;
            case 'w':
                this.setYSpeed(-random.nextInt(3) - 1);
                break;
            case 's':
                this.setYSpeed(random.nextInt(3) + 1);
        }
    }


}
