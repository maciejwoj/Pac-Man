package Model;

import Controller.Movement;
import View.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ghost extends MovingObject{
    GamePanel gp;
    PacmanGameBoard pacmanGameBoard;
    public BufferedImage ghost;

    public String currentdirection = "down";

    public Ghost(GamePanel gp, PacmanGameBoard pacmanGameBoard,int row, int col) {
        this.gp = gp;
        this.pacmanGameBoard = pacmanGameBoard;
        setDefaultValues(row,col);
    }

    public void setDefaultValues(int row, int col){
        x = row;
        y = col;
        speed = 1;
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        try {
            ghost = ImageIO.read(new File("src\\Model\\images\\ghost.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g2.drawImage(ghost,x,y,gp.objectSize, gp.objectSize, null);
    }

    public void update(){
        int cl = (pacmanGameBoard.getValue((x+(gp.objectSize/2))/gp.objectSize,((y+(gp.objectSize/2))/ gp.objectSize)));
        boolean up,down,left,right;
        up = false;
        down =false;
        left = false;
        right = false;
        List<String> direction = new ArrayList<>();



        if ( ((pacmanGameBoard.getValue((x+1)/gp.objectSize,((y+gp.objectSize)/ gp.objectSize)-1)) > 0)
            && ((pacmanGameBoard.getValue((x+gp.objectSize-1)/gp.objectSize,((y+gp.objectSize)/ gp.objectSize)-1)) > 0)){
            up = true;


        }

        if (((pacmanGameBoard.getValue((x+gp.objectSize-1)/gp.objectSize,((y)/ gp.objectSize)+1)) > 0)
                && ((pacmanGameBoard.getValue((x+1)/gp.objectSize,((y)/ gp.objectSize)+1)) > 0)){
            down = true;


        }
        if (((pacmanGameBoard.getValue(((x+gp.objectSize)/gp.objectSize)-1,((y+1)/ gp.objectSize))) > 0)
                && ((pacmanGameBoard.getValue(((x+gp.objectSize)/gp.objectSize)-1,((y+gp.objectSize-1)/ gp.objectSize))) > 0)){
            left = true;


        }
        if (((pacmanGameBoard.getValue(((x)/gp.objectSize)+1,((y+1)/ gp.objectSize))) > 0)
                && ((pacmanGameBoard.getValue(((x)/gp.objectSize)+1,((y+gp.objectSize-1)/ gp.objectSize))) > 0)) {
            right = true;

        }

        if(up && !currentdirection.equals("down")){
            direction.add("up");
        }
        if(down && !currentdirection.equals("up")){
            direction.add("down");
        }
        if(left && !currentdirection.equals("right")){
            direction.add("left");
        }
        if(right && !currentdirection.equals("left")){
            direction.add("right");
        }

        boolean isThere = false;
        for (String d : direction){
            if(d.equals(currentdirection)){
                isThere = true;
            }
        }
        Random random = new Random();
        String choice;
        if (isThere) {
            choice = currentdirection;
        }else{

            int ch = random.nextInt(direction.size());
            choice = direction.get(ch);
            currentdirection = choice;
        }


        switch (choice){
            case "up":
                y -= speed;
                break;
            case "down":
                y += speed;
                break;
            case "left":
                x -= speed;
                break;
            case "right":
                x += speed;
                break;
        }
    }

    public void powerUp(){

        Random random = new Random();
        if (random.nextDouble() < 0.05){
            pacmanGameBoard.changeValue(pacmanGameBoard.model,((x+(gp.objectSize/2))/gp.objectSize),((y+(gp.objectSize/2))/ gp.objectSize),3);
        }
    }


}
