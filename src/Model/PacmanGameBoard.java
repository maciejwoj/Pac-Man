
package Model;
import javax.swing.JTable;
import javax.swing.table.*;

public class PacmanGameBoard {


    public int boardHeight;
    public int boardLenght;
    JTable table;


    GameBoardGenerator gameBoardGenerator;
    int[][] gameMap;

    public DefaultTableModel model;


    public PacmanGameBoard(int boardHeight, int boardLenght) {
        this.boardHeight = boardHeight;
        this.boardLenght = boardLenght;
        GameBoardGenerator gameBoardGenerator = new GameBoardGenerator(boardLenght,boardHeight);
        gameMap = gameBoardGenerator.gameMap;
        model = new DefaultTableModel(gameMap.length, gameMap[0].length);
        fillModel(model, gameMap);
        table = new JTable(model);
    }


    public void fillModel(DefaultTableModel model,int[][] gameMap){
        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[0].length; j++) {
                model.setValueAt(gameMap[i][j],i,j);
            }
        }
    }

    public int getValue(int a, int b){
        int w;
        if (a < boardHeight && b < boardLenght)
            w = (int)table.getValueAt(a,b);
        else{
            w = 0;
        }
        return w;
    }


    public void changeValue(DefaultTableModel model, int row, int column, Object newValue) {
        model.setValueAt(newValue, row, column);
    }


}
