package domain.Stmt;

import domain.DataStructures.IDictionary;

/**
 * Created by Dutzi on 10/11/2015.
 */
public interface IStmt {
    public String toStr();
    void exec(IDictionary symT);

}