package repository;

import domain.DataStructures.Dictionary.*;
import domain.DataStructures.List.*;
import domain.DataStructures.Stack.ArrayStack;
import domain.DataStructures.Stack.IStack;
import domain.DataStructures.Stack.LibStack;
import domain.Expression.*;
import domain.PrgState;
import domain.Stmt.*;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Dutzi on 10/14/2015.
 */
public class Repository implements IRepository {
    private IList<PrgState> states;


    public void setCrtPrg(IList<PrgState> prgStates) {
        this.states = prgStates;
    }


    public Repository(IList<PrgState> prgStates)
    {
        this.states = prgStates;
    }

    public Repository(){this.states = null;}

    @Override
    public PrgState getCrtPrg() throws EmptyRepository
    {
        try{
            if(states.size() > 0){
                return this.states.get(0);
            }
        }catch (IndexOutOfBoundException e){
            throw new EmptyRepository();
        }
        throw new EmptyRepository();
    }
//
//    @Override
//    public void example1()
//    {
//        //example: v = 2;Print(v)
//        IStack exeStack = new LibStack<>();
//        IDictionary symTable = new LibDictionary<>();
//        IList out = new LibList<>();
//        LibDictionary<Exp, IStmt> tbl = new LibDictionary<>() ;
//        try {
//            tbl.add(new ConstExp(1), new CompStmt(new AssignStmt("a", new VarExp("v")), new PrintStmt(new ArithExp(new VarExp("a"), new ConstExp(1), "+"))));
//            tbl.add(new ConstExp(5), new CompStmt(new AssignStmt("a", new ArithExp(new VarExp("v"), new ConstExp(1), "+")), new PrintStmt(new ArithExp(new VarExp("a"), new ConstExp(1), "+"))));
//            tbl.add(new ConstExp(2), new CompStmt(new AssignStmt("a", new ArithExp(new VarExp("v"), new ConstExp(2), "+")), new PrintStmt(new ArithExp(new VarExp("a"), new ConstExp(1), "+"))));
//        }
//        catch (FullMapException e){
//            System.out.println("Full map");}
//        IStmt example = new CompStmt(new AssignStmt("v", new ConstExp(5)),
//                            new CompStmt(new SwitchStmt("v", tbl, new CompStmt(new AssignStmt("a", new ConstExp(0)), new PrintStmt(new VarExp("a")))),
//                                         new CompStmt(new AssignStmt("c", new ArithExp(new ReadExp(0), new BoolExp(new ConstExp(2), new ConstExp(10), "<"), "-")),
//                                                     new AssignStmt("d", new LogicalExp(new LogicalExp(new VarExp("c"),"!"), new BoolExp(new ConstExp(10), new ConstExp(10), "<="), "&&")))));
//
//        //exeStack.push(example);
//        PrgState inputState = new PrgState(exeStack, symTable, out, example);
//        try {
//            this.states.add(inputState);
//        } catch (FullListException e){
//            System.err.println("full repository exception");
//        }
//
//    }

    @Override
    public void serialize() {
        ObjectOutputStream out = null;
        try {
            FileOutputStream file = new FileOutputStream("PrgStateFile.ser");
            out = new ObjectOutputStream(file);
            out.writeObject(states);
        }
        catch (IOException e){
            System.err.println("Serialization failed: " + e.getMessage());
        }
        finally {
            if (out != null)
                try {
                    out.close();
                } catch(IOException e)  {
                    System.err.println("Error " + e.getMessage());
                }
        }
    }

    @Override
    public IList<PrgState> deserialize() throws IOException, ClassNotFoundException {
        //IList<PrgState> states = null;
        ObjectInputStream in = null;
        try{
            FileInputStream file = new FileInputStream("PrgStateFile.ser");
            in = new ObjectInputStream(file);
            IList<PrgState> st = (IList<PrgState>) in.readObject();
            this.setCrtPrg(st);

        }catch (ClassNotFoundException e){
            System.err.println("Serialization failed: " + e.getMessage());
        }
        finally {
            in.close();
            return this.states;
        }
    }

    public void writeToFile() throws IOException, EmptyRepository{
//        try {
//            out.write("==========Program State=======");
//            out.newLine();
//            out.write("EXE STACK:");
//            out.newLine();
//            out.write(getCrtPrg().getExeStack().toString());
//            out.newLine();
//            out.write("SYMBOL TABLE:");
//            out.newLine();
//            for (String key : getCrtPrg().getSymTable().keys()) {
//                out.write(key + "-->" + getCrtPrg().getSymTable().getValue(key));
//                out.newLine();
//            }
//            out.newLine();
//            out.write("OUTPUT: ");
//            out.newLine();
//            IList<String> lst = getCrtPrg().getOut();
//            for (int i = 0; i < lst.size(); i++) {
//                out.write(lst.get(i));
//                out.newLine();
//            }
//            out.newLine();
//            out.close();
//        }
//        catch (IndexOutOfBoundException e){
//            throw  new EmptyRepository();
//        }
//        catch (IsNotKeyException e){
//            throw  new EmptyRepository();
//        }
        try {
            FileChannel fc = new RandomAccessFile("log.txt", "rw").getChannel();
            fc.position(fc.size());
            fc.write(ByteBuffer.wrap(this.getCrtPrg().toStr().getBytes()));
        } catch (IOException| EmptyRepository  e) {
            System.out.println("no such file");
        }

    }
}
