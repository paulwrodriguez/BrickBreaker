package com.company.paul;

import sun.plugin2.util.ColorUtil;
import sun.text.resources.ro.CollationData_ro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements KeyListener, ActionListener {

    // Properties
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;
    private Timer timer;
    private int delay = 8; // speed of ball
    private int playerX = 310;
    private int ballPosX = 120;
    private int ballPosY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private int height = 600;
    private int width = 700;
    private MapGenerator mapGenerator;

    public GamePlay(){
        mapGenerator = new MapGenerator(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g){
        // background
        g.setColor(Color.BLACK);
        g.fillRect(1,1, width - 8,height - 8);

        // drawing map
        mapGenerator.draw((Graphics2D) g);

        // borders
        g.setColor(Color.YELLOW);
        g.fillRect(0,0,3,height - 8);
        g.fillRect(0,0,width - 8,3);
        g.fillRect(691,0,3,height - 8);

        // scores
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("" + score, 590, 30);

        // the paddle
        g.setColor(Color.GREEN);
        g.fillRect(playerX, 550, 100, 8);

        // the ball
        g.setColor(Color.YELLOW);
        g.fillOval(ballPosX, ballPosY, 20, 20);

        if(play){

            // meets the paddle
            if(new Rectangle(ballPosX, ballPosY, 20,20).intersects(new Rectangle(playerX, 550, 100, 8))){
                ballYdir = -ballYdir;
            }

            A: for(int i = 0; i < mapGenerator.map.length; ++i){
                for(int j = 0; j < mapGenerator.map[i].length; ++j){
                    if(mapGenerator.map[i][j] > 0 ){
                        int brickX = j * mapGenerator.brickWidth + 80;
                        int brickY = i * mapGenerator.brickHeight + 50;
                        int brickWidth = mapGenerator.brickWidth;
                        int brickHeight = mapGenerator.brickHeight;

                        Rectangle rectangle = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20);
                        Rectangle brickRec = rectangle;

                        if(ballRect.intersects(brickRec)){
                            mapGenerator.setBrickValue(0, i, j);
                            totalBricks--;
                            score +=5;

                            if(ballPosX + 19 <= brickRec.x || ballPosX + 1 >= brickRec.x + brickRec.width){
                                ballXdir = -ballXdir;
                            }
                            else {
                                ballYdir = -ballYdir;
                            }
                            break A;
                        }
                    }
                }
            }

            ballPosX += ballXdir;
            ballPosY += ballYdir;
            if(ballPosX < 0){
                ballXdir = -ballXdir;
            }
            if(ballPosY < 0 ){
                ballYdir = -ballYdir;
            }
            if(ballPosX > 670){ // width - diameter of ball - border (currently 674)
                ballXdir = -ballXdir;
            }
        }

        if(ballPosY > 570) {
            play = false;
            ballYdir = 0;
            ballXdir = 0;

            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over, Scores: " + score, 190, 300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart ", 190, 350);

        }
        if(totalBricks <= 0) {
            play = false;
            ballYdir = 0;
            ballXdir = 0;

            g.setColor(Color.GREEN);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You Won, Scores: " + score, 190, 300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart ", 190, 350);

        }

        g.dispose();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX > 600 ){
                playerX = 600;
            }
            else{
                moveRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX < 10 ){
                playerX = 10;
            }
            else {
                moveLeft();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
                play = true;
                ballPosX = 120;
                ballPosY = 350;
                ballXdir = -1;
                ballYdir = -2;
                playerX = 310;
                score = 0;
                totalBricks = 21;
                mapGenerator = new MapGenerator(3,7);
                repaint();
            }
        }
    }

    public void moveLeft() {
        play = true;
        playerX-=20;
    }

    public void moveRight() {
        play = true;
        playerX +=20;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
