package domain.Expression;

import domain.DataStructures.Dictionary.IDictionary;
import domain.DataStructures.Dictionary.IsNotKeyException;
import domain.DataStructures.Heap.IHeap;

/**
 * Created by Dutzi on 11/8/2015.
 */
public class BoolExp extends Exp {
    private String opt;
    private Exp exp1;
    private Exp exp2;
    /*
    * Options:
    *  <
    *  >
    *  <=
    *  >=
    *  ==
    *  !=
    */

    public BoolExp(Exp exp1, Exp exp2, String opt)
    {
        this.opt = opt;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public int eval(IDictionary<String, Integer> tbl, IHeap<Integer> heap)  throws IsNotKeyException, DivisionByZeroException, VariableNotDefinedException {
        switch (opt) {
            case "<":
                if (exp1.eval(tbl, heap) < exp2.eval(tbl, heap)) {
                    return 1;
                }
                return 0;
            case "<=":
                if (exp1.eval(tbl, heap) <= exp2.eval(tbl, heap)) {
                    return 1;
                }
                return 0;
            case ">=":
                if (exp1.eval(tbl, heap) >= exp2.eval(tbl, heap)) {
                    return 1;
                }
                return 0;
            case ">":
                if (exp1.eval(tbl, heap) > exp2.eval(tbl, heap)) {
                    return 1;
                }
                return 0;
            case "==":
                if (exp1.eval(tbl, heap) == exp2.eval(tbl, heap)) {
                    return 1;
                }
                return 0;
            case "!=":
                if (exp1.eval(tbl, heap) != exp2.eval(tbl, heap)) {
                    return 1;
                }
                return 0;
            default:
                return eval(tbl, heap);
        }
    }

    @Override
    public String toStr() {
        switch (opt) {
            case "<":
                return "(" + exp1.toStr() + " < " + exp2.toStr() + ")";
            case "<=":
                return "(" + exp1.toStr() + " <= " + exp2.toStr() + ")";
            case ">=":
                return "(" + exp1.toStr() + " >= " + exp2.toStr() + ")";
            case ">":
                return "(" + exp1.toStr() + " > " + exp2.toStr() + ")";
            case "==":
                return "(" + exp1.toStr() + " == " + exp2.toStr() + ")";
            case "!=":
                return "(" + exp1.toStr() + " != " + exp2.toStr() + ")";
            default:
                return toStr();
        }
    }



    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
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

}
