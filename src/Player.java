
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class Player extends ControlledChar{

    ImageIcon player = new ImageIcon("");
    


    static int height, width;

    public Player(int x, int y, int s, Color c, KeyboardListener control){
        super(x, y, s, c, control);

        width = (int) Math.round(Board.getBW() * .04);
        height = (int) Math.round(Board.getBW() * .08);
    }
    //returns object width
    public int getWidth() {
        return width;
    }
    //returns object height
    public int getHeight() {
        return height;
    }
    //Draws life object
    public void lifeDraw(Graphics g){
        g.setColor(this.color);
        g.fillRect(this.x, this.y, 20, 20);
    }
    //defines and returns hitbox
    @Override
    public Rectangle getBounds() {
        Rectangle playerHitbox = new Rectangle(this.getX(), this.getY(), width, height);
        return playerHitbox;
    }
    //draws player object
    @Override
    public void draw(Graphics g) {
        if(player.getIconWidth() > 0){
            player.paintIcon(null, g, this.getX(), this.getY());
        }
        else{
            g.setColor(Color.RED);
            g.fillRect(this.getX(), this.getY(), width, height);
        }          
    }
    //move methord
    public void move() {        
        // left arrow move left
        if(controller.getKeyState(KeyEvent.VK_LEFT)){
            x -= s;
        }
        // right arrow move right
        if(controller.getKeyState(KeyEvent.VK_RIGHT)){
            x += s;
        }
        //edge to edge
        if(x + width > Board.getBW()){
            x = 0;
        }
        if(x < 0){
            x = Board.getBW() - width;
        }
    }
    
}
