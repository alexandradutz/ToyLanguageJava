package domain.Stmt;

import domain.DataStructures.Dictionary.FullMapException;
import domain.DataStructures.Dictionary.IsNotKeyException;
import domain.DataStructures.List.FullListException;
import domain.Expression.DivisionByZeroException;
import domain.Expression.Exp;
import domain.Expression.VariableNotDefinedException;
import domain.PrgState;

/**
 * Created by Dutzi on 12/6/2015.
 */
public class HeapWriteStmt implements IStmt {
    private String varName;
    private Exp expression;

    public HeapWriteStmt(String var, Exp expr){
        this.expression = expr;
        this.varName = var;
    }

    public String getVarName() {
        return varName;
    }

    public Exp getExpression() {
        return expression;
    }

    @Override
    public PrgState execute(PrgState state) throws FullListException, DivisionByZeroException, IsNotKeyException, VariableNotDefinedException, FullMapException {
        state.getHeap().update(state.getSymTable().getValue(this.getVarName()), this.getExpression().eval(state.getSymTable(), state.getHeap()));
        return null;
    }

    @Override
    public String toString() {
        return "wH(" + varName + ", " + expression.toStr() + ")";
    }
}
