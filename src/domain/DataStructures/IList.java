package domain.DataStructures;

/**
 * Created by Dutzi on 10/11/2015.
 */
public interface IList {
    public void add(String o);
    public boolean isEmpty();
    public int size();
    public String get(int i);
    public String toStr();
}
