package repository;

import domain.DataStructures.Dictionary.ArrayDictionary;
import domain.DataStructures.Dictionary.FullMapException;
import domain.DataStructures.Dictionary.IDictionary;
import domain.DataStructures.Dictionary.LibDictionary;
import domain.DataStructures.List.ArrayList;
import domain.DataStructures.List.IList;
import domain.DataStructures.List.LibList;
import domain.DataStructures.Stack.ArrayStack;
import domain.DataStructures.Stack.IStack;
import domain.DataStructures.Stack.LibStack;
import domain.Expression.*;
import domain.PrgState;
import domain.Stmt.*;

/**
 * Created by Dutzi on 10/14/2015.
 */
public class Repository implements IRepository {
    private PrgState[] state;

    public PrgState[] getState(){
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
        IStack exeStack = new LibStack<>();
        IDictionary symTable = new LibDictionary<>();
        IList out = new LibList<>();
        LibDictionary<Exp, IStmt> tbl = new LibDictionary<>() ;
        try {
            tbl.add(new ConstExp(1), new CompStmt(new AssignStmt("a", new VarExp("v")), new PrintStmt(new ArithExp(new VarExp("a"), new ConstExp(1), "+"))));
            tbl.add(new ConstExp(5), new CompStmt(new AssignStmt("a", new ArithExp(new VarExp("v"), new ConstExp(1), "+")), new PrintStmt(new ArithExp(new VarExp("a"), new ConstExp(1), "+"))));
            tbl.add(new ConstExp(2), new CompStmt(new AssignStmt("a", new ArithExp(new VarExp("v"), new ConstExp(2), "+")), new PrintStmt(new ArithExp(new VarExp("a"), new ConstExp(1), "+"))));
        }
        catch (FullMapException e){
            System.out.println("Full map");}
        IStmt example = new CompStmt(new AssignStmt("v", new ConstExp(5)),
                            new CompStmt(new SwitchStmt("v", tbl, new CompStmt(new AssignStmt("a", new ConstExp(0)), new PrintStmt(new VarExp("a")))),
                                         new CompStmt(new AssignStmt("c", new ArithExp(new ReadExp(0), new BoolExp(new ConstExp(2), new ConstExp(10), "<"), "-")),
                                                     new AssignStmt("d", new LogicalExp(new LogicalExp(new VarExp("c"),"!"), new BoolExp(new ConstExp(10), new ConstExp(10), "<="), "&&")))));

        //exeStack.push(example);
        PrgState inputState = new PrgState(exeStack, symTable, out, example);
        this.state[0] = inputState;
        //if(exeStack.isEmpty()){
        //    System.out.println("No input program. Exe Stack IS empty!");
       // }else{
       //     System.out.println("Exe Stack IS NOT empty");
       // }

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
        //this.state[0] = inputState;
       // if(exeStack.isEmpty()){
        //    System.out.println("No input program. Exe Stack IS empty!");
       // }else{
        //    System.out.println("Exe Stack IS NOT empty");
       // }

    }

}
