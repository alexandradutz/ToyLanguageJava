package domain.DataStructures.List;

import java.util.Vector;

/**
 * Created by Dutzi on 11/8/2015.
 */
public class LibList<T> implements IList<T> {
    private Vector<T> list;

    /**
     *Constructs an empty list with an initial capacity of ten.
     */
    public LibList(){
        this.list = new Vector<>();
    }

    @Override
    public void add(T o) throws FullListException{
        if(this.list.size() == 10) throw new FullListException();
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
    public T get(int i) throws IndexOutOfBoundException {
        if((i >= 0) && (i < this.list.size()))
            return list.get(i);
        throw new IndexOutOfBoundException();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
