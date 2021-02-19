

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {
  //defines boolean for each key as an array
  private boolean[] key; 
  //constuctor that populates the array
  public KeyboardListener() {
    key = new boolean[256];
  }
  //returns key boolean value as long as its in range
  public boolean getKeyState(int k){
    if(k< 0 || k > 255){
      return false;
    }
    else{
      return key[k];
    }
    
  }
  //resets boolean array
  public void resetController(){
    key = new boolean[256];
  }
  //sets boolen value to true on press and prints char or value in console 
  @Override
  public void keyPressed(KeyEvent e) {
    key[e.getKeyCode()] = true;
    if ((e.getKeyCode() > 64 && e.getKeyCode() < 91) || (e.getKeyCode() > 96 && e.getKeyCode() < 123) || e.getKeyCode() == 8){
      System.out.println(e.getKeyChar());
    }
    else{
      System.out.println(e.getKeyCode());
    }
  }
  //sets boolen value to false on release
  @Override
  public void keyReleased(KeyEvent e) {
    key[e.getKeyCode()] = false;
  }
  //needed fore class to run
  @Override
  public void keyTyped(KeyEvent e) { }
}
