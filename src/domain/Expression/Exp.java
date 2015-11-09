package domain.Expression;

import domain.DataStructures.Dictionary.IDictionary;
import domain.DataStructures.Dictionary.IsNotKeyException;

/**
 * Created by Dutzi on 10/13/2015.
 */
public abstract class Exp {
    public abstract int eval(IDictionary tbl) throws IsNotKeyException, DivisionByZeroException, VariableNotDefinedException;
    public abstract String toStr();
}
