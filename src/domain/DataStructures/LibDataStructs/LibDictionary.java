package domain.DataStructures.LibDataStructs;

import domain.DataStructures.Interface.IDictionary;
import exception.MyException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Dutzi on 11/8/2015.
 */
public class LibDictionary <K, V> implements IDictionary<K, V> {
    private HashMap<K, V> dictionary;

    public LibDictionary(){
        this.dictionary = new HashMap<>();
    }

    @Override
    public boolean isEmpty() {
        return dictionary.isEmpty();
    }

    @Override
    public void add(K key, V value) {
        dictionary.put(key, value);
    }

    @Override
    public V getValue(K key) throws MyException {
        return dictionary.get(key);
    }

    @Override
    public int size() {
        return dictionary.size();
    }

    @Override
    public boolean isKey(K key) {
        if(dictionary.get(key) != null){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return dictionary.toString();
    }

    @Override
    public void modify(K key, V value) {
        dictionary.put(key, value);
    }

    public ArrayList<K> keys() {
        ArrayList<K> arList = new ArrayList<K>();
        for(K key : dictionary.keySet()){
            arList.add(arList.size(), key);
        }
        return arList;
    }

}
