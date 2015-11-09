package domain.Stmt;

import domain.DataStructures.Interface.IDictionary;

/**
 * Created by Dutzi on 10/11/2015.
 */
public interface IStmt {
    public String toString();
    void exec(IDictionary symT);

}
