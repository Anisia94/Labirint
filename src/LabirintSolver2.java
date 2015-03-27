import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Anisia-Ioana on 3/27/2015.
 */
public class LabirintSolver2 implements LabyrinthSolver, LabyrinthModel, LabyrinthObserver {
    public Locatie locatie;
    private Integer[][] maze;
    private ArrayList<Locatie> parcurgere;

    public static final int START = -1;
    public static final int FINISH = 2;
    public static final int FREE = 0;
    public static final int WALL = 1;

    public LabirintSolver2() {
        this.maze = getMatrixFromFile();
        // generareAleatoare(7,7);
        this.deplasari = new ArrayList<String>();
        this.parcurgere = new ArrayList<Locatie>();
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
        if (maze[i][j] == 0)
            return true;
        return false;
    }

    @Override
    public Boolean isWallAt(int i, int j) {
        if (maze[i][j] == 1)
            return true;
        return false;
    }

    @Override
    public Locatie getStartCell() {
        int l = 0, c = 0;
        outside:
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                if (maze[i][j] == -1) {
                    l = i;
                    c = j;
                    break outside;
                }
            }
        }
        return new Locatie(c, l);
    }

    @Override
    public Locatie getFinishCell() {
        int l = 0, c = 0;
        outside:
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                if (maze[i][j] == 2) {
                    l = i;
                    c = j;
                    break outside;
                }
            }

        }
        return new Locatie(l, c);
    }

    public Integer[][] getMatrixFromFile() {
        Integer[][] labirint = new Integer[5][5];
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
        if (i < 0 || j < 0 || j >= getColumnCount() || i >= getRowCount()) return false;
        if (maze[j][i] == 0 || maze[j][i] == 2)
            return true;
        return false;
    }

    @Override
    public void processCell(Locatie loc) {
        int i = loc.i;
        int j = loc.j;
        for (String item : deplasari) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    @Override
    public void processSolution(Locatie loc) {
        int i = loc.i;
        int j = loc.j;
        for (String item : deplasari) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public void generareAleatoare(int row, int column) {
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

    public String Pas(Locatie first, Locatie second) {
        if (second.i == first.i && second.j+1==first.j){
            return "U";
        }
        if (second.i == first.i && second.j-1==first.j){
            return "D";

        }
        if (second.i+1 == first.i && second.j==first.j){
            return "L";

        }
        if (second.i-1 == first.i && second.j==first.j){
            return "R";

        }
        return "H";
    }
    public void getSolution() throws CloneNotSupportedException {
        GraphStructure graph = new GraphStructure().genGraph(getMaze());
        LinkedList<String> visited = new LinkedList();
        locatie = getStartCell();
        visited.add(String.valueOf(locatie.i) + "," + (String.valueOf(locatie.j)));
        BFSImplementation bfsImplementation = new BFSImplementation();
        bfsImplementation.breadthFirst(graph, visited);
        LabirintView3 labView3 = new LabirintView3();
        labView3.setMaze(getMaze());
        String mutare;

        for (ArrayList<Locatie> locaties : BFSImplementation.allPath) {
            locatie = getStartCell();
            Locatie first = getStartCell();
            Locatie second = getStartCell();
            parcurgere = new ArrayList<Locatie>();

            deplasari.clear();
            for (Locatie locatie1 : locaties) {
                second=locatie1;
                mutare = Pas(first,second);
                if (mutare.equals("H")) {
                    continue;
                }
                try {
                    if (mutare.equals("R")) {
                        if (nextCellToExplore(locatie.i+1, locatie.j)) {
                            locatie.i = locatie.i+1;
                            parcurgere.add((Locatie) locatie.clone());
                            deplasari.add("r");
                            if (maze[locatie.i][locatie.j] == FINISH) {
                                System.out.println("Am ajuns la final!");
                                processSolution(locatie);
                                processCell(locatie);
                                System.out.println(labView3.toString5(parcurgere,locatie));
                                continue;
                            }

                        } else {
                            System.out.println("Nu este permis incercati alta varianta!");
                        }
                    } else if (mutare.equals("D")) {
                        if (nextCellToExplore(locatie.i, locatie.j + 1)) {
                            locatie.j = locatie.j+1;
                            parcurgere.add((Locatie) locatie.clone());
                            deplasari.add("d");
                            if (maze[locatie.i][locatie.j] == FINISH) {
                                System.out.println("Am ajuns la final!");
                                processSolution(locatie);
                                processCell(locatie);
                                System.out.println(labView3.toString5(parcurgere,locatie));
                                continue;
                            }

                        } else {
                            System.out.println("Nu este permis incercati alta varianta!");
                        }
                    } else if (mutare.equals("L")) {
                        if (nextCellToExplore(locatie.i-1, locatie.j)) {
                            locatie.i = locatie.i-1;
                            parcurgere.add((Locatie) locatie.clone());
                            deplasari.add("l");
                            if (maze[locatie.i][locatie.j] == FINISH) {
                                System.out.println("Am ajuns la final!");
                                processSolution(locatie);
                                processCell(locatie);
                                System.out.println(labView3.toString5(parcurgere,locatie));
                                continue;
                            }

                        } else {
                            System.out.println("Nu este permis incercati alta varianta!");
                        }
                    } else if (mutare.equals("U")) {
                        if (nextCellToExplore(locatie.i, locatie.j - 1)) {
                            System.out.println("Este permis    <la stanga cu o casuta >");
                            locatie.j = locatie.j-1;
                            parcurgere.add((Locatie) locatie.clone());
                            deplasari.add("u");

                            if (maze[locatie.i][locatie.j] == FINISH) {
                                System.out.println("Am ajuns la final!");
                                processSolution(locatie);
                                processCell(locatie);
                                System.out.println(labView3.toString5(parcurgere,locatie));
                                continue;
                            }

                        } else {
                            System.out.println("Nu este permis incercati alta varianta!");
                        }
                    }
                    else if (mutare.equals("E")){
                        continue;
                    }
                }
                catch (IllegalAccessError e){
                    // e.getStackTrace();

                }
                catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("Eroare! Ati iesit din labirint");
                }
                first=second;

            }
            processCell(locatie);
            System.out.println(labView3.toString6(parcurgere,locatie));
        }
    }
}
