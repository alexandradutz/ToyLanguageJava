package controller;

import domain.DataStructures.IDictionary;
import domain.DataStructures.IList;
import domain.DataStructures.IStack;
import domain.Expression.Exp;
import domain.PrgState;
import domain.Stmt.*;
import repository.IRepository;

/**
 * Created by Dutzi on 10/20/2015.
 */
public class Controller
{
    private IRepository repo;
    private boolean debugFlag = false;

    /**
     *
     */
    public void changeDebugFlag() {
        debugFlag = !debugFlag;
    }

    /**
     *
     * @param repo
     */
    public Controller(IRepository repo)
    {
        this.repo = repo;
    }


    /**
     * @
     * @param state
     */
    public void oneStep(PrgState state)
    {
        IStack stk = state.getExeStack();
        if(stk.isEmpty()) System.out.println("stack is empty");
        IStmt crtStmt = stk.pop();
        if(crtStmt instanceof CompStmt)
        {
            CompStmt crtStmt1 = (CompStmt) crtStmt;
            stk.push(crtStmt1.getSecond());
            stk.push(crtStmt1.getFirst());
        }

        if(crtStmt instanceof AssignStmt)
        {
            AssignStmt aStmt = (AssignStmt) crtStmt;
            String id = aStmt.getId();
            Exp exp = aStmt.getExp();
            IDictionary symTable = state.getSymTable();
            symTable.add(id, exp.eval(symTable));
        }

        if(crtStmt instanceof IfStmt)
        {
            IfStmt ifSt = (IfStmt) crtStmt;
            IStmt thenS = ifSt.getThenS();
            IStmt elseS = ifSt.getElseS();
            Exp ifExp = ifSt.getExp();
            IDictionary symTable = state.getSymTable();
            if(ifExp.eval(symTable) != 0)
            {
                stk.push(thenS);
            }
            else
            {
                stk.push(elseS);
            }
        }
        else if (crtStmt instanceof PrintStmt)
        {
            IDictionary symTable = state.getSymTable();
            PrintStmt pStmt = (PrintStmt) crtStmt;
            IList out = state.getOut();
            out.add(Integer.toString(pStmt.getExp().eval(state.getSymTable())));
        }
        if(debugFlag)
        {
            System.out.println("Stack: " + state.getExeStack().toStr() +
                    "\nSymbol Table: " + state.getSymTable().toStr() +
                    "\nOutput: " + state.getOut().toStr() +
                    "\n=========================================================\n");
        }
        if(stk.isEmpty())
        {
            System.out.println("\nFINISHED\n" + "Output:" + state.getOut().toStr() + "\n");
        }
    }

    /**
     *
     */
    public void allStep()
    {
        PrgState state = repo.getCrtPrg();
        IStack stk = state.getExeStack();
        while(!stk.isEmpty())
        {
            this.oneStep(state);
        }
    }


    public IRepository getRepo() {
        return repo;
    }

    public void setRepo(IRepository repo) {
        this.repo = repo;
    }

    public boolean isDebugFlag() {
        return debugFlag;
    }

    public void setDebugFlag(boolean debugFlag) {
        this.debugFlag = debugFlag;
    }
}
