package View;

import View.PacmanFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu extends JFrame implements ActionListener {

    private final JButton startButton;
    private final JButton rankingButton;
    private final JButton exitButton;
    ScoreBoard scoreBoard = new ScoreBoard();

    public Menu() {
        super("Pac-Man");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300, 200));

        ImageIcon image = new ImageIcon("src\\Model\\images\\pacmanLeft.png");
        setIconImage(image.getImage());
        setBackground(Color.black);

        startButton = new JButton("Start game");
        startButton.addActionListener(this);
        startButton.setFocusable(false);
        startButton.setIcon(image);
        startButton.setBackground(Color.cyan);
        rankingButton = new JButton("show scoreboard");
        rankingButton.addActionListener(this);
        rankingButton.setFocusable(false);
        rankingButton.setIcon(image);
        rankingButton.setBackground(Color.gray);
        rankingButton.setForeground(Color.WHITE);
        exitButton = new JButton("Exit game");
        exitButton.addActionListener(this);
        exitButton.setFocusable(false);
        exitButton.setIcon(image);
        exitButton.setBackground(Color.cyan);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1,5,5));
        buttonPanel.add(startButton);
        buttonPanel.add(rankingButton);
        buttonPanel.add(exitButton);
        getContentPane().add(buttonPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton){
            startNewGame();
        } else if (e.getSource() == rankingButton) {
            showScoreboard();
        } else if (e.getSource() == exitButton) {
            dispose();
        }
    }

    private void startNewGame() {
        Slider slider = new Slider(this);
    }
    private void showScoreboard() {
        scoreBoard.showScoreBoard();
        scoreBoard.saveScoreBoard("scoreBoard.ser");
        scoreBoard.loadScoreBoard("scoreBoard.ser");
    }



}

