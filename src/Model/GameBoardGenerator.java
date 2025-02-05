package Model;

import java.util.Random;

public class GameBoardGenerator {

    public int gBrows;
    public int gBcolumns;
    public int[][] gameMap;

    public GameBoardGenerator(int gBrows, int gBcolumns) {
        this.gBrows = gBrows;
        this.gBcolumns = gBcolumns;
        gameMap = generateMap(gBrows,gBcolumns);
    }


    static Random random = new Random();
    public static int[][] generateMap(int width, int height) {
        int[][] map = new int[height][width];
        Random random = new Random();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    map[i][j] = 0;
                } else {
                    if (random.nextDouble() < 0.75) {
                        map[i][j] = 1;
                    } else {
                        map[i][j] = 0;
                    }
                }
            }
        }

        return map;
    }

}
