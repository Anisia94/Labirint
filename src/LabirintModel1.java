import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Anisia-Ioana on 3/24/2015.
 */
public class LabirintModel1 implements LabyrinthModel{

    private Integer[][] maze;

    public LabirintModel1(){
    this.maze=getMatrixFromFile();
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
    public Integer[][] getMatrixFromFile(){
        Integer[][] labirint=new Integer[5][5];
        try {
            Scanner input = new Scanner(new File("Matrix.txt"));
            int m = 5;
            int n = 5;
            // labirint = new int[m][n];
            while (input.hasNextLine()) {
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        try {
                            labirint[i][j] = input.nextInt();

                        } catch (java.util.NoSuchElementException e) {
                            // e.printStackTrace();
                        }
                    }
                }
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return labirint;
    }

}
