import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Anisia-Ioana on 3/24/2015.
 */
public class LabirintSolver1 implements LabyrinthSolver, LabyrinthModel, LabyrinthObserver, Serializable {

    private Integer[][] maze;
    private ArrayList<Locatie> parcurgere;

    public ArrayList<Locatie> getParcurgere() {
        return parcurgere;
    }

    public Locatie locatie;

    public static final int START = -1;
    public static final int FINISH = 2;
    public static final int FREE = 0;
    public static final int WALL = 1;

    public LabirintSolver1(){
       this.maze=getMatrixFromFile();
       // generareAleatoare(7,7);
        this.deplasari=new ArrayList<String>();
        this.parcurgere=new ArrayList<Locatie>();
        this.locatie=getStartCell();
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
        if(i<0 || j<0 || j>=getColumnCount()|| i>= getRowCount()) return false;
        if (maze[i][j]==0 || maze[i][j]==2)
            return true;
        return false;
    }

    /**
     * REZOLVARE INTERACTIVA
     * @throws CloneNotSupportedException
     */
    public void getSolution() throws CloneNotSupportedException {
        //setam locatia de start
       // setLocatie(getStartCell());
        LabirintView3 labView3=new LabirintView3();
        labView3.setMaze(getMaze());
        Scanner scaner=new Scanner(System.in);
        String mutare;
        try {
            System.out.println("Ne aflam pe pozitia START...");
            do {
                System.out.println("Introduceti urmatoarea operatiune: [L ,R, U, D, E-exit, S-salvare]");
                mutare = scaner.next();
                if (mutare.equals("D")) {
                    if (nextCellToExplore(locatie.i+1, locatie.j)) {
                        System.out.println("Este permis   < am coborat cu o casuta >");
                        locatie.i = locatie.i+1;
                        parcurgere.add((Locatie) locatie.clone());
                        deplasari.add("d");
                        if (maze[locatie.i][locatie.j] == FINISH) {
                            System.out.println("Am ajuns la final!");
                            processSolution(locatie);
                            break;
                        }
                        System.out.println("Deplasari efectuate ");
                        processCell(locatie);
                     //   System.out.println(labView3.toString2(locatie));
                        System.out.println(labView3.toString5(parcurgere,locatie));
                    } else {
                        System.out.println("Nu este permis incercati alta varianta!");
                    }
                } else if (mutare.equals("R")) {
                    if (nextCellToExplore(locatie.i, locatie.j + 1)) {
                        System.out.println("Este permis   < la dreapta cu o casuta >");
                        locatie.j = locatie.j+1;
                        parcurgere.add((Locatie) locatie.clone());
                        deplasari.add("r");
                        if (maze[locatie.i][locatie.j] == FINISH) {
                            System.out.println("Am ajuns la final!");
                            processSolution(locatie);
                            break;
                        }
                        System.out.println("Deplasari efectuate ");
                        processCell(locatie);
                       // System.out.println(labView3.toString2(locatie));
                        System.out.println(labView3.toString5(parcurgere,locatie));
                    } else {
                        System.out.println("Nu este permis incercati alta varianta!");
                    }
                } else if (mutare.equals("U")) {
                    if (nextCellToExplore(locatie.i-1, locatie.j)) {
                        System.out.println("Este permis   <am urcat cu o casuta >");
                        locatie.i = locatie.i-1;
                        parcurgere.add((Locatie) locatie.clone());
                        deplasari.add("u");
                        if (maze[locatie.i][locatie.j] == FINISH) {
                            System.out.println("Am ajuns la final!");
                            processSolution(locatie);
                            break;
                        }
                        System.out.println("Deplasari efectuate ");
                        processCell(locatie);
                       // System.out.println(labView3.toString2(locatie));
                        System.out.println(labView3.toString5(parcurgere,locatie));

                    } else {
                        System.out.println("Nu este permis incercati alta varianta!");
                    }
                } else if (mutare.equals("L")) {
                    if (nextCellToExplore(locatie.i, locatie.j - 1)) {
                        System.out.println("Este permis    <la stanga cu o casuta >");
                        locatie.j = locatie.j-1;
                        parcurgere.add((Locatie) locatie.clone());
                        deplasari.add("l");

                        if (maze[locatie.i][locatie.j] == FINISH) {
                            System.out.println("Am ajuns la final!");
                            processSolution(locatie);
                            break;
                        }
                        System.out.println("Deplasari efectuate ");
                        processCell(locatie);
                      //  System.out.println(labView3.toString2(locatie));
                        System.out.println(labView3.toString5(parcurgere,locatie));
                    } else {
                        System.out.println("Nu este permis incercati alta varianta!");
                    }
                }
                else if (mutare.equals("E")){
                    break;
                }
                else if (mutare.equals("S")){
                    try {
                        this.toFile("Serializare.txt");
                    }
                    catch(IOException e){
                        System.out.println("Esec la serializare!");
                    }
                }
            }while(1==1);
        }
        catch (IllegalAccessError e){
            // e.getStackTrace();

        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Eroare! Ati iesit din labirint");
        }
//        for (String item : deplasari) {
//            System.out.print(item+" ");
//        }
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

    public void toFile(String filename) throws IOException {
        FileOutputStream fout = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(this);
    }
    public static LabirintSolver1 fromFile(String Filename) throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream(Filename);
        ObjectInputStream oin = new ObjectInputStream(fin);
        return (LabirintSolver1)oin.readObject();
    }

    public void afterSerializationJoc() throws CloneNotSupportedException {
        System.out.println("BINE ATI REVENIT IN LABIRINT! incarcam pozitia curenta...");
        LabirintView3 labView=new LabirintView3();
        labView.setMaze(getMaze());
        System.out.println(labView.toString5(parcurgere,locatie));
        labView.setMaze(getMaze());
            Scanner scaner=new Scanner(System.in);
            String mutare;
            try {
                do {
                    System.out.println("Introduceti urmatoarea operatiune: [L ,R, U, D, E-exit, S-salvare]");
                    mutare = scaner.next();
                    if (mutare.toUpperCase().equals("D")) {
                        if (nextCellToExplore(locatie.i+1, locatie.j)) {
                            System.out.println("Este permis   < am coborat cu o casuta >");
                            locatie.i = locatie.i+1;
                            parcurgere.add((Locatie) locatie.clone());
                            deplasari.add("d");
                            if (maze[locatie.i][locatie.j] == FINISH) {
                                System.out.println("Am ajuns la final! FELICITARI!");
                                processSolution(locatie);
                                break;
                            }
                            System.out.println("Deplasari efectuate ");
                            processCell(locatie);
                            //   System.out.println(labView3.toString2(locatie));
                            System.out.println(labView.toString5(parcurgere,locatie));
                        } else {
                            System.out.println("Nu este permis incercati alta varianta!");
                        }
                    } else if (mutare.equals("R")) {
                        if (nextCellToExplore(locatie.i, locatie.j + 1)) {
                            System.out.println("Este permis   < la dreapta cu o casuta >");
                            locatie.j = locatie.j+1;
                            parcurgere.add((Locatie) locatie.clone());
                            deplasari.add("r");
                            if (maze[locatie.i][locatie.j] == FINISH) {
                                System.out.println("Am ajuns la final! FELICITARI!");
                                processSolution(locatie);
                                break;
                            }
                            System.out.println("Deplasari efectuate ");
                            processCell(locatie);
                            // System.out.println(labView3.toString2(locatie));
                            System.out.println(labView.toString5(parcurgere,locatie));
                        } else {
                            System.out.println("Nu este permis incercati alta varianta!");
                        }
                    } else if (mutare.equals("U")) {
                        if (nextCellToExplore(locatie.i-1, locatie.j)) {
                            System.out.println("Este permis   <am urcat cu o casuta >");
                            locatie.i = locatie.i-1;
                            parcurgere.add((Locatie) locatie.clone());
                            deplasari.add("u");
                            if (maze[locatie.i][locatie.j] == FINISH) {
                                System.out.println("Am ajuns la final! FELICITARI!");
                                processSolution(locatie);
                                break;
                            }
                            System.out.println("Deplasari efectuate ");
                            processCell(locatie);
                            // System.out.println(labView3.toString2(locatie));
                            System.out.println(labView.toString5(parcurgere,locatie));

                        } else {
                            System.out.println("Nu este permis incercati alta varianta!");
                        }
                    } else if (mutare.equals("L")) {
                        if (nextCellToExplore(locatie.i, locatie.j - 1)) {
                            System.out.println("Este permis    <la stanga cu o casuta >");
                            locatie.j = locatie.j-1;
                            parcurgere.add((Locatie) locatie.clone());
                            deplasari.add("l");

                            if (maze[locatie.i][locatie.j] == FINISH) {
                                System.out.println("Am ajuns la final! FELICITARI!");
                                processSolution(locatie);
                                break;
                            }
                            System.out.println("Deplasari efectuate ");
                            processCell(locatie);
                            //  System.out.println(labView3.toString2(locatie));
                            System.out.println(labView.toString5(parcurgere,locatie));
                        } else {
                            System.out.println("Nu este permis incercati alta varianta!");
                        }
                    }
                    else if (mutare.equals("E")){
                        break;
                    }
                    else if (mutare.equals("S")){
                        try {
                            this.toFile("Serializare.txt");
                        }
                        catch(IOException e){
                            System.out.println("Esec la serializare!");
                        }
                    }
                    else System.out.println("Comanda incorecta! Incercati din nou!");
                }while(1==1);
            }
            catch (IllegalAccessError e){
                // e.getStackTrace();

            }
            catch(ArrayIndexOutOfBoundsException e){
                System.out.println("Eroare! Ati iesit din labirint");
            }
    }


}
