package View;

import Controller.Movement;
import Model.Ghost;
import Model.MapElementManager;
import Model.Pacman;
import Model.PacmanGameBoard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable{
    JLabel label;
    public int sliderValue;

    boolean endgame = false;
    final int originalObjectSize = 6;
    int scale;
    public int objectSize;
    int col;
    int row;
    int screenWidth;
    int screenHeight;
    Movement key = new Movement();

    PacmanGameBoard pacmanGameBoard;
    Pacman pacman;
    MapElementManager mapElementManager;
    Ghost ghost1;
    Ghost ghost2;
    Ghost ghost3;
    Ghost ghost4;
    PacmanFrame pacmanFrame;
    Menu menu;



    Thread gameThread;

    public GamePanel(PacmanFrame pacmanFrame, Menu menu, int sliderValue){
        initData(sliderValue);
        this.menu = menu;
        this.pacmanFrame = pacmanFrame;
        this.sliderValue = sliderValue;
        this.setPreferredSize(new Dimension(screenWidth,screenHeight+60));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
        pacmanGameBoard = new PacmanGameBoard(sliderValue,sliderValue);
        pacman = new Pacman(this,key, pacmanGameBoard);
        mapElementManager = new MapElementManager(this,pacmanGameBoard);
        ghost1 = new Ghost(this,pacmanGameBoard,3*objectSize,3*objectSize);
        ghost2 = new Ghost(this,pacmanGameBoard,5*objectSize,5*objectSize);
        ghost3 = new Ghost(this,pacmanGameBoard,9*objectSize,9*objectSize);
        ghost4 = new Ghost(this,pacmanGameBoard,8*objectSize,8*objectSize);
    }

    public void initData(int sliderValue){
        col = sliderValue;
        row = sliderValue;

        if(sliderValue > 25 && sliderValue < 50){
            scale = 3;
        }else if (sliderValue > 50 && sliderValue < 75){
            scale = 2;
        }else if (sliderValue > 75 ) {
            scale = 1;
        }else{
            scale = 4;
        }
        objectSize = originalObjectSize * scale;
        screenWidth = col * objectSize;
        screenHeight = row * objectSize;
    }




    public void startGameTread(){
        gameThread = new Thread(this);
        gameThread.start();

    }

    int t = 0;
    int sec = 0;
    int min = 0;
    @Override
    public void run() {

        while(gameThread != null && !endgame){
            update();
            repaint();

            if(t == 100){
                ghost2.powerUp();
                ghost1.powerUp();
                ghost3.powerUp();
                ghost4.powerUp();


                t=0;
                sec += 1;
                if(sec == 60){
                    sec = 0;
                    min += 1;
                }
            }
            dead();
            win();
            backToMenu();
            try {
                Thread.sleep(10);
                t += 1;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        ghost1.update();
        ghost2.update();
        ghost3.update();
        ghost4.update();
        pacman.update();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        mapElementManager.draw(g2);
        pacman.draw(g2);
        ghost1.draw(g2);
        ghost2.draw(g2);
        ghost3.draw(g2);
        ghost4.draw(g2);
        scorePrinter(g);
        livesPrinter(g);
        timePrinter(g);
        g2.dispose();
    }

    public void scorePrinter(Graphics g){
        g.drawString("my score: " + pacman.score,4 * objectSize, screenHeight+40);
    }

    public void timePrinter(Graphics g){

        g.drawString("time: " + min + ":" + sec , 9 * objectSize, screenHeight+40);
    }

    public void livesPrinter(Graphics g){
        BufferedImage heart;
        try {
            heart = ImageIO.read(new File("src\\Model\\images\\heart.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < pacman.lives; i++) {
            g.drawImage(heart,i*objectSize,screenHeight+objectSize,null);
        }

    }



    public void dead(){
        if ((pacman.x == ghost1.x && pacman.y == ghost1.y) || (pacman.x == ghost2.x && pacman.y == ghost2.y) ||
//                (pacman.x+1 == ghost1.x && pacman.y == ghost1.y) || (pacman.x+1 == ghost2.x && pacman.y == ghost2.y) ||
//                (pacman.x-1 == ghost1.x && pacman.y == ghost1.y) || (pacman.x-1 == ghost2.x && pacman.y == ghost2.y) ||
//                (pacman.x == ghost1.x && pacman.y+1 == ghost1.y) || (pacman.x == ghost2.x && pacman.y+1 == ghost2.y) ||
//                (pacman.x == ghost1.x && pacman.y-1 == ghost1.y) || (pacman.x == ghost2.x && pacman.y-1 == ghost2.y) ||
                (pacman.x == ghost3.x && pacman.y == ghost3.y) || (pacman.x == ghost4.x && pacman.y == ghost4.y)){
//                (pacman.x+1 == ghost3.x && pacman.y == ghost3.y) || (pacman.x+1 == ghost4.x && pacman.y == ghost4.y) ||
//                (pacman.x-1 == ghost3.x && pacman.y == ghost3.y) || (pacman.x-1 == ghost4.x && pacman.y == ghost4.y) ||
//                (pacman.x == ghost3.x && pacman.y+1 == ghost3.y) || (pacman.x == ghost4.x && pacman.y+1 == ghost4.y) ||
//                (pacman.x == ghost3.x && pacman.y-1 == ghost3.y) || (pacman.x == ghost4.x && pacman.y-1 == ghost4.y)){
            pacman.lives -= 1;
            if(pacman.lives < 1) {
                gameOver();
            }
        }
    }

    public void win(){
        boolean won = true;
        for (int i = 0; i < pacmanGameBoard.boardHeight+1; i++) {
            for (int j = 0; j < pacmanGameBoard.boardLenght+1; j++) {
                if(pacmanGameBoard.getValue(j,i) == 1){
                    won = false;
                }
            }
        }
        if (won){
            gameOver();
        }
    }

    public void gameOver(){
        endgame = true;
        pacmanFrame.dispose();
        String userName = JOptionPane.showInputDialog(null, "Enter Player name:", "Player name", JOptionPane.PLAIN_MESSAGE);
        menu.scoreBoard.addScore(userName,pacman.score);
        menu.scoreBoard.saveScoreBoard("scoreBord.ser");
        menu.scoreBoard.loadScoreBoard("scoreBord.ser");
    }

//    public void scaling(){
//        Rectangle rectangle = pacmanFrame.getBounds();
//        int width = rectangle.width;
//        int height = rectangle.height;
//        if((double)width/screenWidth > (double)height/screenHeight){
//            scale = (double)width/screenWidth;
//        }else{
//            scale = (double)height/screenHeight;
//        }
//
//        System.out.println(scale);
//    }

    public void backToMenu(){
        if (key.backToMenu){
            Menu menu = new Menu();
            key.backToMenu = false;
        }
    }



}
