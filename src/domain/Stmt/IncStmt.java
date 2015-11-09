package domain.Stmt;

import domain.DataStructures.Interface.IDictionary;
import domain.Expression.Exp;

/**
 * Created by Dutzi on 11/2/2015.
 */
public class IncStmt implements IStmt {
    private Exp var;
    private IDictionary symT;

    public IncStmt(Exp number)
    {
        this.var = number;
    }

    @Override
    public String toString() {
        return "inc(" + var.toStr() + ")";
    }

    @Override
    public void exec(IDictionary symT) {
        if(symT.isKey(var.toStr()))
        {
            symT.modify(var.toStr(), var.eval(symT) + 1);
        }

    }

    public Exp getVar() {
        return var;
    }

    public void setVar(Exp var) {
        this.var = var;
    }

}
