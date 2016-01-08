package domain;

import controller.StatementExecutionException;
import domain.DataStructures.Dictionary.FullMapException;
import domain.DataStructures.Dictionary.IDictionary;
import domain.DataStructures.Dictionary.IsNotKeyException;
import domain.DataStructures.Heap.IHeap;
import domain.DataStructures.List.FullListException;
import domain.DataStructures.List.IList;
import domain.DataStructures.Stack.EmptyStackException;
import domain.DataStructures.Stack.IStack;
import domain.Expression.DivisionByZeroException;
import domain.Expression.VariableNotDefinedException;
import domain.Stmt.IStmt;
import repository.EmptyRepository;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Dutzi on 10/14/2015.
 */
public class PrgState implements Serializable {
    private int id;
    private IStmt origPrg;
    private IStack<IStmt> exeStack;
    private IDictionary<String, Integer> symTable;
    private IList<String> out;
    private IHeap<Integer> heap;

    public PrgState(int pid,IStack<IStmt> stk, IDictionary<String, Integer> dict, IList<String> lst, IHeap<Integer> h, IStmt prg)
    {
        this.id = pid;
        this.exeStack = stk;
        this.symTable = dict;
        this.out = lst;
        this.heap = h;
        this.origPrg = prg;
        this.exeStack.push(origPrg);
    }

    public String toStr()
    {
        return  "==================================\n" +
                "Id: " + id +
                "\nExeStack:\n" + exeStack.toString() +
                "\nSymbolTable:\n" + symTable.toString() +
                "\nHeap:\n" + heap.toString() +
                "\nOutput:\n" + out.toString()
                ;
    }

    public PrgState oneStep() throws EmptyStackException, IOException, StatementExecutionException, DivisionByZeroException, VariableNotDefinedException, IsNotKeyException, FullMapException, FullListException
    {
        IStack<IStmt> stk = this.getExeStack();
        if(exeStack.isEmpty()) throw new StatementExecutionException();

        IStmt crtStmt = stk.pop();
        return crtStmt.execute(this);
    }

    public int getId(){return this.id;}

    public String toString(){
        return this.toStr();
    }

    public IStmt getOrigPrg() {
        return origPrg;
    }


    public IStack<IStmt> getExeStack() {
        return exeStack;
    }


    public IDictionary<String, Integer> getSymTable() {
        return symTable;
    }


    public IList<String> getOut() {
        return out;
    }

    public IHeap<Integer> getHeap() {return this.heap; }

    public boolean isNotCompleted(){return !exeStack.isEmpty();}

}
