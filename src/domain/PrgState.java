package domain;

import domain.DataStructures.Interface.IDictionary;
import domain.DataStructures.Interface.IList;
import domain.DataStructures.Interface.IStack;
import domain.Stmt.IStmt;

/**
 * Created by Dutzi on 10/14/2015.
 */
public class PrgState {
    private IStmt origPrg;
    private IStack exeStack;
    private IDictionary symTable;
    private IList out;

    public PrgState(IStack stk, IDictionary dict, IList lst)
    {
        exeStack = stk;
        symTable = dict;
        out = lst;
    }

    public String toStr()
    {
        return "ExeStack = [" + exeStack.toStr() + "]\n" +
                "SymbolTable = [" + symTable.toStr() + "]\n" +
                "Output = [" + out.toStr() + "]\n" ;
    }

    public IStmt getOrigPrg() {
        return origPrg;
    }

    public void setOrigPrg(IStmt origPrg) {
        this.origPrg = origPrg;
    }

    public IStack getExeStack() {
        return exeStack;
    }

    public void setExeStack(IStack exeStack) {
        this.exeStack = exeStack;
    }

    public IDictionary getSymTable() {
        return symTable;
    }

    public void setSymTable(IDictionary symTable) {
        this.symTable = symTable;
    }

    public IList getOut() {
        return out;
    }

    public void setOut(IList out) {
        this.out = out;
    }
}
