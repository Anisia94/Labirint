import java.util.Random;

/**
 * Created by Anisia-Ioana on 3/24/2015.
 */
public class LabirintModel2 implements LabyrinthModel{
    private Integer[][] maze;

    public LabirintModel2(){
        generareAleatoare(5,5);
    }

    @Override
    public Integer getRowCount() {
        return maze.length;
    }

    @Override
    public Integer getColumnCount() {
        return maze[0].length;
    }

    @Override
    public Boolean isFreeAt(int i, int j) {
        if (maze[i][j]==0)
            return true;
        return false;
    }

    @Override
    public Boolean isWallAt(int i,int j) {
        if (maze[i][j]==1)
            return true;
        return false;
    }

    @Override
    public Locatie getStartCell() {
        int l=0,c=0;
        outside:
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                if (maze[i][j]==-1){
                    l=i;
                    c=j;
                    break outside;
                }
            }

        }
        return new Locatie(l,c);
    }

    @Override
    public Locatie getFinishCell() {
        int l=0,c=0;
        outside:
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                if (maze[i][j]==2){
                    l=i;
                    c=j;
                    break outside;
                }
            }

        }
        return new Locatie(l,c);
    }

    public void generareAleatoare(int row,int column) {
        maze = new Integer[row][column];
        Random random = new Random();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int randomInteger = random.nextInt(2);
                maze[i][j] = randomInteger;
            }
        }
        int randomLinie = random.nextInt(column);
        int randomLinieSfarsit = random.nextInt(column);
        maze[0][randomLinie] = -1;
        maze[row - 1][randomLinieSfarsit] = 2;
    }
}
