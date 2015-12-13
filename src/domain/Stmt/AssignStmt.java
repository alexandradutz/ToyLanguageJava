package domain.Stmt;

import domain.DataStructures.Dictionary.FullMapException;
import domain.DataStructures.Dictionary.IDictionary;
import domain.DataStructures.Dictionary.IsNotKeyException;
import domain.DataStructures.Heap.IHeap;
import domain.Expression.DivisionByZeroException;
import domain.Expression.Exp;
import domain.Expression.VariableNotDefinedException;
import domain.PrgState;

/**
 * Created by Dutzi on 10/11/2015.
 */
public class AssignStmt implements IStmt {
    private String id;
    private Exp exp;
    private IDictionary symT;

    public AssignStmt(String i, Exp e)
    {
        id = i;
        exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, IsNotKeyException, VariableNotDefinedException, FullMapException {
        IDictionary<String, Integer> symTbl = state.getSymTable();
        IHeap<Integer> heap =  state.getHeap();
        int val = exp.eval(symTbl, heap);
        symTbl.add(id, val);
        return state;
    }

    @Override
    public String toString()
    { return id + "=" + exp.toStr() ; }

    public Exp getExp() {
        return exp;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
