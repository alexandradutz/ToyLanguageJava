package domain.DataStructures.Heap;

import domain.DataStructures.Dictionary.FullMapException;
import domain.DataStructures.Dictionary.IsNotKeyException;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Dutzi on 12/5/2015.
 */
public interface IHeap<V> extends Serializable {
    int add(V value) throws FullMapException;
    V getValue(Integer key) throws IsNotKeyException;
    String toString();
    void update(int address, V value) throws IsNotKeyException;


}
