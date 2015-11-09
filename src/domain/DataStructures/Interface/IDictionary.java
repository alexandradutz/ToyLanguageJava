package domain.DataStructures.Interface;

import exception.MyException;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Dutzi on 10/6/2015.
 */
public interface IDictionary<K, V>{
    public boolean isEmpty();
    public void add(K key, V value);
    public V getValue(K key) throws MyException;
    public int size();
    public boolean isKey(K key);
    public String toString();
    public void modify(K key, V value);
    public ArrayList<K> keys();
}
