package domain.DataStructures;

import exception.MyException;

/**
 * Created by Dutzi on 10/6/2015.
 */
public interface IDictionary {
    public boolean isEmpty();
    public void add(String key, int value);
    public int getValue(String key) throws MyException;
    public int size();
    public boolean isKey(String key);
    public String toStr();
}
