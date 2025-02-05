package View;

import java.io.Serializable;

public class PlayerScore implements Serializable {
    private final String name;
    private final int score;

    public PlayerScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return name + "   -->   " + score;
    }
}
