package domain.DataStructures.Dictionary;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Created by Dutzi on 10/6/2015.
 */
public interface IDictionary<K, V> extends Serializable{
    public boolean isEmpty();
    public void add(K key, V value) throws FullMapException;
    public V getValue(K key) throws IsNotKeyException;
    public int size();
    public boolean isKey(K key);
    public String toString();
    public ArrayList<K> keys();
}
