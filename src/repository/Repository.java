package repository;

import domain.DataStructures.*;
import domain.DataStructures.Interface.IDictionary;
import domain.DataStructures.Interface.IList;
import domain.DataStructures.Interface.IStack;
import domain.Expression.ArithExp;
import domain.Expression.ConstExp;
import domain.Expression.VarExp;
import domain.PrgState;
import domain.Stmt.*;

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

        //exeStack.push(example);
        PrgState inputState = new PrgState(exeStack, symTable, out, example);
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
        IStack exeStack = new ArrayStack();
        IDictionary symTable = new ArrayDictionary();
        IList out = new ArrayList();
        IStmt prgStmt =  new CompStmt(new AssignStmt("v",new ConstExp(6)),
                new CompStmt(new WhileStmt(new ArithExp(new VarExp("v"), new ConstExp(4), "-"),
                new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(new VarExp("v"), new ConstExp(1), "-")))),
                new PrintStmt(new VarExp("v"))));

        //exeStack.push(prgStmt);
        PrgState inputState = new PrgState(exeStack, symTable, out, prgStmt);
        this.state[0] = inputState;
        if(exeStack.isEmpty()){
            System.out.println("No input program. Exe Stack IS empty!");
        }else{
            System.out.println("Exe Stack IS NOT empty");
        }

    }

}
