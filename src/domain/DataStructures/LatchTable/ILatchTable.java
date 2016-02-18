package domain.DataStructures.LatchTable;


import domain.DataStructures.Dictionary.FullMapException;
import domain.DataStructures.Dictionary.IsNotKeyException;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Dutzi on 1/22/2016.
 */
public interface ILatchTable<V> extends Serializable{
    int add(V value) throws FullMapException;
    V getValue(Integer key) throws IsNotKeyException;
    String toString();
    void update(int address, V value) throws IsNotKeyException;
    public Integer getFirstFree();
}
