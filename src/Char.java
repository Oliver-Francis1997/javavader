
import java.awt.Color;
import java.awt.Rectangle;
//default oject constuctor
public abstract class Char implements CanDraw {
    //movement left and right with and at what speed
    int x,y;
    Color color;
    boolean isColliding;
    public Char(){};

    public Char(int x, int y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public abstract Rectangle getBounds();
    // returns the X position of any char object
    public int getX() {
        return this.x;
    }
    //Returns the Y position of any char object
    public int getY() {
        return this.y;
    }
    //Retuns the color of any char object
    public Color getColor() {
        return this.color;
    }

    // Sets the X position of any char object
    public void setX(int x) {
      this.x = x;
    }
    // Sets the Y position of any char object
    public void setY(int y) {
        this.y = y;
    }
    // Sets the color of any char object
    public void setColor(Color color) {
        this.color = color;
    }
    // Checks if the hitboxes of any two objects are intersecting
    public boolean isColliding(Char other) {
        isColliding = other.getBounds().intersects(this.getBounds());
        return isColliding;
    }
}
