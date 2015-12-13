package domain.Stmt;

import domain.DataStructures.Dictionary.FullMapException;
import domain.DataStructures.Dictionary.IDictionary;
import domain.DataStructures.Dictionary.IsNotKeyException;
import domain.Expression.DivisionByZeroException;
import domain.Expression.Exp;
import domain.Expression.VariableNotDefinedException;
import domain.PrgState;

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
    public PrgState execute(PrgState state) throws DivisionByZeroException, IsNotKeyException, VariableNotDefinedException, FullMapException {
        IDictionary<String, Integer> symTable = state.getSymTable();
        if (symTable.isKey(var.toStr())) {
            symTable.add(var.toStr(), var.eval(symTable, state.getHeap()) + 1);

        }
        return state;
    }

    public Exp getVar() {
        return var;
    }

    public void setVar(Exp var) {
        this.var = var;
    }

}
