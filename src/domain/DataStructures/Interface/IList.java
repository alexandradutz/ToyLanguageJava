package domain.DataStructures.Interface;

/**
 * Created by Dutzi on 10/11/2015.
 */
public interface IList<T> {
    public void add(T o);
    public boolean isEmpty();
    public int size();
    public T get(int i);
    public String toString();
}
