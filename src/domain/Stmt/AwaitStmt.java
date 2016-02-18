package domain.Stmt;
import domain.DataStructures.Dictionary.FullMapException;
import domain.DataStructures.Dictionary.IsNotKeyException;
import domain.DataStructures.List.FullListException;
import domain.Expression.DivisionByZeroException;
import domain.Expression.VariableNotDefinedException;
import domain.PrgState;
import domain.Stmt.IStmt;

/**
 * Created by Dutzi on 1/22/2016.
 */
public class AwaitStmt implements IStmt {
    private String varname;

    public AwaitStmt(String v){
        this.varname = v;
    }
    @Override
    public PrgState execute(PrgState state) throws FullListException, DivisionByZeroException, IsNotKeyException, VariableNotDefinedException, FullMapException {
        if(state.getSymTable().isKey(varname)){
            Integer val = state.getLatchTable().getValue(state.getSymTable().getValue(varname));
            if(val != 0){
                state.getExeStack().push(this);
            }
        }
        else{
            throw new IsNotKeyException();
        }
        return state;
    }

    @Override
    public String toString() {
        return "await(" + varname + ")";
    }
}