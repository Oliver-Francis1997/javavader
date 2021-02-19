import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

public class Beam extends MovingChar {
    int width, height;
    //beam constructor
    public Beam(int x, int y, Color color) {
        super(x, y, 0, 0, color);
        width = (int) Math.round(Board.getBW() * 0.015);
        height = (int) Math.round(Board.getBH() * 0.03);
    }
    //draw methord for the beam
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(this.getX(), this.getY(),width, height);
    }
    //hitbox generation
    @Override
    public Rectangle getBounds() {
        Rectangle beamHitbox = new Rectangle(x, y, width, height);
        return beamHitbox;
    }
    //move methord for the beam 
    //travals at a speed of 2% of the height of the game window per tick
    @Override
    public void move() {
        this.setY(this.getY() - (int) Math.round(Board.getBH()* .02));
    }
}
