package domain.Stmt;

import domain.DataStructures.Interface.IDictionary;
import domain.DataStructures.Interface.IList;
import domain.Expression.Exp;

/**
 * Created by Dutzi on 10/11/2015.
 */
public class PrintStmt implements IStmt {
    private Exp exp;
    private IList out;

    public PrintStmt(Exp e)
    {
        this.exp = e;
    }

//getter & setter
    public Exp getExp() {
        return exp;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString()
    {
        return "print(" + exp.toStr() + ")";
    }

    @Override
    public void exec(IDictionary symT) {
        out.add(exp.toStr());
    }
}
