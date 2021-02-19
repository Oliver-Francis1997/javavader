
import java.awt.Color;
//default object constructor that allows movment
public abstract class ControlledChar extends Char implements CanMove {
    KeyboardListener controller;
    //defines speed
    int s;

    public ControlledChar(int x, int y, int s, Color color, KeyboardListener controller){
        super(x, y, color);
        this.controller = controller;
        this.s = s;
   }
}
