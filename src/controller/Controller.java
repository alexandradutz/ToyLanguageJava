package controller;

import domain.DataStructures.Interface.IDictionary;
import domain.DataStructures.Interface.IList;
import domain.DataStructures.Interface.IStack;
import domain.Expression.ConstExp;
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
        if(crtStmt instanceof IncStmt)
        {
            IncStmt incStmt = (IncStmt) crtStmt;
            Exp exp = incStmt.getVar();
            IDictionary symTable = state.getSymTable();
            if(symTable.isKey(exp.toStr()))
            {
                symTable.modify(exp.toStr(), exp.eval(symTable) + 1);
            }
        }
        if(crtStmt instanceof ForStmt)
        {
            IDictionary symTable = state.getSymTable();
            ForStmt forSt = (ForStmt) crtStmt;
            if(forSt.getExp1().eval(symTable) < forSt.getExp2().eval(symTable)) {
                int e1 = forSt.getExp1().eval(symTable);
                int e2 = forSt.getExp2().eval(symTable);
                int e3 = forSt.getExp3().eval(symTable);
                IStmt stmt = forSt.getStmt();

                Exp f1 = new ConstExp(e1 + e3);
                Exp f2 = new ConstExp(e2);
                Exp f3 = new ConstExp(e3);
                ForStmt forSt2 = new ForStmt(f1, f2, f3, stmt);
                stk.push(forSt2);
                stk.push(stmt);
            }
        }
        if(crtStmt instanceof WhileStmt)
        {
            IDictionary symTable = state.getSymTable();
            WhileStmt whileSt = (WhileStmt) crtStmt;
            if(whileSt.getExpr().eval(symTable) != 0) {
                int e1 = whileSt.getExpr().eval(symTable);
                IStmt stmt = whileSt.getStmt();

                stk.push(whileSt);
                stk.push(stmt);
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
