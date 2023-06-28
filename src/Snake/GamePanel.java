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
        newApple();
        running = true;
        timer = new Timer(DELAY,this);
        timer.start();


    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);

    }

    public void draw(Graphics g){
        if(running) {
            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT); // draw vertical lines
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE); // draw horizontal lines
            }
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE); // draw the apple
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE); // draw the head of the snake
                } else {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE); // draw the body of the snake
                }
            }
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont()); // get the font metrics
            g.drawString("Score: "+applesEaten,(SCREEN_WIDTH - metrics.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize()); // draw the score
        }
        else{
            gameOver(g);
        }

    }
    public void newApple(){
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE; // generate a random x coordinate for the apple
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE; // generate a random y coordinate for the apple
    }
    public void move(){
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i-1]; // move the x coordinates of the snake
            y[i] = y[i-1]; // move the y coordinates of the snake
        }
        switch (direction){
        case 'U':
            y[0] = y[0] - UNIT_SIZE; // move the snake up
            break;
        case 'D':
            y[0] = y[0] + UNIT_SIZE; // move the snake down
            break;
        case 'L':
            x[0] = x[0] - UNIT_SIZE; // move the snake left
            break;
        case 'R':
            x[0] = x[0] + UNIT_SIZE; // move the snake right
            break;
        }
    }
    public void checkApple(){

    }
    public void checkCollisions(){
        // check if the head of the snake collides with the apple
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])){ // if the snake collides with itself
                running = false;
            }
        }
        // check if the head of the snake collides with the left border
        if (x[0] < 0){
            running = false;
        }
        // check if the head of the snake collides with the right border
        if (x[0] > SCREEN_WIDTH){
            running = false;
        }
        // check if the head of the snake collides with the top border
        if (y[0] < 0){
            running = false;
        }
        // check if the head of the snake collides with the bottom border
        if (y[0] > SCREEN_HEIGHT){
            running = false;
        }
        if (!running){
            timer.stop();
        }

    }
    public void gameOver(Graphics g){

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (running){
            move();
            checkApple();
            checkCollisions();
        }
        repaint();

    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if (direction != 'R'){
                        direction = 'L'; // change the direction of the snake to left
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L'){
                        direction = 'R'; // change the direction of the snake to right
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D'){
                        direction = 'U'; // change the direction of the snake to up
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U'){
                        direction = 'D'; // change the direction of the snake to down
                    }
                    break;

            }

        }
    }
}
