package Snake;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25; // size of the objects in the game
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE; // how many objects can fit in the game
    static final int DELAY = 75; // speed of the game
    final int x[] = new int[GAME_UNITS]; // x coordinates of the snake
    final int y[] = new int[GAME_UNITS]; // y coordinates of the snake
    int bodyParts = 6; // initial size of the snake
    int applesEaten; // number of apples eaten
    int appleX; // x coordinate of the apple
    int appleY; // y coordinate of the apple
    char direction = 'R'; // initial direction of the snake
    boolean running = false; // is the game running?
    Timer timer; // timer for the game
    Random random; // random number generator


    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();

    }
    public void startGame(){

    }
    public void paintComponent(Graphics g){

    }

    public void draw(Graphics g){

    }
    public void move(){

    }
    public void checkApple(){

    }
    public void checkCollisions(){

    }
    public void gameOver(Graphics g){

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){

        }
    }
}
