package domain.Expression;

import domain.DataStructures.Dictionary.IDictionary;
import domain.DataStructures.Dictionary.IsNotKeyException;
import domain.DataStructures.Heap.IHeap;

/**
 * Created by Dutzi on 12/6/2015.
 */
public class HeapReadExp extends Exp {
    private String variableName;

    public HeapReadExp(String var){
        this.variableName = var;
    }
    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public int eval(IDictionary<String, Integer> tbl, IHeap<Integer> heap) throws IsNotKeyException, DivisionByZeroException, VariableNotDefinedException {
        return heap.getValue(tbl.getValue(this.getVariableName()));
    }

    @Override
    public String toStr() {
        return "rH(" + variableName + ")";
    }
}
