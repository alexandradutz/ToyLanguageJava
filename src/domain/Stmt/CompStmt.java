package domain.Stmt;

import domain.DataStructures.Dictionary.IDictionary;

/**
 * Created by Dutzi on 10/11/2015.
 */
public class CompStmt implements IStmt {
    private IStmt first;
    private IStmt second;

    public CompStmt(IStmt f, IStmt s)
    {
        first = f;
        second = s;
    }

    @Override
    public String toString() {
        return first.toString() + ";" + second.toString();
    }


    public IStmt getSecond() {
        return second;
    }

    public void setSecond(IStmt second) {
        this.second = second;
    }

    public IStmt getFirst() {

        return first;
    }

    public void setFirst(IStmt first) {
        this.first = first;
    }
}
