package domain.Stmt;

import domain.DataStructures.Dictionary.FullMapException;
import domain.DataStructures.Dictionary.IDictionary;
import domain.DataStructures.Dictionary.IsNotKeyException;
import domain.Expression.DivisionByZeroException;
import domain.Expression.Exp;
import domain.Expression.VariableNotDefinedException;
import domain.PrgState;

/**
 * Created by Dutzi on 11/3/2015.
 */
public class WhileStmt implements IStmt {
    private Exp expr;
    private IStmt stmt;

    public WhileStmt(Exp e, IStmt st){
        this.expr = e;
        this.stmt = st;
    }

    @Override
    public String toString() {
        return "WHILE(" + expr.toStr() + ") {" + stmt.toString() + "}";
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, IsNotKeyException, VariableNotDefinedException, FullMapException {
        if (getExpr().eval(state.getSymTable(), state.getHeap()) != 0) {
            state.getExeStack().push(this);
            state.getExeStack().push(this.getStmt());
        }
        return null;
    }

    public Exp getExpr() {
        return expr;
    }

    public void setExpr(Exp expr) {
        this.expr = expr;
    }

    public IStmt getStmt() {
        return stmt;
    }

    public void setStmt(IStmt stmt) {
        this.stmt = stmt;
    }
}
