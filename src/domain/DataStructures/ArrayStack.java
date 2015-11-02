package domain.DataStructures;

import domain.DataStructures.Interface.IStack;
import domain.Stmt.IStmt;

/**
 * Created by Dutzi on 10/6/2015.
 */
public class ArrayStack implements IStack {
    private IStmt[] elems;
    private int nrElem;

    public ArrayStack(){
        nrElem = 0;
        elems = new IStmt[20];
    }

    public void push(IStmt o){
        if(nrElem == elems.length){
            resize();
        }
        elems[nrElem++] = o;
    }

    private void resize(){
        IStmt[] tmp = new IStmt[elems.length * 2];
        for(int i = 0; i < nrElem; i++){
            tmp[i] = elems[i];
        }
        elems = tmp;
    }

    public IStmt pop(){
        if(nrElem > 0){
            return elems[--nrElem];
        }
        return null;
    }

    public boolean isEmpty(){
        return nrElem == 0;
    }

    public IStmt top(){
        if(nrElem > 0){
            return elems[nrElem - 1];
        }
        return null;
    }

    public String toStr()
    {
        String res = "";
        for(int i = 0; i < nrElem; i++)
        {
            res = elems[i].toStr() + " " + res;
        }
        return res;
    }
}
