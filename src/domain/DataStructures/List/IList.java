package domain.DataStructures.List;

import java.io.Serializable;

/**
 * Created by Dutzi on 10/11/2015.
 */
public interface IList<T> extends Serializable{
    public void add(T o) throws FullListException;
    public boolean isEmpty();
    public int size();
    public T get(int i) throws IndexOutOfBoundException;
    public String toString();
}
