package domain.Stmt;

import domain.DataStructures.IDictionary;
import domain.Expression.Exp;

/**
 * Created by Dutzi on 10/11/2015.
 */
public class IfStmt implements IStmt {
    private Exp exp;
    private IStmt thenS;
    private IStmt elseS;
    private IDictionary symT;

    public IfStmt(Exp e, IStmt th, IStmt el)
    {
        this.exp = e;
        this.thenS = th;
        this.elseS = el;
    }

    @Override
    public String toStr()
    {
        return "IF (" + exp.toStr() + ") THEN (" + thenS.toStr() + ") ELSE (" + elseS.toStr() + ")";
    }

    /**
     *
     * @param symT
     */
    @Override
    public void exec(IDictionary symT) {
        if(exp.eval(symT) != 0)
        {
            thenS.exec(symT);
        }
        else
        {
            elseS.exec(symT);
        }
    }

    public Exp getExp() {
        return exp;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }

    /**
     * @description getter for then statement
     * @post IStmt
     * @return
     */
    public IStmt getThenS() {
        return thenS;
    }

    /**
     * @description setter for then statement
     * @pre IStmt
     * @param thenS
     */
    public void setThenS(IStmt thenS) {
        this.thenS = thenS;
    }

    /**
     * @description getter for else statement
     * @post IStmt
     * @return elseS
     */
    public IStmt getElseS() {
        return elseS;
    }

    /**
     * @description setter for else statement
     * @pre IStmt
     * @param elseS
     */
    public void setElseS(IStmt elseS) {
        this.elseS = elseS;
    }
}
