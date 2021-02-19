
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;


public class Virus extends MovingChar {
    //defines image
    //image source https://pngtree.com/freepng/coronavirus-covid-19-element_5340536.html
    ImageIcon VirusIcon= new ImageIcon("images/Coronavirus_Cell.png");
    
    // defiens width and height 
    int width, height;

    public Virus(int x, int y, int xS, int yS, Color color, int width, int height) {
        super(x, y, xS, yS, color);
        this.width = width;
        this.height = height;
        // imports the image and scales to the height and width of the aliens
        Image convert = VirusIcon.getImage();
        Image ScaleVirusIcon = convert.getScaledInstance(this.height, this.width, Image.SCALE_SMOOTH);
        VirusIcon = new ImageIcon(ScaleVirusIcon);

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
        if(VirusIcon.getIconWidth() > 1){
            VirusIcon.paintIcon(null, g, this.getX(), this.getY());
        }
        else{
            g.setColor(Color.GREEN);
            g.fillRect(this.getX(), this.getY(), this.width, this.height);
        }       
    }
    //sets and returns hitbox
    @Override
    public Rectangle getBounds() {
        Rectangle virusHitBox = new Rectangle(x, y, width, height);
        return virusHitBox;
    }
    //move methord
    @Override
    public void move() {
        x += xS;
    }

}

