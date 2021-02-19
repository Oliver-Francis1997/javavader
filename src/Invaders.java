
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;


public class Invaders extends MovingChar {
    //defines image
    ImageIcon InvadersIcon= new ImageIcon("");
    
    // defiens width and height 
    int width, height;

    public Invaders(int x, int y, int xS, int yS, Color color, int width, int height) {
        super(x, y, xS, yS, color);
        this.width = width;
        this.height = height;
        // imports the image and scales to the height and width of the aliens
        Image convert = InvadersIcon.getImage();
        Image ScaleInvadersIcon = convert.getScaledInstance(this.height, this.width, Image.SCALE_SMOOTH);
        InvadersIcon = new ImageIcon(ScaleInvadersIcon);

    }
    //returns object width
    public int getWidth() {
        return width;
    }
    //returns object height
    public int getHeight() {
        return height;
    }
    //draws image if its there else draws a green box
    @Override
    public void draw(Graphics g) {
        if(InvadersIcon.getIconWidth() > 1){
            InvadersIcon.paintIcon(null, g, this.getX(), this.getY());
        }
        else{
            g.setColor(Color.GREEN);
            g.fillRect(this.getX(), this.getY(), this.width, this.height);
        }       
    }
    //sets and returns hitbox
    @Override
    public Rectangle getBounds() {
        Rectangle invadersHitBox = new Rectangle(x, y, width, height);
        return invadersHitBox;
    }
    //move methord
    @Override
    public void move() {
        x += xS;
    }

}

