import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Plasma extends MovingChar {

    // constructor for Plasma
    public Plasma(int x, int y, int xS, int yS, Color color){
        super(x, y, xS, yS, color);
    }
    //draws Plasma object
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(this.getX(), this.getY(), 10, 20);
    }
    //defiens and returns hitbox
    @Override
    public Rectangle getBounds() {
        Rectangle PlasmaHitbox = new Rectangle(x, y, 10, 20);
        return PlasmaHitbox;
    }
}
