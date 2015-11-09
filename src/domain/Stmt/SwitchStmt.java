package domain.Stmt;

import domain.DataStructures.Dictionary.IDictionary;
import domain.Expression.Exp;
/**
 * Created by Dutzi on 11/9/2015.
 */
public class SwitchStmt implements IStmt {
    private String varname;
    private IDictionary<Exp, IStmt> caseTbl;



    private IStmt defaultStmt;

    public SwitchStmt(String var, IDictionary<Exp, IStmt> tbl, IStmt defaultStmt){
        this.varname = var;
        this.caseTbl = tbl;
        this.defaultStmt = defaultStmt;
    }

    @Override
    public String toString() {
        String res = " Switch (" + varname.toString() + ") ";
        try {
            for (Exp e : caseTbl.keys()) {
                res = res + " case " + e.toStr() + ": " + caseTbl.getValue(e).toString();
            }
        }catch (Exception e){
            System.out.println("Input value is wrong.");
        }
        res = res + " default: " + defaultStmt.toString();
        return res;
    }

    public String getVarname() {
        return varname;
    }

    public IDictionary<Exp, IStmt> getCaseTbl() {
        return caseTbl;
    }

    public IStmt getDefaultStmt() {
        return defaultStmt;
    }

}
