package controller;

import domain.DataStructures.Dictionary.FullMapException;
import domain.DataStructures.Dictionary.IDictionary;
import domain.DataStructures.Dictionary.IsNotKeyException;
import domain.DataStructures.List.FullListException;
import domain.DataStructures.List.IndexOutOfBoundException;
import domain.DataStructures.Stack.EmptyStackException;
import domain.DataStructures.List.IList;
import domain.DataStructures.Stack.*;
import domain.Expression.*;
import domain.PrgState;
import domain.Stmt.*;
import repository.IRepository;
import repository.Repository;
import repository.RepositoryException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Dutzi on 10/20/2015.
 */
public class Controller
{
    private IRepository repo;
    private boolean debugFlag = false;
    private PrgState crtPrg;
    private boolean logFlag = false;

    /**
     *
     */
    public void changeDebugFlag() {
        debugFlag = !debugFlag;
    }

    public void changeLogFlag() {
        logFlag = !logFlag;
    }

    public boolean isLogFlag(){return logFlag;}

    public Controller(IRepository repo) throws RepositoryException
    {
        this.repo = repo;
        crtPrg = repo.getCrtPrg();
   }

    public PrgState getCrtPrgState() throws RepositoryException {
        return repo.getCrtPrg();
    }
    public void oneStep(String file) throws IOException, StatementExecutionException, DivisionByZeroException, VariableNotDefinedException, IsNotKeyException, FullMapException, FullListException
    {
        IStack<IStmt> stk = crtPrg.getExeStack();
        try {
            if(debugFlag)
            {
                System.out.println("Stack:  " + crtPrg.getExeStack().toString() +
                        "\nSymbol Table: " + crtPrg.getSymTable().toString() +
                        "\nOutput: " + crtPrg.getOut().toString() +
                        "\n=========================================================\n");
            }
            if(logFlag)
            {
                this.getRepo().writeToFile(file);
            }
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
                IDictionary<String, Integer> symTable = crtPrg.getSymTable();
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
                IDictionary<String, Integer> symTable = crtPrg.getSymTable();
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
                IDictionary<String, Integer> symTable = crtPrg.getSymTable();
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
                IDictionary<String, Integer> symTable = crtPrg.getSymTable();
                if (symTable.isKey(exp.toStr())) {
                    symTable.add(exp.toStr(), exp.eval(symTable) + 1);

                }
            }
            if (crtStmt instanceof WhileStmt) {
                IDictionary<String, Integer> symTable = crtPrg.getSymTable();
                WhileStmt whileSt = (WhileStmt) crtStmt;
                if (whileSt.getExpr().eval(symTable) != 0) {
                    IStmt stmt = whileSt.getStmt();

                    stk.push(whileSt);
                    stk.push(stmt);
                }

            }
            if (crtStmt instanceof SwitchStmt) {
                IDictionary<String, Integer> symTable = crtPrg.getSymTable();
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
                crtPrg.getExeStack().push(prevIfStmt);
            } else if (crtStmt instanceof PrintStmt) {
                PrintStmt pStmt = (PrintStmt) crtStmt;
                IList<String> out = crtPrg.getOut();
                out.add(Integer.toString(pStmt.getExp().eval(crtPrg.getSymTable())));
            }

        } catch(EmptyStackException ex)
        {
            throw new StatementExecutionException();
        }
        catch (RepositoryException ex)
        {
            throw  new StatementExecutionException();
        }
    }

    /**
     *
     */
    public void allStep(String file) throws IOException, RepositoryException, StatementExecutionException, DivisionByZeroException, VariableNotDefinedException, IsNotKeyException, FullListException, FullMapException
    {
        PrgState state = repo.getCrtPrg();
        IStack<IStmt> stk = state.getExeStack();
        while(!stk.isEmpty())
        {
            this.oneStep(file);
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
