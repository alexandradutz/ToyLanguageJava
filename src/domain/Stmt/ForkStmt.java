package domain.Stmt;

import domain.DataStructures.Dictionary.FullMapException;
import domain.DataStructures.Dictionary.IsNotKeyException;
import domain.DataStructures.Dictionary.LibDictionary;
import domain.DataStructures.Heap.IHeap;
import domain.DataStructures.List.FullListException;
import domain.DataStructures.List.IList;
import domain.DataStructures.Stack.LibStack;
import domain.Expression.DivisionByZeroException;
import domain.Expression.VariableNotDefinedException;
import domain.PrgState;

/**
 * Created by Dutzi on 12/13/2015.
 */
public class ForkStmt implements IStmt {
    private IStmt stmt;

    public ForkStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws FullListException, DivisionByZeroException, IsNotKeyException, VariableNotDefinedException, FullMapException {
        LibStack<IStmt> stk2 = new LibStack<>();
        //stk2.push(stmt);
        LibDictionary symT = (LibDictionary) state.getSymTable();
        LibDictionary symT2 = new LibDictionary<>(symT);
        IList<String> out = state.getOut();
        IHeap<Integer> heap = state.getHeap();

        return new PrgState(state.getId() * 10, stk2, symT2, out, heap, stmt );

    }

    @Override
    public String toString() {
        return "fork(" + stmt.toString() + ")";
    }
}
