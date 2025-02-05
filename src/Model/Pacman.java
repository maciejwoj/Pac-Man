package Model;
import View.GamePanel;
import Controller.Movement;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Pacman extends MovingObject{
    GamePanel gp;
    Movement key;
    PacmanGameBoard pacmanGameBoard;

    public int lives = 3;

    public BufferedImage upI,downI,leftI,rightI,full;
    public String direction = "full";

    public boolean goup, godown, goright, goleft;
    public int score = 0;

    public void init(){
        goup = false;
        godown = false;
        goleft = false;
        goright = false;
    }




    public Pacman(GamePanel gp, Movement key, PacmanGameBoard pacmanGameBoard) {
        this.pacmanGameBoard = pacmanGameBoard;
        this.gp = gp;
        this.key = key;
        setDefaultValues();
        init();
    }

    public void setDefaultValues(){
        x = 2 * gp.objectSize;
        y = 2 * gp.objectSize;
        speed = 1;
    }



    public void draw(Graphics2D g2){
        BufferedImage image = null;
        try {
            upI = ImageIO.read(new File("src\\Model\\images\\pacmanUP.png"));
            downI = ImageIO.read(new File("src\\Model\\images\\PacmanDown.png"));
            leftI = ImageIO.read(new File("src\\Model\\images\\pacmanLeft.png"));
            rightI = ImageIO.read(new File("src\\Model\\images\\pacmanRight.png"));
            full = ImageIO.read(new File("src\\Model\\images\\pacmanFull.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        switch (direction) {
            case "up":
                image = upI;
                break;
            case "down":
                image = downI;
                break;
            case "left":
                image = leftI;
                break;
            case "right":
                image = rightI;
                break;
            case "full":
                image = full;
                break;

        }

        g2.drawImage(image,x,y,gp.objectSize, gp.objectSize,null);


    }
    public void update(){



        int cl = (pacmanGameBoard.getValue((x+(gp.objectSize/2))/gp.objectSize,((y+(gp.objectSize/2))/ gp.objectSize)));


        if (key.up && ((pacmanGameBoard.getValue((x+1)/gp.objectSize,((y+gp.objectSize)/ gp.objectSize)-1)) > 0)
                && ((pacmanGameBoard.getValue((x+gp.objectSize-1)/gp.objectSize,((y+gp.objectSize)/ gp.objectSize)-1)) > 0)){
            if(cl == 1|| cl == 3){
                direction = "full";
                pacmanGameBoard.changeValue(pacmanGameBoard.model,((x+(gp.objectSize/2))/gp.objectSize),((y+(gp.objectSize/2))/ gp.objectSize),2);
                score += 10;
            }
            if(cl == 3){
                getPower();
            }
            y -= speed;
            direction = "up";
        }

        else if (key.down && ((pacmanGameBoard.getValue((x+gp.objectSize-1)/gp.objectSize,((y)/ gp.objectSize)+1)) > 0)
                && ((pacmanGameBoard.getValue((x+1)/gp.objectSize,((y)/ gp.objectSize)+1)) > 0)){
            if(cl == 1|| cl == 3){
                direction = "full";
                pacmanGameBoard.changeValue(pacmanGameBoard.model,((x+(gp.objectSize/2))/gp.objectSize),((y+(gp.objectSize/2))/ gp.objectSize),2);
                score += 10;
            }
            if(cl == 3){
                getPower();
            }
            y += speed;
            direction = "down";
        }
        else if (key.left && ((pacmanGameBoard.getValue(((x+gp.objectSize)/gp.objectSize)-1,((y+1)/ gp.objectSize))) > 0)
                && ((pacmanGameBoard.getValue(((x+gp.objectSize)/gp.objectSize)-1,((y+gp.objectSize-1)/ gp.objectSize))) > 0)){
            if(cl == 1|| cl == 3){
                direction = "full";
                pacmanGameBoard.changeValue(pacmanGameBoard.model,((x+(gp.objectSize/2))/gp.objectSize),((y+(gp.objectSize/2))/ gp.objectSize),2);
                score += 10;
            }
            if(cl == 3){
                getPower();
            }
            x -= speed;
            direction = "left";
        }
        else if (key.right && ((pacmanGameBoard.getValue(((x)/gp.objectSize)+1,((y+1)/ gp.objectSize))) > 0)
                && ((pacmanGameBoard.getValue(((x)/gp.objectSize)+1,((y+gp.objectSize-1)/ gp.objectSize))) > 0)){
            if(cl == 1 || cl == 3){
                direction = "full";
                pacmanGameBoard.changeValue(pacmanGameBoard.model,((x+(gp.objectSize/2))/gp.objectSize),((y+(gp.objectSize/2))/ gp.objectSize),2);
                score += 10;
            }
            if(cl == 3){
                getPower();
            }
            x += speed;
            direction = "right";
        }

    }

    private void getPower() {
        Random random = new Random();
        int ch = random.nextInt(4) + 1;
        switch (ch) {
            case 1:
                Power1();
                break;
            case 2:
                Power2();
                break;
            case 3:
                Power3();
                break;
            case 4:
                Power4();
                break;

        }

    }

    private void Power1(){
        score = 0;
        JOptionPane.showMessageDialog(null, "You lost all your points", "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    private void Power2(){
        x = 2 * gp.objectSize;
        y = 2 *gp.objectSize;
    }
    private void Power3(){
        lives += 1;
    }
    private void Power4(){
        score += 100;
    }

}
