package domain.DataStructures.Stack;

import java.util.Stack;

/**
 * Created by Dutzi on 11/7/2015.
 */
public class LibStack<T> implements IStack<T>{
    private Stack<T> stack;

    public LibStack(){
        this.stack = new Stack<>();
    }

    @Override
    public void push(T o){

        stack.push(o);
    }

    @Override
    public T pop() throws EmptyStackException {
        if (this.stack.isEmpty()) throw new EmptyStackException();
        return stack.pop();
    }

    @Override
    public boolean isEmpty() {
        return stack.empty();
    }

    @Override
    public T top()throws EmptyStackException {
        if (this.stack.isEmpty()) throw new EmptyStackException();
        return stack.peek();
    }

    @Override
    public String toString() {
        //return toString();
        String res = "";
        for (T elem : stack) {
            res = elem.toString() + res;
        }
        return res;
    }
}
