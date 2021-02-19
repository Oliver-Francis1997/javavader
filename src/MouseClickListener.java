
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseClickListener implements MouseListener {

    private Board board; // board passed through to allow for game manipulation

    public MouseClickListener(){

    }

    public MouseClickListener(Board board) {
        this.board = board;
    }

    public void mousePressed(MouseEvent e) {

    }
  
    public void mouseReleased(MouseEvent e) {
  
    }
  
    public void mouseEntered(MouseEvent e) {
  
    }
  
    public void mouseExited(MouseEvent e) {
  
    }
    //prints x & y positions for debugging and writing assistence
    public void mouseClicked(MouseEvent e) {
      int x = e.getX();
      int y = e.getY();
      System.out.println("Mouse click x: " + x + " " + "Mouse click y: " + y);
      
    }
}
