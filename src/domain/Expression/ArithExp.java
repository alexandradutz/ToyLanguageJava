package domain.Expression;

import domain.DataStructures.Dictionary.IDictionary;
import domain.DataStructures.Dictionary.IsNotKeyException;
import domain.DataStructures.Heap.IHeap;

/**
 * Created by Dutzi on 10/13/2015.
 */
public class ArithExp extends Exp {
    private Exp exp1;
    private Exp exp2;
    String opt;
    /*
    * Options:
    *  +
    *  -
    *  *
    *  /
    */

    //constructor
    public ArithExp(Exp e1, Exp e2, String o)
    {
        exp1 = e1;
        exp2 = e2;
        opt = o;
    }

    @Override
    public int eval(IDictionary<String, Integer> tbl, IHeap<Integer> heap)  throws IsNotKeyException, DivisionByZeroException, VariableNotDefinedException {
        switch (opt){
            case "+": return (exp1.eval(tbl, heap) + exp2.eval(tbl, heap));
            case "-": return (exp1.eval(tbl, heap) - exp2.eval(tbl, heap));
            case "*": return (exp1.eval(tbl, heap) * exp2.eval(tbl, heap));
            case "/":{
                if(exp2.eval(tbl, heap) != 0)
                    return (exp1.eval(tbl, heap) / exp2.eval(tbl, heap));
                throw new DivisionByZeroException();
            }

            default: return eval(tbl, heap);
        }
    }

    @Override
    public String toStr() {
        switch (opt){
            case "+": return " (" + exp1.toStr() + "+" + exp2.toStr()+ ") ";
            case "-": return " (" + exp1.toStr() + "-" + exp2.toStr()+ ") ";
            case "*": return " (" + exp1.toStr() + "*" + exp2.toStr()+ ") ";
            case "/": return " (" + exp1.toStr() + "/" + exp2.toStr()+ ") ";
            default: return toStr();
        }
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


    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }
}
