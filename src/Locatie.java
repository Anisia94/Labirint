import java.io.Serializable;

/**
 * Created by Anisia-Ioana on 3/24/2015.
 */

public class Locatie implements Cloneable,Serializable {
    public int i;
    public int j;
    public Locatie(int ii, int jj){
        this.i=ii;
        this.j=jj;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

