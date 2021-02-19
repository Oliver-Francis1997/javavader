import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Screen extends Char {
    //defiens message
    String message;
    //constuctor for boject
    public Screen(int x, int y, Color color, String message) {
        super(x, y, color);
        this.message = message;
    }
    //sets message
    public void setMessage(String message) {
        this.message = message;
    }
    //draws message
    @Override
    public void draw(Graphics g) {
        //string manipulation
        String[] lines = message.split("\n");
        //set font and color
        g.setColor(this.color);
        g.setFont(new Font("papyrus", Font.PLAIN, 32));
        //draws each line of text
        for (int i = 0; i < lines.length; i++) {
            g.drawString(lines[i], this.x, this.y +(50 * i));
        }
       
    }
    //need as it extends char
    @Override
    public Rectangle getBounds() {
        return null;
    }

}
