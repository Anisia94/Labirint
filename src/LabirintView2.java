/**
 * Created by Anisia-Ioana on 3/24/2015.
 */
public class LabirintView2 implements LabyrinthView {
    private Integer[][] maze;

    public Integer[][] getMaze(){
        return this.maze;
    }

    public void setMaze(Integer[][] maze) {
        this.maze = maze;
    }

    @Override
    public String toString() {
        String result="\n";
        for (int row = 0; row < maze.length; row++) {
            for (int column = 0; column < maze[0].length; column++) {
                result+=maze[row][column]+" ";
            }
            result+="\n";
        }
        return result;
    }
}
