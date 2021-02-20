import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main extends JFrame {

    private Board board;
    private ImageIcon icon = new ImageIcon("");

    public Main() {
        //sets title and exit on close
        super("Javavader");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //constructs new game board
        board = new Board();
        board.setDoubleBuffered(true);
        //adds peramiters and icon
        this.getContentPane().add(board);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        //this.setIconImage(icon.getImage());
        this.setBackground(Color.BLACK);
        //runs the game board
        board.run();
    }
    //main runner
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                new Main().setVisible(true);
            }
        });
    }
}
