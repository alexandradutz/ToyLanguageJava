package domain.DataStructures;

import domain.DataStructures.Interface.IDictionary;
import exception.MyException;

import java.util.*;
import java.util.ArrayList;

/**
 * Created by Dutzi on 10/6/2015.
 */

public class ArrayDictionary implements IDictionary<String, Integer>{
    private Integer[] elems;
    private String[] keys;
    private int nrElem;



    public ArrayDictionary(){
        nrElem = 0;
        keys = new String[20];
        elems = new Integer[20];
    }

    public boolean isEmpty(){
        return nrElem == 0;
    }

    public void add(String key, Integer value){
        if( nrElem == elems.length){
            resize();
        }
        for(int i = 0; i < nrElem; i++){
            if(keys[i].equals(key)){
                elems[i] = value;
                return;
            }
        }
        elems[nrElem] = value;
        keys[nrElem++] = key;
    }

    public void resize(){
        Integer tmp[] = new Integer[elems.length * 2];
        String k[] = new String[elems.length * 2];

        for(int i = 0; i < nrElem; i++){
            tmp[i] = elems[i];
            k[i] = keys[i];
        }
        elems = tmp;
        keys = k;
    }

    public Integer getValue(String key) throws MyException {
        int i;
        for(i = 0; i < nrElem; i++){
            if(keys[i].equals(key)){
                return this.elems[i];
            }
        }
        throw new MyException("Key not found");
    }

    public int size(){
        return nrElem;
    }

    public boolean isKey(String key){
        int i;
        for(i = 0; i < nrElem; i++){
            if(keys[i].equals(key)){return true; }
        }
        return i < nrElem;
    }

    @Override
    public String toString() {
        String res = "";
        for(int i = 0; i < nrElem; i++)
        {
            res = res + ", " + keys[i] + ":" + elems[i];
        }
        return res;
    }

    @Override
    public void modify(String key, Integer value) {
        int i;
        for(i = 0; i < nrElem; i++)
        {
            if(keys[i].equals(key))
            {
                elems[i] = value;
                break;
            }
        }

    }

    @Override
    public ArrayList<String> keys() {
        return null;
    }
}

