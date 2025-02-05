package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoard implements Serializable {
    private List<PlayerScore> scoreBoard;
    private DefaultListModel<String> sbmodel;
    private JList<String> sblist;

    public ScoreBoard() {
        scoreBoard = new ArrayList<>();
    }

    public void showScoreBoard(){
        JFrame frame = new JFrame("Scoreboard");
        ImageIcon image = new ImageIcon("src\\Model\\images\\pacmanLeft.png");
        frame.setIconImage(image.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        sbmodel = new DefaultListModel<>();
        sblist = new JList<>(sbmodel);
        sblist.setBackground(Color.BLACK);
        sblist.setForeground(Color.WHITE);
        sblist.setFont(new Font("PacFont", Font.PLAIN, 21));
        JScrollPane scrollPane = new JScrollPane(sblist);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);

    }

    public void addScore(String name, int score) {
        PlayerScore playerScore = new PlayerScore(name, score);
        scoreBoard.add(playerScore);
        SwingUtilities.invokeLater(() -> {
            sbmodel.addElement(playerScore.toString());
        });
    }

    public void saveScoreBoard(String file) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(file)));
            oos.writeObject(scoreBoard);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadScoreBoard(String file) {
        try {
            ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(file)));
            scoreBoard = (List<PlayerScore>) ois.readObject();
            ois.close();
            SwingUtilities.invokeLater(() -> {
                sbmodel.clear();
                for (PlayerScore entry : scoreBoard) {
                    sbmodel.addElement(entry.toString());
                }
            });
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}