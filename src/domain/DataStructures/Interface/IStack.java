package domain.DataStructures.Interface;

/**
 * Created by Dutzi on 10/6/2015.
 */

public interface IStack<T>{
    public void push(T o);
    public T pop();
    public boolean isEmpty();
    public T top();
    public String toString();
}
