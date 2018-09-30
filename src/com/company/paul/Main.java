package com.company.paul;

import javax.swing.*;

/*
    Improvements:
      1) Remove hardcoded dimensions.
      2) Create more objects
      3) Abstract intersections
      4) Maps should be random
      5)
 */
public class Main {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        GamePlay gamePlay = new GamePlay();
        jFrame.setBounds(600,250,700,600);
        jFrame.setTitle("Brick Breaker");
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(gamePlay);
    }
}
