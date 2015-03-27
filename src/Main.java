import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException,ClassNotFoundException, IOException {
        int opt;
        System.out.println("Bine ati venit!");

        do {
            System.out.println("Introduceti ce metoda de abordare a labirintului doriti: [1]-INTERACTIVA  [2]-AUTOMATA [0]-IESIRE");
            Scanner scan=new Scanner(System.in);
            opt=scan.nextInt();
            switch (opt)
            {
                case 0: break;
                case 1:
                    LabirintSolver1 lab=new LabirintSolver1();
                    LabirintView1 labview=new LabirintView1();
                    LabirintView2 labview2=new LabirintView2();
                    labview.setMaze(lab.getMaze());
                    labview2.setMaze(lab.getMaze());
                    System.out.println(labview.toString());
                    System.out.println(labview2.toString());
                    System.out.println(labview2.toString1());
                    lab.getSolution();

                    // deserializare
                    LabirintSolver1 lab2=LabirintSolver1.fromFile("Serializare.txt");
                    lab2.afterSerializationJoc();
                    break;

                case 2:
                    //testare automata
                    LabirintSolver2 labirintSolver2=new LabirintSolver2();
                    LabirintView1 labirintView1=new LabirintView1();
                    labirintView1.setMaze(labirintSolver2.getMaze());
                    System.out.println(labirintView1.toString());
                    labirintSolver2.getSolution();
                    break;

                default:
                        System.out.println("Ati introdus o comanda gresita! Incercati din nou");
                        break;
            }

        }while(opt!=0);


      //  Labirint test1= new Labirint();
      //  System.out.println(test1.toString1());
      //  System.out.println(test1.toString2());
      //  test1.nextCellToExplore();
       // Labirint test2= new Labirint();
      //  System.out.println(test2.toString1());




    }
}
