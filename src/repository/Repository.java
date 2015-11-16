package repository;

import domain.DataStructures.Dictionary.ArrayDictionary;
import domain.DataStructures.Dictionary.FullMapException;
import domain.DataStructures.Dictionary.IDictionary;
import domain.DataStructures.Dictionary.LibDictionary;
import domain.DataStructures.List.*;
import domain.DataStructures.Stack.ArrayStack;
import domain.DataStructures.Stack.IStack;
import domain.DataStructures.Stack.LibStack;
import domain.Expression.*;
import domain.PrgState;
import domain.Stmt.*;

import java.io.*;

/**
 * Created by Dutzi on 10/14/2015.
 */
public class Repository implements IRepository {
    private IList<PrgState> states;

    public IList<PrgState> getState(){
        return states;
    }

    public void setCrtPrg(IList<PrgState> prgStates) {
        this.states = prgStates;
    }


    public Repository(IList<PrgState> prgStates)
    {
        this.states = prgStates;
    }

    public Repository(){this.states = null;}

    @Override
    public PrgState getCrtPrg() throws RepositoryException
    {
        try{
            if(states.size() > 0){
                return this.states.get(0);
            }
        }catch (IndexOutOfBoundException e){
            throw new RepositoryException();
        }
        throw new RepositoryException();
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
        try {
            this.states.add(inputState);
        } catch (FullListException e){
            System.err.println("full repository exception");
        }

    }

    @Override
    public void serialize() throws IOException {
        ObjectOutputStream out = null;
        try {
            FileOutputStream file = new FileOutputStream("PrgStateFile.ser");
            out = new ObjectOutputStream(file);
            out.writeObject(states);
            out.close();
        }
        catch (IOException e){
            System.err.println("Serialization failed: " + e.getMessage());
        }
    }

    @Override
    public IList<PrgState> deserialize() throws IOException, ClassNotFoundException {
        IList<PrgState> states = null;
        ObjectInputStream in = null;
        try{
            FileInputStream file = new FileInputStream("PrgStateFile.ser");
            in = new ObjectInputStream(file);
            this.states = (IList<PrgState>) in.readObject();
            in.close();
        }catch (ClassNotFoundException e){
            System.err.println("Serialization failed: " + e.getMessage());
        }
        return states;

    }
}
