package View;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Slider implements ChangeListener, ActionListener {

    JFrame frame;
    JPanel panel;
    JLabel label;
    JSlider slider;

    JButton startGame;
    Menu menu;

    Slider(Menu menu) {
        this.menu = menu;
        frame = new JFrame("Pac-man");
        ImageIcon image = new ImageIcon("src\\Model\\images\\pacmanLeft.png");
        frame.setIconImage(image.getImage());
        frame.setBackground(Color.black);
        panel = new JPanel();
        label =  new JLabel();
        slider = new JSlider(10,100,30);
        slider.setPreferredSize(new Dimension(400,200));
        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(5);
        slider.setMajorTickSpacing(30);
        slider.setPaintTrack(true);
        slider.setFont(new Font("Serif", Font.ITALIC, 21));
        slider.setPaintLabels(true);

        label.setText("You choose: " + slider.getValue());
        slider.addChangeListener(this);
        startGame = new JButton("Start Game");
        startGame.addActionListener(this);
        startGame.setFocusable(false);
        startGame.setIcon(image);
        startGame.setBackground(Color.darkGray);
        startGame.setForeground(Color.WHITE);

        panel.add(startGame);
        panel.add(slider);
        panel.add(label);
        frame.add(panel);
        frame.setSize(420,420);
        frame.setVisible(true);

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        label.setText("You choose: " + slider.getValue());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startGame) {
            PacmanFrame pacmanFrame = new PacmanFrame(menu, slider.getValue());
        }
    }
}
