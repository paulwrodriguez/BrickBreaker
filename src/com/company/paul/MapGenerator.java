package com.company.paul;

import java.awt.*;

public class MapGenerator {
    public int map[][];
    public int brickWidth;
    public int brickHeight;

    public MapGenerator(int row, int col){
        map = new int[row][col];
        for( int i =0 ; i < map.length; ++i){
            for(int j=0; j < map[i].length; j++){
                map[i][j] = 1;
            }
        }
        brickWidth = 540/col;
        brickHeight = 150/row;
    }

    public void draw(Graphics2D graphics2D){
        for( int i =0 ; i < map.length; ++i) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] > 0) {
                    graphics2D.setColor(Color.WHITE);
                    graphics2D.fillRect(j*brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);

                    graphics2D.setStroke(new BasicStroke(3));
                    graphics2D.setColor(Color.black);
                    graphics2D.drawRect(j*brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }

    public void setBrickValue(int value, int row, int col){
        map[row][col] = value;
    }
}
