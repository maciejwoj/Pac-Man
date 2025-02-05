package Model;

import View.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MapElementManager {

    GamePanel gp;
    MapElements[] mapElements;

    PacmanGameBoard pacmanGameBoard;
    public MapElementManager(GamePanel gp,PacmanGameBoard pacmanGameBoard){
        this.gp = gp;
        this.pacmanGameBoard = pacmanGameBoard;
        mapElements = new MapElements[4];

    }

    public void draw(Graphics2D g2) {
        try {
            mapElements[0] = new MapElements();
            mapElements[0].image = ImageIO.read(new File("src\\Model\\images\\wall.png"));
            mapElements[0].collision = true;
            mapElements[1] = new MapElements();
            mapElements[1].image = ImageIO.read(new File("src\\Model\\images\\point2.png"));
            mapElements[2] = new MapElements();
            mapElements[2].image = ImageIO.read(new File("src\\Model\\images\\space.png"));
            mapElements[3] = new MapElements();
            mapElements[3].image = ImageIO.read(new File("src\\Model\\images\\chery.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int x = 0;
        int y =0;

        for (int i = 0; i < pacmanGameBoard.boardHeight+1; i++) {
            for (int j = 0; j < pacmanGameBoard.boardLenght+1; j++) {
                g2.drawImage(mapElements[pacmanGameBoard.getValue(j,i)].image,x,y,gp.objectSize, gp.objectSize, null);
                x += gp.objectSize;
            }
            y += gp.objectSize;
            x = 0;
        }
    }
}
