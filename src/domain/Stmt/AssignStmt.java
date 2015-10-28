package domain.Stmt;

import domain.DataStructures.IDictionary;
import domain.Expression.Exp;

/**
 * Created by Dutzi on 10/11/2015.
 */
public class AssignStmt implements IStmt {
    private String id;
    private Exp exp;
    private IDictionary symT;

    /**
     *
     * @param i
     * @param e
     */
    public AssignStmt(String i, Exp e)
    {
        id = i;
        exp = e;
    }


    /**
     *
     * @return
     */
    @Override
    public String toStr()
    {
        return id + "=" + exp.toStr();
    }

    /**
     *
     * @param symT
     */
    @Override
    public void exec(IDictionary symT) {
        symT.add(id, exp.eval(symT));
    }

    /**
     *
     * @return
     */
    public Exp getExp() {
        return exp;
    }

    /**
     *
     * @param exp
     */
    public void setExp(Exp exp) {
        this.exp = exp;
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
