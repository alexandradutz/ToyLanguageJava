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
 * Created by Dutzi on 12/5/2015.
 */
public class NewStmt implements IStmt {
    private String varname;
    private Exp expression;

    public NewStmt(String v, Exp e){
        this.varname = v;
        this.expression = e;
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, IsNotKeyException, VariableNotDefinedException, FullMapException{
        IDictionary<String, Integer> dictionary = state.getSymTable();
        IHeap<Integer> heap = state.getHeap();

        dictionary.add(varname, heap.add(expression.eval(dictionary, heap)));
        return state;
    }

    public String toString() {
        return "new( " + varname + ", " + expression.toStr() + ") ";
    }
}
