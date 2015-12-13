package domain.Stmt;

import domain.DataStructures.Dictionary.FullMapException;
import domain.DataStructures.Dictionary.IDictionary;
import domain.DataStructures.Dictionary.IsNotKeyException;
import domain.DataStructures.List.IList;
import domain.Expression.*;
import domain.PrgState;

import java.util.ArrayList;

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
        }catch (IsNotKeyException e){
            System.out.println("Input value is wrong.");
        }
        res = res + " default: " + defaultStmt.toString();
        return res;
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZeroException, IsNotKeyException, VariableNotDefinedException, FullMapException {
        IDictionary<String, Integer> symTable = state.getSymTable();
        ArrayList<Exp> list = this.getCaseTbl().keys();

        IStmt prevIfStmt = this.getDefaultStmt();

        for (Exp exp : list) {
            try {
                prevIfStmt = new IfStmt(new ArithExp(new ConstExp(symTable.getValue(this.getVarname())), exp, "-"), prevIfStmt, this.getCaseTbl().getValue(exp));
            } catch (Exception e) {
                System.out.println("Not a good value: in controller switch");
            }
        }
        state.getExeStack().push(prevIfStmt);
        return null;
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
