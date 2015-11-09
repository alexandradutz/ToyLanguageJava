package domain.Stmt;

import domain.DataStructures.Dictionary.IDictionary;
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

    public Exp getVar() {
        return var;
    }

    public void setVar(Exp var) {
        this.var = var;
    }

}
