package domain.DataStructures.Stack;

/**
 * Created by Dutzi on 10/6/2015.
 */

public interface IStack<T>{
    public void push(T o);
    public T pop() throws EmptyStackException;
    public boolean isEmpty();
    public T top() throws EmptyStackException;
    public String toString();
}
