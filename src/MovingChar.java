
import java.awt.Color;

public abstract class MovingChar extends Char implements CanMove {
    //declare x adn y speed
    int xS, yS;

    //constuctor for PC controlled characters 
    public MovingChar(int x, int y, int xS, int yS, Color color){
        super(x, y, color);
        this.xS = xS;
        this.yS = yS;
    }

    //returns x speed
    public int getxS() {
        return xS;
    }
    //returns y speed
    public int getyS() {
        return yS;
    }
    //sets x speed
    public void setxS(int xS) {
        this.xS = xS;
    }
    //sets y speed
    public void setyS(int yS) {
        this.yS = yS;
    }
    @Override
    // Used to move non controllable objects
    public void move()
    {
        this.x += xS;
        this.y += yS;
    }
}
