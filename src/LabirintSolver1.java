import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Anisia-Ioana on 3/24/2015.
 */
public class LabirintSolver1 implements LabyrinthSolver, LabyrinthModel {

    private Integer[][] maze;
    Locatie locatie;
    public static final int START = -1;
    public static final int FINISH = 2;
    public static final int FREE = 0;
    public static final int WALL = 1;

    public LabirintSolver1(){
        this.maze=getMatrixFromFile();
        this.deplasari=new ArrayList<String>();
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
        if (maze[i][j]==0 || maze[i][j]==2)
            return true;
        return false;
    }
    public void getSolution(){
        //setam locatia de start
       Locatie locatie=getStartCell();
        Scanner scaner=new Scanner(System.in);
        String mutare;
        try {
            System.out.println("Ne aflam pe pozitia START...");
            do {
                System.out.println("Introduceti urmatoarea operatiune: [L,R,U,D,E-exit]");
                mutare = scaner.next();
                if (mutare.equals("D")) {
                    if (nextCellToExplore(locatie.i+1, locatie.j)) {
                        System.out.println("Este permis... am coborat cu o casuta :)");
                        locatie.i = locatie.i+1;
                        deplasari.add("d");
                        if (maze[locatie.i][locatie.j] == FINISH) {
                            System.out.println("Am ajuns la final!");
                            break;
                        }
                    } else {
                        System.out.println("Nu este permis incercati alta varianta!");
                    }
                } else if (mutare.equals("R")) {
                    if (nextCellToExplore(locatie.i, locatie.j + 1)) {
                        System.out.println("Este permis... la dreapta cu o casuta :)");
                        locatie.j = locatie.j+1;
                        deplasari.add("r");
                        if (maze[locatie.i][locatie.j] == FINISH) {
                            System.out.println("Am ajuns la final!");
                            break;
                        }
                    } else {
                        System.out.println("Nu este permis incercati alta varianta!");
                    }
                } else if (mutare.equals("U")) {
                    if (nextCellToExplore(locatie.i-1, locatie.j)) {
                        System.out.println("Este permis... am urcat cu o casuta :)");
                        locatie.i = locatie.i-1;
                        deplasari.add("u");
                        if (maze[locatie.i][locatie.j] == FINISH) {
                            System.out.println("Am ajuns la final!");
                            break;
                        }
                    } else {
                        System.out.println("Nu este permis incercati alta varianta!");
                    }
                } else if (mutare.equals("L")) {
                    if (nextCellToExplore(locatie.i, locatie.j - 1)) {
                        System.out.println("Este permis... am facut stanga cu o casuta :)");
                        locatie.j = locatie.j-1;
                        deplasari.add("l");
                        if (maze[locatie.i][locatie.j] == FINISH) {
                            System.out.println("Am ajuns la final!");
                            break;
                        }
                    } else {
                        System.out.println("Nu este permis incercati alta varianta!");
                    }
                }
                else if (mutare.equals("E")){
                    break;
                }
            }while(1==1);
        }
        catch (IllegalAccessError e){
            // e.getStackTrace();

        }
        for (String item : deplasari) {
            System.out.print(item+" ");
        }
    }

}
