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
import repository.EmptyRepository;
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

    public PrgState getCrtPrgState() throws EmptyRepository {
        return repo.getCrtPrg();
    }
    public PrgState oneStep(PrgState state) throws EmptyStackException, IOException, StatementExecutionException, DivisionByZeroException, VariableNotDefinedException, IsNotKeyException, FullMapException, FullListException
    {
        IStack<IStmt> stk = crtPrg.getExeStack();
        try {
            if (debugFlag) {
                System.out.println("Stack:  " + crtPrg.getExeStack().toString() +
                        "\nSymbol Table: " + crtPrg.getSymTable().toString() +
                        "\nOutput: " + crtPrg.getOut().toString() +
                        "\n=========================================================\n");
            }
            if (logFlag) {
                this.getRepo().writeToFile();
            }
            IStmt crtStmt = stk.pop();
            return crtStmt.execute(state);
        } catch (EmptyRepository ex)
        {
            throw  new StatementExecutionException();
        }
    }

    /**
     *
     */
    public void allStep(PrgState state) throws EmptyStackException, IOException, EmptyRepository, StatementExecutionException, DivisionByZeroException, VariableNotDefinedException, IsNotKeyException, FullListException, FullMapException
    {
        IStack<IStmt> stk = state.getExeStack();
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
