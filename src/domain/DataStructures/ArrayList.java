package domain.DataStructures;

/**
 * Created by Dutzi on 10/11/2015.
 */
public class ArrayList implements IList {
    private String[] elems;
    private int nrElem;

    public ArrayList(){
        nrElem = 0;
        elems = new String[20];
    }

    @Override
    public void add(String o) {
        if(nrElem == elems.length){
            resize();
        }
        elems[nrElem++] = o;
    }

    public void resize(){
        String tmp[] = new String[elems.length * 2];
        for(int i = 0; i < nrElem; i++){
            tmp[i] = elems[i];
        }
        elems = tmp;
    }

    @Override
    public boolean isEmpty() {
        return nrElem == 0;
    }

    @Override
    public int size() {
        return nrElem;
    }

    @Override
    public String get(int i) {
        if (i < nrElem){
            return elems[i];
        }
        return null;
    }

    @Override
    public String toStr() {
        String res = "";
        for(int i = 0; i < nrElem; i++)
        {
            res = res + " " + elems[i];
        }
        return res;
    }

}
