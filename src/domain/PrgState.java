package domain;

import domain.DataStructures.Dictionary.IDictionary;
import domain.DataStructures.List.IList;
import domain.DataStructures.Stack.IStack;
import domain.Stmt.IStmt;

/**
 * Created by Dutzi on 10/14/2015.
 */
public class PrgState {
    private IStmt origPrg;
    private IStack<IStmt> exeStack;
    private IDictionary<String, Integer> symTable;
    private IList<String> out;

    public PrgState(IStack<IStmt> stk, IDictionary<String, Integer> dict, IList<String> lst, IStmt prg)
    {
        exeStack = stk;
        symTable = dict;
        out = lst;
        origPrg = prg;
        exeStack.push(origPrg);
    }

    public String toStr()
    {
        return "ExeStack = [" + exeStack.toString() + "]\n" +
                "SymbolTable = [" + symTable.toString() + "]\n" +
                "Output = [" + out.toString() + "]\n" ;
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

}
