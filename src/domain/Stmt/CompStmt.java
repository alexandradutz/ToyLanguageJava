package domain.Stmt;

import domain.DataStructures.Dictionary.FullMapException;
import domain.DataStructures.Dictionary.IsNotKeyException;
import domain.Expression.DivisionByZeroException;
import domain.Expression.VariableNotDefinedException;
import domain.PrgState;


/**
 * Created by Dutzi on 10/11/2015.
 */
public class CompStmt implements IStmt {
    private IStmt first;
    private IStmt second;

    public CompStmt(IStmt f, IStmt s)
    {
        first = f;
        second = s;
    }

    @Override
    public String toString() {
        return first.toString() + ";" + second.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, IsNotKeyException, VariableNotDefinedException, FullMapException {
        state.getExeStack().push(second);
        state.getExeStack().push(first);
        return state;
    }

    public IStmt getSecond() {
        return second;
    }

    public void setSecond(IStmt second) {
        this.second = second;
    }

    public IStmt getFirst() {

        return first;
    }

    public void setFirst(IStmt first) {
        this.first = first;
    }
}
