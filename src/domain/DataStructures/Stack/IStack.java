package domain.DataStructures.Stack;

import java.io.Serializable;

/**
 * Created by Dutzi on 10/6/2015.
 */

public interface IStack<T> extends Serializable{
    public void push(T o);
    public T pop() throws EmptyStackException;
    public boolean isEmpty();
    public T top() throws EmptyStackException;
    String toString();
}
