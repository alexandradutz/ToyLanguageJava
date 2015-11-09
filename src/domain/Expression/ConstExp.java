package domain.Expression;

import domain.DataStructures.Dictionary.IDictionary;
import domain.DataStructures.Dictionary.IsNotKeyException;

/**
 * Created by Dutzi on 10/13/2015.
 */
public class ConstExp extends Exp {
    private int nr;

    public ConstExp(int number)
    {
        nr = number;
    }


    @Override
    public int eval(IDictionary tbl) throws IsNotKeyException, DivisionByZeroException, VariableNotDefinedException {
        return nr;
    }

    @Override
    public String toStr()
    {
        return nr + "";
    }
}
