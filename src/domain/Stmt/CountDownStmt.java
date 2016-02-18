package domain.Stmt;

import domain.DataStructures.Dictionary.FullMapException;
import domain.DataStructures.Dictionary.IsNotKeyException;
import domain.DataStructures.List.FullListException;
import domain.Expression.DivisionByZeroException;
import domain.Expression.VariableNotDefinedException;
import domain.PrgState;

/**
 * Created by Dutzi on 1/22/2016.
 */
public class CountDownStmt implements IStmt {
    private String varname;

    public CountDownStmt(String v){
        this.varname = v;
    }

    @Override
    public String toString() {
        return "countDown(" + varname + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws FullListException, DivisionByZeroException, IsNotKeyException, VariableNotDefinedException, FullMapException {
        Object lock = new Object();
        if(state.getSymTable().isKey(varname)){
            synchronized (lock) {
                Integer val = state.getLatchTable().getValue(state.getSymTable().getValue(varname)); //throw excp if not index
                if (val > 0) {
                    Integer newVal = val - 1;
                    state.getLatchTable().update(state.getSymTable().getValue(varname), newVal);
                }
            }
        }
        else{
            throw new IsNotKeyException();
        }
        return state;
    }

}
