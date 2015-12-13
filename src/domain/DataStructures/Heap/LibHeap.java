package domain.DataStructures.Heap;


import domain.DataStructures.Dictionary.FullMapException;
import domain.DataStructures.Dictionary.IsNotKeyException;
import java.util.HashMap;

/**
 * Created by Dutzi on 12/5/2015.
 */
public class LibHeap<V> implements IHeap<V> {
    private HashMap<Integer, V> heap;
    private Integer firstFree;

    public LibHeap(){
        this.heap = new HashMap<>();
        this.firstFree = 1;
    }

    @Override
    public int add(V value) throws FullMapException {
        heap.put(this.firstFree, value);
        return this.firstFree++;
    }

    @Override
    public V getValue(Integer address) throws IsNotKeyException {
        if(this.heap.get(address) == null) throw new IsNotKeyException();
        return this.heap.get(address);
    }


    @Override
    public String toString() {
        String res = "";
        for (Integer e : this.heap.keySet())
        {
            res = e.toString() + " -> " + this.heap.get(e).toString() + "\n" + res;
        }
        return res;
    }



    @Override
    public void update(int address, V value) throws IsNotKeyException {
        if(this.heap.get(address) == null) throw new IsNotKeyException();
        this.heap.put(address, value);
    }
}
