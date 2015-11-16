package domain.Expression;

import domain.DataStructures.Dictionary.IDictionary;
import domain.DataStructures.Dictionary.IsNotKeyException;

import java.io.Serializable;

/**
 * Created by Dutzi on 10/13/2015.
 */
public abstract class Exp implements Serializable{
    public abstract int eval(IDictionary tbl) throws IsNotKeyException, DivisionByZeroException, VariableNotDefinedException;
    public abstract String toStr();
}
