package View;

import javax.swing.*;
import java.awt.*;

public class PacmanFrame extends JFrame {
    public int sliderValue;
    Menu menu;
    public PacmanFrame(Menu menu, int sliderValue){
        setVisible(true);
        setTitle("Pac-Man");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        GamePanel gamePanel = new GamePanel(this,menu,sliderValue);
        gamePanel.startGameTread();
        getContentPane().add(gamePanel);
        pack();

    }
}
