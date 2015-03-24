/**
 * Created by Anisia-Ioana on 3/20/2015.
 */
public interface LabyrinthModel {
    public Integer getRowCount();
    public Integer getColumnCount();
    public Boolean isFreeAt(int i,int j);
    public Boolean isWallAt(int i, int j);
    public Locatie getStartCell();
    public Locatie getFinishCell();
  //  public void constructLabirint();
  //  public Integer[][] getMatrixFromFile( );


}
