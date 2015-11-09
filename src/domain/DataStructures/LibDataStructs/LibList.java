package domain.DataStructures.LibDataStructs;

import domain.DataStructures.Interface.IList;
import java.util.Vector;

/**
 * Created by Dutzi on 11/8/2015.
 */
public class LibList<T> implements IList<T> {
    private Vector<T> list;

    public LibList(){
        this.list = new Vector<>(20);
    }

    @Override
    public void add(T o) {
        list.addElement(o);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public T get(int i) {
        return list.get(i);
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
