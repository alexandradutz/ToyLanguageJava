package domain.Expression;

import domain.DataStructures.Dictionary.IDictionary;
import domain.DataStructures.Dictionary.IsNotKeyException;
import domain.DataStructures.Heap.IHeap;

/**
 * Created by Dutzi on 10/13/2015.
 */
public class VarExp extends Exp {
    private String id;

    public VarExp(String id) {
        this.id = id;
    }


    @Override
    public int eval(IDictionary<String, Integer> tbl, IHeap<Integer> heap)  throws IsNotKeyException, VariableNotDefinedException {
        if(tbl.isKey(id))
                return (int) tbl.getValue(id);
        throw new VariableNotDefinedException();
    }

    @Override
    public String toStr() {
        return id + "";
    }
}
