package domain.Expression;

import domain.DataStructures.Dictionary.IDictionary;
import domain.DataStructures.Dictionary.IsNotKeyException;
import domain.DataStructures.Heap.IHeap;

/**
 * Created by Dutzi on 11/8/2015.
 */
public class LogicalExp extends Exp {
    private String opt;
    private Exp exp1;
    private Exp exp2;
    /*
    * Options:
    *  &&
    *  ||
    *  !
    */

    public LogicalExp(Exp exp1, Exp exp2, String opt){
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.opt = opt;
    }

    public LogicalExp(Exp exp1, String opt){
        this.exp1 = exp1;
        this.opt = opt;
    }

    @Override
    public int eval(IDictionary<String, Integer> tbl, IHeap<Integer> heap)  throws IsNotKeyException, DivisionByZeroException, VariableNotDefinedException {
        switch (opt){
            case "&&": if (exp1.eval(tbl, heap)!=0 && exp2.eval(tbl, heap)!=0){return 1;} return 0;
            case "||": if (exp1.eval(tbl, heap)!=0 || exp2.eval(tbl, heap)!=0){return 1;} return 0;
            case "!": if (exp1.eval(tbl, heap) == 0){return 1;} return 0;
            default: return eval(tbl, heap);
        }
    }

    @Override
    public String toStr() {
        switch (opt){
            case "&&": return "(" + exp1.toStr() + " && " + exp2.toStr() + ")";
            case "||": return "(" + exp1.toStr() + " || " + exp2.toStr() + ")";
            case "!": return "!(" + exp1.toStr() + ")";
            default: return toStr();
        }
    }
}
