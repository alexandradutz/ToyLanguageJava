package domain.Stmt;


import domain.DataStructures.Interface.IDictionary;
import domain.Expression.Exp;

public class ForStmt implements IStmt {
    private Exp exp1;
    private Exp exp2;
    private Exp exp3;
    private IStmt stmt;

    public ForStmt(Exp e1, Exp e2, Exp e3, IStmt st)
    {
        this.exp1 = e1;
        this.exp2 = e2;
        this.exp3 = e3;
        this.stmt = st;
    }

    public Exp getExp1() {
        return exp1;
    }

    public void setExp1(Exp exp1) {
        this.exp1 = exp1;
    }

    public Exp getExp2() {
        return exp2;
    }

    public void setExp2(Exp exp2) {
        this.exp2 = exp2;
    }

    public Exp getExp3() {
        return exp3;
    }

    public void setExp3(Exp exp3) {
        this.exp3 = exp3;
    }

    public IStmt getStmt() {
        return stmt;
    }

    public void setStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public void exec(IDictionary symT) {
        return;
    }

    @Override
    public String toStr() {
        return "FOR( i=" + exp1.toStr() + "; i<" + exp2.toStr() + "; i+=" + exp3.toStr() + ") {" +
                stmt.toStr() + "}";
    }
}
