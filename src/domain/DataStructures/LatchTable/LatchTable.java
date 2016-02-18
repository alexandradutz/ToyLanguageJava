package domain.DataStructures.LatchTable;

import domain.DataStructures.Dictionary.FullMapException;
import domain.DataStructures.Dictionary.IsNotKeyException;
import java.util.HashMap;


/**
 * Created by Dutzi on 1/22/2016.
 */
public class LatchTable<V> implements ILatchTable<V> {
    private HashMap<Integer, V> latchTable;
    private Integer firstFree;


    public Integer getFirstFree() {
        return firstFree;
    }

    public void setFirstFree(Integer firstFree) {
        this.firstFree = firstFree;
    }

    public LatchTable(){
        this.latchTable = new HashMap<>();
        this.firstFree = 1;

    }

    @Override
    public int add(V value) throws FullMapException {
        latchTable.put(this.firstFree, value);
        return this.firstFree++;
    }

    @Override
    public V getValue(Integer address) throws IsNotKeyException {
        if(this.latchTable.get(address) == null) throw new IsNotKeyException();
        return this.latchTable.get(address);
    }




    @Override
    public void update(int address, V value) throws IsNotKeyException {
        if(this.latchTable.get(address) == null) throw new IsNotKeyException();
        this.latchTable.put(address, value);
    }

    @Override
    public String toString() {
        String res = "";
        for (Integer e : this.latchTable.keySet())
        {
            res = e.toString() + " -> " + this.latchTable.get(e).toString() + "\n" + res;
        }
        return res;
    }
}
