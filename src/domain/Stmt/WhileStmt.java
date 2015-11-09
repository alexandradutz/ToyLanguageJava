package domain.Stmt;

import domain.DataStructures.Interface.IDictionary;
import domain.Expression.Exp;

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
    public void exec(IDictionary symT) {
        return;
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
