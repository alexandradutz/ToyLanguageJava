package domain.Stmt;

import domain.DataStructures.Dictionary.FullMapException;
import domain.DataStructures.Dictionary.IsNotKeyException;
import domain.DataStructures.List.FullListException;
import domain.Expression.DivisionByZeroException;
import domain.Expression.Exp;
import domain.Expression.VariableNotDefinedException;
import domain.PrgState;

/**
 * Created by Dutzi on 1/22/2016.
 */
public class newLatchStmt implements IStmt {
    private String varname;
    private Exp number;

    public newLatchStmt(String v, Exp e){
        this.varname = v;
        this.number = e;
    }

    @Override
    public PrgState execute(PrgState state) throws FullListException, DivisionByZeroException, IsNotKeyException, VariableNotDefinedException, FullMapException {
        Object lock = new Object();
        synchronized (lock){
            Integer firstFree = state.getLatchTable().getFirstFree();
            state.getSymTable().add(varname, firstFree);
            Integer eval = number.eval(state.getSymTable(), state.getHeap());
            state.getLatchTable().add(eval);
        }
        return state;
    }

    @Override
    public String toString() {
        return "newLatch(" + varname + ", " + number.toStr() + ")";
    }
}
