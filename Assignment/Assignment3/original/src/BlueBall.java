import java.awt.*;

public class BlueBall extends Ball{
    public BlueBall(int xSpeed, int ySpeed, int ballSize) {
        this.color = Color.BLUE;
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
        this.setXSpeed(-1 * this.getXSpeed());
        this.setYSpeed(-1 * this.getYSpeed());
    }


}
