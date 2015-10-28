package domain.DataStructures;

import domain.Stmt.IStmt;

/**
 * Created by Dutzi on 10/6/2015.
 */

public interface IStack {
    public void push(IStmt o);
    public IStmt pop();
    public boolean isEmpty();
    public IStmt top();
    public String toStr();
}
