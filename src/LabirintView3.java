/**
 * Created by Anisia-Ioana on 3/25/2015.
 */
public class LabirintView3 implements LabyrinthView {
    private Integer[][] maze;

    public Integer[][] getMaze() {
        return this.maze;
    }

    public void setMaze(Integer[][] maze) {
        this.maze = maze;
    }

    @Override
    public String toString(){
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
    public String toString3() {
        Locatie locatie = new Locatie(-1, -1);
        String result = "\n";

        for (int row = 0; row < maze.length; row++) {
            for (int column = 0; column < maze[0].length; column++) {
                result += "|";
                if (maze[row][column] == -1) {
                    result += "S";
                    locatie.i = row;
                    locatie.j = column;
                } else if (maze[row][column] == 0)
                    result += " ";
                else if (maze[row][column] == 2)
                    result += "F";
                else if (locatie.i == row && locatie.j == column) {
                    result += "C";
                } else
                    result += "*";
                // result += labirint[row][column] + " ";

            }
            result += "|";
            result += "\n";
        }
        return result;
    }

    public String toString2(Locatie l) {
        String result = "\n";

        for (int row = 0; row < maze.length; row++) {
            for (int column = 0; column < maze[0].length; column++) {
                result += "|";
                if (row == l.i && column == l.j)
                    result += "X";
                else if (maze[row][column] == -1) {
                    result += "S";

                } else if (maze[row][column] == 0)
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
}
