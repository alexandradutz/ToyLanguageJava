package domain.Stmt;

import domain.DataStructures.Dictionary.FullMapException;
import domain.DataStructures.Dictionary.IDictionary;
import domain.DataStructures.Dictionary.IsNotKeyException;
import domain.DataStructures.List.FullListException;
import domain.DataStructures.List.IList;
import domain.Expression.DivisionByZeroException;
import domain.Expression.Exp;
import domain.Expression.VariableNotDefinedException;
import domain.PrgState;

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
    public PrgState execute(PrgState state) throws FullListException, DivisionByZeroException, IsNotKeyException, VariableNotDefinedException, FullMapException {
        state.getOut().add(Integer.toString(this.getExp().eval(state.getSymTable(), state.getHeap())));
        return state;
    }
}
