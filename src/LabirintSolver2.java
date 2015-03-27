import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Anisia-Ioana on 3/27/2015.
 */
public class LabirintSolver2 implements LabyrinthSolver, LabyrinthModel, LabyrinthObserver {
    public static Locatie locatie;
    private Integer[][] maze;
    private ArrayList<Locatie> parcurgere;

    public static final int START = -1;
    public static final int FINISH = 2;
    public static final int FREE = 0;
    public static final int WALL = 1;

    public LabirintSolver2(){
        this.maze=getMatrixFromFile();
        // generareAleatoare(7,7);
        this.deplasari=new ArrayList<String>();
        this.parcurgere=new ArrayList<Locatie>();
    }

    public Integer[][] getMaze() {
        return maze;
    }

    private ArrayList<String> deplasari;
    @Override
    public Integer getRowCount() {
        return maze.length;
    }

    @Override
    public Integer getColumnCount() {
        return maze[0].length;
    }

    public static void setLocatie(Locatie locatie) {
        LabirintSolver1.locatie = locatie;
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

    public ArrayList<String> getDeplasari() {
        return deplasari;
    }

    @Override
    public Boolean nextCellToExplore(int i, int j) {
        if(i<0 || j<0 || j>=getColumnCount()|| i>= getRowCount()) return false;
        if (maze[i][j]==0 || maze[i][j]==2)
            return true;
        return false;
    }
    @Override
    public void processCell(Locatie loc) {
        int i=loc.i;
        int j=loc.j;
        for(String item: deplasari){
            System.out.print(item + " ");
        }
        System.out.println();
    }

    @Override
    public void processSolution(Locatie loc) {
        int i=loc.i;
        int j=loc.j;
        for(String item: deplasari){
            System.out.print(item + " ");
        }
        System.out.println();
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
