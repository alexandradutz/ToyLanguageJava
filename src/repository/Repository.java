package repository;

import domain.DataStructures.*;
import domain.Expression.ArithExp;
import domain.Expression.ConstExp;
import domain.Expression.VarExp;
import domain.PrgState;
import domain.Stmt.AssignStmt;
import domain.Stmt.CompStmt;
import domain.Stmt.IStmt;
import domain.Stmt.PrintStmt;
import exception.MyException;

/**
 * Created by Dutzi on 10/14/2015.
 */
public class Repository implements IRepository {
    private PrgState[] state;

    public PrgState[] getState() {
        return state;
    }

    public void setCrtPrg(PrgState st) {
        this.state[0] = st;
    }


    public Repository()
    {
        this.state = new PrgState[10];
    }

    @Override
    public PrgState getCrtPrg()
    {
        return this.state[0];
    }

    @Override
    public void example1()
    {
        //example: v = 2;Print(v)
        IStack exeStack = new ArrayStack();
        IDictionary symTable = new ArrayDictionary();
        IList out = new ArrayList();
        IStmt example = new CompStmt(new AssignStmt("v", new ConstExp(2)), new PrintStmt(new VarExp("v")));

        exeStack.push(example);
        PrgState inputState = new PrgState(exeStack, symTable, out);
        this.state[0] = inputState;
        if(exeStack.isEmpty()){
            System.out.println("No input program. Exe Stack IS empty!");
        }else{
            System.out.println("Exe Stack IS NOT empty");
        }

    }

    @Override
    public void example2()
    {
        //a=2+3*5;b=a+1;Print(b)
        IStack exeStack = new ArrayStack();
        IDictionary symTable = new ArrayDictionary();
        IList out = new ArrayList();
        IStmt ex2 = new CompStmt(new AssignStmt("a", new ArithExp(new ConstExp(2),new ArithExp(new ConstExp(3), new ConstExp(5), "*"), "+")), new CompStmt(
                new AssignStmt("b",new ArithExp(new VarExp("a"), new ConstExp(1), "+")), new PrintStmt(new VarExp("b"))));

        exeStack.push(ex2);
        PrgState inputState = new PrgState(exeStack, symTable, out);
        this.state[0] = inputState;
        if(exeStack.isEmpty()){
            System.out.println("No input program. Exe Stack IS empty!");
        }else{
            System.out.println("Exe Stack IS NOT empty");
        }

    }

}
