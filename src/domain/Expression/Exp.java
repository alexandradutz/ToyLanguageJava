package domain.Expression;

import domain.DataStructures.IDictionary;

/**
 * Created by Dutzi on 10/13/2015.
 */
public abstract class Exp {
    public abstract int eval(IDictionary tbl);
    public abstract String toStr();
}
