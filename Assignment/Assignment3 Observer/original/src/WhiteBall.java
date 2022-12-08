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
    public void update(char ch, MainPanel.GameStatus gameStatus){
        if(gameStatus == MainPanel.GameStatus.START){
            switch (ch) {
                case 'a' -> this.setXSpeed(-8);
                case 'd' -> this.setXSpeed(8);
                case 'w' -> this.setYSpeed(-8);
                case 's' -> this.setYSpeed(8);
            }
        }
    }
    @Override
    public void changeIntersect(Ball another){

    }
}
