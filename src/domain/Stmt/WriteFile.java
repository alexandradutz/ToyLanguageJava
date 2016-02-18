package domain.Stmt;

import domain.DataStructures.Dictionary.FullMapException;
import domain.DataStructures.Dictionary.IsNotKeyException;
import domain.DataStructures.List.FullListException;
import domain.Expression.DivisionByZeroException;
import domain.Expression.Exp;
import domain.Expression.VariableNotDefinedException;
import domain.MyBuffer;
import domain.PrgState;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Dutzi on 1/21/2016.
 */
public class WriteFile implements IStmt {
    private String filename;
    private Exp varname;

    public WriteFile(String f, Exp v){
        this.filename = f;
        this.varname = v;
    }

    @Override
    public String toString() {
        return "writeFile(" + filename + ", " + varname.toStr() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws FullListException, DivisionByZeroException, IsNotKeyException, VariableNotDefinedException, FullMapException {
        System.out.println("what da fac");
        if(state.getFileTable().isKey(filename)) {
            MyBuffer buf = state.getFileTable().getValue(filename);
            if (buf.getThreadId() == state.getId()) {
                Integer[] b = buf.getElements();
                if (b[0] == null) {
                    System.out.println("what da fac 2");
                    b[0] = varname.eval(state.getSymTable(), state.getHeap());
                    //state.getFileTable().add(filename, new MyBuffer(b, state.getId()));
                } else if (b[1] == null) {
                    System.out.println("what da fac 3");
                    b[1] = varname.eval(state.getSymTable(), state.getHeap());
                    //state.getFileTable().add(filename, new MyBuffer(b, state.getId()));
                } else {
                    System.out.println("pasul 156145");
                    writeToFile(b);
                    System.out.println("pasul 1515154");
                    b[0] = varname.eval(state.getSymTable(), state.getHeap());
                    b[1] = null;
                    //state.getFileTable().add(filename, new MyBuffer(b, state.getId()));
                }
            } else {
                System.out.println("what da fac 5");
                state.getExeStack().push(this);
            }
        }
        else{
            //Integer[] elem = new Integer[2];
            //elem[0] = varname.eval(state.getSymTable(), state.getHeap());
            //elem[1] = null;
            //MyBuffer buff = new MyBuffer(elem, state.getId());
            //state.getFileTable().add(filename, buff);
            System.out.println("what da fac 6");
            throw new IsNotKeyException();
        }
        System.out.println("fsdfjdsnfjkdkjf");
        return null;
    }

    public void writeToFile(Integer[] b){
        try {
            System.out.println("pasul 1");
            FileChannel fc = new RandomAccessFile(filename, "rw").getChannel();
            System.out.println("pasul 2");
            fc.position(fc.size());
            System.out.println("pasul 3");
            fc.write(ByteBuffer.wrap(b[0].toString().getBytes()));
            System.out.println("pasul 4");
            fc.write(ByteBuffer.wrap(b[1].toString().getBytes()));
            System.out.println("pasul 5");
            //fc.close();
        } catch (FileNotFoundException e) {
            System.out.println("No file found");
        } catch (IOException e) {
            System.out.println("Cannot close file");
        }

    }
}
