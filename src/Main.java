
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main extends JFrame {

    private Board board;
    private ImageIcon icon = new ImageIcon("images/Coronavirus_Cell.png");

    public Main() {
        //sets title and exit on close
        super("Ronavader StudentID: CH17598");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //constructs new game board
        board = new Board();
        board.setDoubleBuffered(true);
        //adds peramiters and icon
        this.getContentPane().add(board);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(icon.getImage());
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
