public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
      //  Labirint test1= new Labirint();
      //  System.out.println(test1.toString1());
      //  System.out.println(test1.toString2());
      //  test1.nextCellToExplore();
       // Labirint test2= new Labirint();
      //  System.out.println(test2.toString1());
        LabirintSolver1 lab=new LabirintSolver1();
        LabirintView1 labview=new LabirintView1();
        labview.setMaze(lab.getMaze());
        System.out.println(labview.toString());
        lab.getSolution();

    }
}
