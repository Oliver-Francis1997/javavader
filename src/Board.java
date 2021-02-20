
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.ArrayList;
import java.util.Random;

public class Board extends JPanel implements Runnable {
  //calls the timers so the game can run
  private Timer gameTimer;
  private Timer gameTimerOnHit;

  // large 4:3 res
  private static final int BOARD_WIDTH = 1280;
  private static final int BOARD_HEIGHT = 960;
  public static Dimension d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
  private final int TICK_RATE = 128;

  //set background image
  private ImageIcon bgIcon = new ImageIcon("images/space.jpg");
  private Image convert = bgIcon.getImage();
  private Image ScalebgIcon = convert.getScaledInstance(BOARD_WIDTH, BOARD_HEIGHT, Image.SCALE_SMOOTH);
  private ImageIcon bgIconScaled = new ImageIcon(ScalebgIcon);

  // Added Counters
  private int screen = 0;
  private int score = 0;
  private int level = 1;
  private int lives = 3;
  private int mX, mY;
  //defines randome for the invaders plasma
  Random r = new Random();
  //defines constuctors
  private KeyboardListener controller;
  private Player p;
  private Player life;
  private Invaders v;
  private Beam a;
  private Shield plasmas;
  private Highscore h;
  private Screen sS;
  private Screen eS;
  private Screen yes;
  private Screen no;
  private Screen top5;
  private Plasma plasma;
  //defines boolean
  private boolean onHit = false;
  private boolean canFire = true;
  private boolean invadersCanFrie = true;
  //defines lists
  private ArrayList<Invaders> invadersList = new ArrayList<Invaders>();
  private ArrayList<Player> lifeList = new ArrayList<Player>();
  private ArrayList<Shield> Shields = new ArrayList<Shield>();
  private ArrayList<Plasma> plasmaList = new ArrayList<Plasma>();
  // calling height and width in the main
  public static int getBW() {
    return d.width;
  }
  public static int getBH() {
    return d.height;
  }
  //runs on game start
  public final void inGame() {
    // reset controller
    controller.resetController();
    // constucts player
    p = new Player((BOARD_WIDTH / 2) - (Player.width / 2), (int) Math.round(BOARD_HEIGHT * .85), (int) Math.round(BOARD_WIDTH * .01), null, controller);
    // constucts beam
    a = new Beam(BOARD_WIDTH, 0, null);
    //constucts Plasma
    plasma = new Plasma(BOARD_WIDTH, 0, 0, 0, null);
    // constucts highscore
    h = new Highscore();

    // constucts life counter
    for (int col = 0; col < lives; col++) {
      life = new Player(80 + (int) Math.round(BOARD_WIDTH * .2) + (col * 40), 20, 0, Color.BLUE, null);
      lifeList.add(life);
    }

    // constructs invaders enermies
    // 6 rows
    for (int row = 0; row < 6; row++) {
      // 5 columns
      for (int col = 0; col < 5; col++) {
        v = new Invaders(20 + (row * 100), 50 + (col * 60), level, 0, null, (int) Math.round(BOARD_WIDTH * .04),
            (int) Math.round(BOARD_WIDTH * .04));
        invadersList.add(v);
      }
    }
    // constcts Shields
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        plasmas = new Shield(((int) Math.round(BOARD_WIDTH * .27) * col) + ((int) Math.round(BOARD_WIDTH * .205)),
            (int) Math.round(BOARD_HEIGHT * .78) - (int) (Math.round(BOARD_HEIGHT * .015) * row),
            (int) Math.round(BOARD_WIDTH * .0875), (int) Math.round(BOARD_HEIGHT * .0125), Color.BLUE);
        Shields.add(plasmas);
      }
    }

    sS = new Screen(BOARD_WIDTH / 6, BOARD_HEIGHT / 3, Color.BLUE, "Welcome to Ronavader\nTo move use the: 'Left' and 'Right' arrowkeys.\nTo fire the antibodies use the: 'space' key.\nPress the 'enter' key when ure ready!");

    eS = new Screen(BOARD_WIDTH / 8, BOARD_HEIGHT / 8, Color.BLUE, "");
    yes = new Screen(BOARD_WIDTH / 8, BOARD_HEIGHT / 2, Color.BLUE, "yes");
    no = new Screen(BOARD_WIDTH / 2, BOARD_HEIGHT / 2, Color.WHITE, "no");
    top5 = new Screen((int) Math.round(BOARD_WIDTH * .8), BOARD_HEIGHT / 8, Color.BLUE,"Highscores: " + h.getTop5());
  }
  //paint methord
  public void paint(Graphics g) {
    super.paint(g);
    // sets bacground if image avalible else will clear screen
    if (bgIconScaled.getIconWidth() > 1) {
      bgIconScaled.paintIcon(null, g, 0, 0);
    } else {
      g.clearRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
    }
    //switch case for the startscreen, gamescreen and endscreen
    switch (screen) {
      //startscreen case
      case 0:
      //draws start screen text and top5 scores
        sS.draw(g);
        top5.draw(g);
        break;
      //gamescreen case
        case 1:
        //as long as an atibody is onscreen scanns for hit
        if (a != null) {
          if (onHit) {
            //on hit the score aquired is added to the screen
            g.setColor(Color.BLUE);
            g.drawString("+ 100", mX, mY -= 1);
          }
        }

        // draws player
        p.draw(g);

        // draws invaders enermies
        for (int index = 0; index < invadersList.size(); index++) {
          invadersList.get(index).draw(g);
        }

        // constructs beam shot on space press
        if (controller.getKeyState(KeyEvent.VK_SPACE)) {
          if (canFire) {
            a = new Beam(p.getX() + (p.getWidth() / 2) - (a.width / 2), p.getY(), Color.RED);
            canFire = false;
          }
        }
        // if beam is consructed it is drawn
        if (a != null) {
          a.draw(g);
        }
        // if the invaders can fire at random
        if(invadersCanFrie){
          for (int i = 0; i < invadersList.size(); i++) {
            if(r.nextInt(30) == i){
              //constucts and add the plasma to the list
              plasma = new Plasma(invadersList.get(i).getX(), invadersList.get(i).getY(), 0, 4, Color.YELLOW);
              plasmaList.add(plasma);
            }
          }
          invadersCanFrie = false;
        }
        //while the plasma is constructed move down at given speed
        if(plasma != null){
          for (int i = 0; i < plasmaList.size(); i++) {
            plasmaList.get(i).setY(plasmaList.get(i).getY() + plasmaList.get(i).yS);
            if(plasmaList.get(i).getY() > BOARD_HEIGHT){
              plasmaList.remove(i);
            }
          }
        }
        //draws Plasma
        for (int i = 0; i < plasmaList.size(); i++) {
          plasmaList.get(i).draw(g);
        }
        try {
          for (int j = 0; j < Shields.size(); j++) {
            for (int i = 0; i < plasmaList.size(); i++) {
              if (plasmaList.get(i).isColliding(Shields.get(j))) {
                //full health - 75% health
                if (Shields.get(j).getColor().equals(Color.BLUE)) {
                  Shields.get(j).setColor(new Color(0, 191, 255));
                  plasmaList.remove(i);
                }
                //75% health - 50% health
                else if (Shields.get(j).getColor().equals(new Color(0, 191, 255))) {
                  Shields.get(j).setColor(new Color(0, 255, 128));
                  plasmaList.remove(i);
                }
                //50% health - 25% health
                else if (Shields.get(j).getColor().equals(new Color(0, 255, 128))) {
                  Shields.get(j).setColor(Color.WHITE);
                  plasmaList.remove(i);
                }
                //25% health - gone
                else if (Shields.get(j).getColor().equals(Color.WHITE)) {
                  Shields.remove(j);
                  plasmaList.remove(i);
                }
              }
            }
          }
        } catch (IndexOutOfBoundsException e) {}
        //checks for colition and removes one life 
        for (int i = 0; i < plasmaList.size(); i++) {
          if(plasmaList.get(i).isColliding(p)){
            plasmaList.remove(i);
            lifeList.remove(lifeList.size() - 1);
            lives -= 1;
          }
        }
        //allows to fire on empty list
        if(plasmaList.isEmpty()){
          invadersCanFrie = true;
        }
        // draws the plasma shields
        for (int i = 0; i < Shields.size(); i++) {
          Shields.get(i).draw(g);
        }
        //sets all fonts and sizes
        g.setFont(new Font("papyrus", Font.PLAIN, 28));
        // draw the life counter display
        g.setColor(Color.BLUE);
        g.drawString("Lives:", (int) Math.round(BOARD_WIDTH * .2), 40);
        for (int i = 0; i < lifeList.size(); i++) {
          lifeList.get(i).lifeDraw(g);
        }

        // draws the score display
        g.setColor(Color.BLUE);
        g.drawString("Score: " + score, (int) Math.round(BOARD_WIDTH * .4), 40);

        // draws Highscore display
        g.setColor(Color.BLUE);
        g.drawString("Highscore: " + h.getHighScore(), (int) Math.round(BOARD_WIDTH * .6), 40);

        // draws level display
        g.setColor(Color.BLUE);
        g.drawString("Level " + level, (int) Math.round(BOARD_WIDTH * .8), 40);
        break;
      //endscreen case
      case 2:
      //draws endscreen text
        eS.draw(g);
        yes.draw(g);
        no.draw(g);
        top5.draw(g);
        break;
    }
    // clears unused graphics
    g.dispose();
  }

  public void updateGameState() {

    // player can now move
    p.move();

    // Makes enemies move and change direction at borders
    try {
      if ((invadersList.get(invadersList.size() - 1).getX() + invadersList.get(invadersList.size() - 1).getxS()) > (BOARD_WIDTH
          - invadersList.get(invadersList.size() - 1).getWidth())
          || (invadersList.get(0).getX() + invadersList.get(0).getxS()) < 0) {
        for (int i = 0; i < invadersList.size(); i++) {
          invadersList.get(i).setxS(invadersList.get(i).getxS() * -1);
          invadersList.get(i).setY(invadersList.get(i).getY() + invadersList.get(i).getHeight());
        }
      } else {
        for (int i = 0; i < invadersList.size(); i++) {
          invadersList.get(i).move();
        }
      }
    } catch (IndexOutOfBoundsException e) {
    }

    // move beam
    if (a != null) {
      a.move();
      if (a.getY() < 0) {
        a = new Beam(BOARD_WIDTH, 0, null);
        canFire = true;
      }
      // Collision checking
      for (int i = 0; i < invadersList.size(); i++) {
        //removes enermy invaders on hit
        if (a.isColliding(invadersList.get(i))) {
          a = new Beam(BOARD_WIDTH, 0, null);
          canFire = true;
          score += 100;
          onHit = true;
          mX = invadersList.get(i).getX();
          mY = invadersList.get(i).getY();
          invadersList.remove(i);
        }
      }
      //damages shields on hit
      for (int i = 0; i < Shields.size(); i++) {
        //collsion check
        if (a.isColliding(Shields.get(i))) {
          //full health - 75% health
          if (Shields.get(i).getColor().equals(Color.BLUE)) {
            Shields.get(i).setColor(new Color(0, 191, 255));
            a = new Beam(BOARD_WIDTH, 0, null);
            canFire = true;
          }
          //75% health - 50% health
          else if (Shields.get(i).getColor().equals(new Color(0, 191, 255))) {
            Shields.get(i).setColor(new Color(0, 255, 128));
            a = new Beam(BOARD_WIDTH, 0, null);
            canFire = true;
          }
          //50% health - 25% health
          else if (Shields.get(i).getColor().equals(new Color(0, 255, 128))) {
            Shields.get(i).setColor(Color.WHITE);
            a = new Beam(BOARD_WIDTH, 0, null);
            canFire = true;
          }
          //25% health - gone
          else if (Shields.get(i).getColor().equals(Color.WHITE)) {
            Shields.remove(i);
            a = new Beam(BOARD_WIDTH, 0, null);
            canFire = true;
          }
        }
      }
    }
    //counter for invaders and shields
    for (int i = 0; i < invadersList.size(); i++) {
      for (int j = 0; j < Shields.size(); j++) {
        //collition check and removal of shield on contact
        if (invadersList.get(i).isColliding(Shields.get(j))) {
          Shields.remove(j);
        }
      }
       // life lost on viruies going too low
      if (invadersList.get(i).getY() >= 780) {
        invadersList.clear();
        lifeList.clear();
        Shields.clear();
        plasmaList.clear();
        lives -= 1;
        inGame();
      }
    }
    // Goes to next level, resets all lists, sets all counters to correct values
    if (invadersList.isEmpty()) {
      Shields.clear();
      lifeList.clear();
      plasmaList.clear();
      level += 1;
      inGame();
    }

    // Ends game if player runs out of lives
    if (lifeList.isEmpty()) {
      screen = 2;
    }
  }
  //startscreen 
  public void startScreen() {
    //on enter press move to game
    if (controller.getKeyState(KeyEvent.VK_ENTER)) {
      screen = 1;
    }
  }
  //endsrcreen
  public void endScreen() {
    if (score >= h.getHighScore()) {
      // updates highscore list, sets text if current score is greater 
      h.updateHighscore(score);
      eS.setMessage("NEW HIGHSCORE\n" + score + "\nDo you want to play again?");
      // updates highscore list sets text if lower than top score
    } else if (score < h.getHighScore()) {
      h.updateHighscore(score);
      eS.setMessage("Game Over!\n" + score + "\nDo you want to play again?");
    }
    //move selecting color (WHITE) from yes to no
    if (controller.getKeyState(KeyEvent.VK_LEFT) & yes.getColor().equals(Color.WHITE) || controller.getKeyState(KeyEvent.VK_RIGHT) & yes.getColor().equals(Color.WHITE)) {
      yes.setColor(Color.BLUE);
      no.setColor(Color.WHITE);
      try {
        Thread.sleep(250);
      }
      catch (InterruptedException e) {}
    ////move selecting color (WHITE) from no to yes
    } else if (controller.getKeyState(KeyEvent.VK_LEFT) & no.getColor().equals(Color.WHITE) || controller.getKeyState(KeyEvent.VK_RIGHT) & no.getColor().equals(Color.WHITE)) {
      yes.setColor(Color.WHITE);
      no.setColor(Color.BLUE);
      try {
        Thread.sleep(250);
      }
      catch (InterruptedException e) {}
    }
    // Gives the player an option to play again or exit
    // If they choose to play again, this resets every element in the game
    if (controller.getKeyState(KeyEvent.VK_ENTER) & yes.getColor().equals(Color.WHITE)) {
      lifeList.clear();
      invadersList.clear();
      Shields.clear();
      plasmaList.clear();
      score = 0;
      level = 1;
      lives = 3;
      canFire = true;
      inGame();
      screen = 0;
    }
    //exit if you dont want to playagain
    else if(controller.getKeyState(KeyEvent.VK_ENTER) & no.getColor().equals(Color.WHITE)){
      System.exit(0);
    }
  }
  
  public Board() {

    //set size
    this.setSize(d);
    this.setPreferredSize(d);
    this.setBackground(Color.BLACK);
  
    // Register KeyboardController as KeyListener
    controller = new KeyboardListener();
    this.addKeyListener(controller);

    // Register MouseClickListener 
    addMouseListener(new MouseClickListener(this));
    this.inGame();
    this.setFocusable(true);
    this.requestFocusInWindow();
  }
  public void run() {
    // sets game timer to tick 128 times a second
    gameTimer = new Timer((int) Math.round(1000 / TICK_RATE), new ActionListener(){
      @Override
      // each tick triggers action
      public void actionPerformed(ActionEvent e){
        switch(screen){
          case 0:
          // startscreen loop
          startScreen();
          break;
          case 1:
          // updates game state each tick
          updateGameState();
          break;
          case 2:
          // startscreen loop
          endScreen();
          break;
        }
        // repaints per tick
        repaint();
      }
    });

    //on screen time length for points markers
    gameTimerOnHit = new Timer(1000, new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        //resets on hit per 1 second
        onHit = false;
      }
    });

    //allows timers to repeat and then starts them
    gameTimer.setRepeats(true);
    gameTimer.start();
    gameTimerOnHit.setRepeats(true);
    gameTimerOnHit.start();
  }
}

