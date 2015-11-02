package domain.Expression;

import domain.DataStructures.Interface.IDictionary;
import exception.MyException;

/**
 * Created by Dutzi on 10/13/2015.
 */
public class VarExp extends Exp {
    private String id;

    public VarExp(String id) {
        this.id = id;
    }


    @Override
    public int eval(IDictionary tbl) {
        int value;
        try {
            value = (int) tbl.getValue(id);
        } catch (MyException e) {
            System.out.println(e.toString());
            return 0;
        }
        return value;
    }

    @Override
    public String toStr() {
        return id + "";
    }
}
