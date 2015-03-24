import org.omg.CORBA.MARSHAL;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/**
 * Created by Anisia-Ioana on 3/20/2015.
 */
/*
public class Labirint implements LabyrinthModel, LabyrinthView, LabyrinthSolver {
    private Integer[][] maze;
    private Integer nrColoane;
    private Integer nrLinii;
    ArrayList<String> deplasari;

    public static final int START = -1;
    public static final int FINISH = 2;
    public static final int FREE = 0;
    public static final int WALL = 1;

//    class Locatie{
//        public int i;
//        public int j;
//        public Locatie(int ii, int jj){
//            i=ii;
//            j=jj;
//        }
//    }

    public Labirint(){
        //this.maze=getMatrixFromFile();

        generareAleatoare(5,5);
        this.nrColoane= maze[0].length;
        this.nrLinii=maze.length;
        this.deplasari=new ArrayList<String>();
    }

    Locatie locatie;

    public Integer[][] getMaze(){
     return maze;
    }
    @Override
    public Integer getRowCount() {
        return nrLinii;
    }

    @Override
    public Integer getColumnCount() {
        return nrColoane;
    }

    @Override
    public Locatie isFreeAt(int i, int j) {
        Locatie loc=new Locatie(-1,-1);
        if(maze[i][j-1]==FREE) loc=new Locatie(i,j-1);
        else if(maze[i+1][j-1]==FREE)loc=new Locatie(i+1,j-1);
        else if(maze[i+1][j]==FREE)loc=new Locatie(i+1,j);
        else if(maze[i+1][j+1]==FREE)loc=new Locatie(i+1,j+1);
        else if(maze[i][j+1]==FREE)loc=new Locatie(i,j+1);
        else if(maze[i-1][j+1]==FREE)loc=new Locatie(i-1,j+1);
        else if(maze[i-1][j]==FREE)loc=new Locatie(i-1,j);
        else if(maze[i-1][j-1]==FREE)loc=new Locatie(i-1,j-1);
        return loc;
    }

    @Override
    public Integer isWallAt() {
        return null;
    }

    @Override
    public Integer getStartCell() {
        return null;
    }

    @Override
    public Integer getFinishCell() {
        return null;
    }

    @Override
    public void constructLabirint() {

    }

    @Override
    public String toString2() {
        String result="\n";
        for (int row = 0; row < maze.length; row++) {
            for (int column = 0; column < maze[0].length; column++) {
               result+=maze[row][column]+" ";
            }
            result+="\n";
        }
        return result;
    }

    @Override
    public String toString1() {

        String result = "\n";

        for (int row = 0; row < maze.length; row++) {
            for (int column = 0; column < maze[0].length; column++) {
                result += "|";
                if (maze[row][column] == -1)
                    result += "S";
                else if (maze[row][column] == 0)
                    result += " ";
                else if (maze[row][column] == 2)
                    result += "F";
                else
                    result += "*";
                // result += labirint[row][column] + " ";

            }
            result += "|";
            result += "\n";
        }
        return result;
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

    @Override
    public void nextCellToExplore(int i, int j){

    }
    public void nextCellToExplore1() {
        int l=0,c=0;
       // locatie=new Locatie(0,0);
        outerloop:
        for (int i = 0; i <nrLinii; i++) {
            for (int j = 0; j <nrColoane ; j++) {
                if(maze[i][j]==-1)
                    l=i;
                    c=j;
                break outerloop;
            }
        }

        locatie=new Locatie(l,c);
        Scanner scaner=new Scanner(System.in);
        String mutare;
        try {
            System.out.println("Ne aflam pe pozitia Start...");
          //  System.out.println();
            do {
                System.out.println("Introduceti urmatoarea operatiune: [L,R,U,D,E-exit]");
                mutare = scaner.next();
                if (mutare.equals("D")) {
                    if (goDown(locatie.i, locatie.j)) {
                        System.out.println("Este permis... am coborat cu o casuta :)");
                        deplasari.add("d");
                        if (maze[locatie.i][locatie.j] == FINISH) {
                            System.out.println("Am ajuns la final!");
                            break;
                        }
                    } else {
                        System.out.println("Nu este permis incercati alta varianta!");
                    }
                } else if (mutare.equals("R")) {
                    if (goRight(locatie.i, locatie.j)) {
                        System.out.println("Este permis... la dreapta cu o casuta :)");
                        deplasari.add("r");
                        if (maze[locatie.i][locatie.j] == FINISH) {
                            System.out.println("Am ajuns la final!");
                            break;
                        }
                    } else {
                        System.out.println("Nu este permis incercati alta varianta!");
                    }
                } else if (mutare.equals("U")) {
                    if (goUp(locatie.i, locatie.j)) {
                        System.out.println("Este permis... am urcat cu o casuta :)");
                        deplasari.add("u");
                        if (maze[locatie.i][locatie.j] == FINISH) {
                            System.out.println("Am ajuns la final!");
                            break;
                        }
                    } else {
                        System.out.println("Nu este permis incercati alta varianta!");
                    }
                } else if (mutare.equals("L")) {
                    if (goLeft(locatie.i, locatie.j)) {
                        System.out.println("Este permis... am facut stanga cu o casuta :)");
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

    public boolean goDown(int i,int j){
        if (maze[i+1][j]==FREE || maze[i + 1][j] == FINISH) {
            locatie.i = locatie.i+1;
            return true;
        }
        return false;
    }

    public boolean goLeft(int i,int j){
        if (maze[i][j-1]==FREE || maze[i][j-1] == FINISH) {
            locatie.j = locatie.j - 1;
            return true;
        }
        return false;
    }
    public boolean goUp(int i,int j){
        if (maze[i+1][j]==FREE || maze[i+1][j] == FINISH) {
            locatie.i = locatie.i +1;
            return true;
        }
        return false;
    }
    public boolean goRight(int i,int j){
        if (maze[i][j+1]==FREE || maze[i][j+1] == FINISH) {
            locatie.j = locatie.j + 1;
            return true;
        }
        return false;
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
*/