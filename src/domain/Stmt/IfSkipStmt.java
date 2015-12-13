package domain.Stmt;

import domain.DataStructures.Dictionary.FullMapException;
import domain.DataStructures.Dictionary.IDictionary;
import domain.DataStructures.Dictionary.IsNotKeyException;
import domain.Expression.DivisionByZeroException;
import domain.Expression.Exp;
import domain.Expression.VariableNotDefinedException;
import domain.PrgState;

/**
 * Created by Dutzi on 11/10/2015.
 */
public class IfSkipStmt implements IStmt {
    private Exp exp;
    private IStmt thenS;
    private IStmt elseS;
    private IDictionary symT;

    public IfSkipStmt(Exp e, IStmt th)
    {
        this.exp = e;
        this.thenS = th;
        this.elseS = null;
    }

    @Override
    public String toString()
    {
        return "IF (" + exp.toStr() + ") THEN (" + thenS.toString() + ") ELSE (" + elseS.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, IsNotKeyException, VariableNotDefinedException, FullMapException {
        return state;
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
