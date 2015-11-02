package domain.Expression;

import domain.DataStructures.Interface.IDictionary;

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
    public int eval(IDictionary tbl) {
        return nr;
    }

    @Override
    public String toStr()
    {
        return nr + "";
    }
}
