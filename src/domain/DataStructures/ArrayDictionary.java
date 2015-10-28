package domain.DataStructures;

import exception.MyException;

/**
 * Created by Dutzi on 10/6/2015.
 */

public class ArrayDictionary implements IDictionary{
    private int[] elems;
    private String[] keys;
    private int nrElem;

    public ArrayDictionary(){
        nrElem = 0;
        keys = new String[20];
        elems = new int[20];
    }

    public boolean isEmpty(){
        return nrElem == 0;
    }

    public void add(String key, int value){
        if( nrElem == elems.length){
            resize();
        }
        elems[nrElem] = value;
        keys[nrElem++] = key;
    }

    public void resize(){
        int tmp[] = new int[elems.length * 2];
        String k[] = new String[elems.length * 2];

        for(int i = 0; i < nrElem; i++){
            tmp[i] = elems[i];
            k[i] = keys[i];
        }
        elems = tmp;
        keys = k;
    }

    public int getValue(String key) throws MyException {
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
            if(keys[i] == key){ break; }
        }
        return i < nrElem;
    }

    @Override
    public String toStr() {
        String res = "";
        for(int i = 0; i < nrElem; i++)
        {
            res = res + ", " + keys[i] + ":" + elems[i];
        }
        return res;
    }

}

