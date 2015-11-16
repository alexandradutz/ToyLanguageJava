package controller;

import domain.DataStructures.Dictionary.FullMapException;
import domain.DataStructures.Dictionary.IDictionary;
import domain.DataStructures.Dictionary.IsNotKeyException;
import domain.DataStructures.List.FullListException;
import domain.DataStructures.Stack.EmptyStackException;
import domain.DataStructures.List.IList;
import domain.DataStructures.Stack.*;
import domain.Expression.*;
import domain.PrgState;
import domain.Stmt.*;
import repository.IRepository;
import repository.RepositoryException;

import java.util.*;

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


    public Controller(IRepository repo)
    {
        this.repo = repo;

    }


    public void oneStep() throws RepositoryException, StatementExecutionException, DivisionByZeroException, VariableNotDefinedException, IsNotKeyException, FullMapException, FullListException
    {
        PrgState state = repo.getCrtPrg();
        IStack<IStmt> stk = state.getExeStack();
        try {
            IStmt crtStmt = stk.pop();
            if (crtStmt instanceof CompStmt) {
                CompStmt crtStmt1 = (CompStmt) crtStmt;
                stk.push(crtStmt1.getSecond());
                stk.push(crtStmt1.getFirst());
            }

            if (crtStmt instanceof AssignStmt) {
                AssignStmt aStmt = (AssignStmt) crtStmt;
                String id = aStmt.getId();
                Exp exp = aStmt.getExp();
                IDictionary<String, Integer> symTable = state.getSymTable();
                symTable.add(id, exp.eval(symTable));
            }

            if (crtStmt instanceof IfSkipStmt) {
                IfSkipStmt ifSt = (IfSkipStmt) crtStmt;
                IStmt thenS = ifSt.getThenS();
                IStmt elseS = ifSt.getElseS();
                if(elseS == null){
                    ifSt.setElseS(new SkipStmt());
                    stk.push(ifSt);
                    //return;
                }
                Exp ifExp = ifSt.getExp();
                IDictionary<String, Integer> symTable = state.getSymTable();
                if (ifExp.eval(symTable) != 0) {
                    stk.push(thenS);
                } else {
                    stk.push(elseS);
                }
                //return;
            }

            if (crtStmt instanceof IfStmt) {
                IfStmt ifSt = (IfStmt) crtStmt;
                IStmt thenS = ifSt.getThenS();
                IStmt elseS = ifSt.getElseS();
                Exp ifExp = ifSt.getExp();
                IDictionary<String, Integer> symTable = state.getSymTable();
                if (ifExp.eval(symTable) != 0) {
                    stk.push(thenS);
                } else {
                    stk.push(elseS);
                }
                //return;
            }
            if (crtStmt instanceof IncStmt) {
                IncStmt incStmt = (IncStmt) crtStmt;
                Exp exp = incStmt.getVar();
                IDictionary<String, Integer> symTable = state.getSymTable();
                if (symTable.isKey(exp.toStr())) {
                    symTable.add(exp.toStr(), exp.eval(symTable) + 1);

                }
            }
            if (crtStmt instanceof WhileStmt) {
                IDictionary<String, Integer> symTable = state.getSymTable();
                WhileStmt whileSt = (WhileStmt) crtStmt;
                if (whileSt.getExpr().eval(symTable) != 0) {
                    IStmt stmt = whileSt.getStmt();

                    stk.push(whileSt);
                    stk.push(stmt);
                }

            }
            if (crtStmt instanceof SwitchStmt) {
                IDictionary<String, Integer> symTable = state.getSymTable();
                SwitchStmt switchSt = (SwitchStmt) crtStmt;

                ArrayList<Exp> list = switchSt.getCaseTbl().keys();

                IStmt prevIfStmt = switchSt.getDefaultStmt();

                for (Exp exp : list) {
                    try {
                        prevIfStmt = new IfStmt(new ArithExp(new ConstExp(symTable.getValue(switchSt.getVarname())), exp, "-"), prevIfStmt, switchSt.getCaseTbl().getValue(exp));
                    } catch (Exception e) {
                        System.out.println("Not a good value: in controller switch");
                    }
                }
                state.getExeStack().push(prevIfStmt);
            } else if (crtStmt instanceof PrintStmt) {
                PrintStmt pStmt = (PrintStmt) crtStmt;
                IList<String> out = state.getOut();
                out.add(Integer.toString(pStmt.getExp().eval(state.getSymTable())));
            }

        } catch(EmptyStackException ex)
        {
            throw new StatementExecutionException();
        }
        if(debugFlag)
        {
            System.out.println("Stack:  " + state.getExeStack().toString() +
                    "\nSymbol Table: " + state.getSymTable().toString() +
                    "\nOutput: " + state.getOut().toString() +
                    "\n=========================================================\n");
        }
    }

    /**
     *
     */
    public void allStep() throws RepositoryException, StatementExecutionException, DivisionByZeroException, VariableNotDefinedException, IsNotKeyException, FullListException, FullMapException
    {
        PrgState state = repo.getCrtPrg();
        IStack<IStmt> stk = state.getExeStack();
        while(!stk.isEmpty())
        {
            this.oneStep();
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
