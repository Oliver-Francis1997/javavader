import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class RNAShield extends Char {
    //defiens width and height
    int width, height;
    //constuctor
    public RNAShield(int x, int y, int width, int height, Color color){
        super(x, y, color);
        this.width = width;
        this.height = height;
    }
    //returns object width
    public int getWidth() {
        return width;
    }
    //returns object height
    public int getHeight() {
        return height;
    }
    //draws the RNA shields
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(this.getX(), this.getY(), width, height);

    }
    //defiens and returns hitbox
    @Override
    public Rectangle getBounds() {
        Rectangle RNAShieldHitbox = new Rectangle(this.getX(), this.getY(), width, height);
        return RNAShieldHitbox;
    }
}