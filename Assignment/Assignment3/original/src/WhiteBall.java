import java.awt.*;

public class WhiteBall extends Ball{
    public WhiteBall(int xSpeed, int ySpeed, int ballSize) {
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
    public void update(char ch){
        switch (ch) {
            case 'a':
                this.setXSpeed(-8);
                break;
            case 'd':
                this.setXSpeed(8);
                break;
            case 'w':
                this.setYSpeed(-8);
                break;
            case 's':
                this.setYSpeed(8);
                break;
        }
    }
}
