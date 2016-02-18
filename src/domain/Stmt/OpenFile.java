package domain.Stmt;

import domain.DataStructures.Dictionary.FullMapException;
import domain.DataStructures.Dictionary.IsNotKeyException;
import domain.DataStructures.List.FullListException;
import domain.Expression.DivisionByZeroException;
import domain.Expression.VariableNotDefinedException;
import domain.MyBuffer;
import domain.PrgState;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/**
 * Created by Dutzi on 1/21/2016.
 */
public class OpenFile implements IStmt {
    private String filename;

    public OpenFile(String f){
        this.filename = f;
    }

    @Override
    public PrgState execute(PrgState state) throws FullListException, DivisionByZeroException, IsNotKeyException, VariableNotDefinedException, FullMapException {
        if(state.getFileTable().isKey(filename)){
            MyBuffer b = state.getFileTable().getValue(filename);
            if (b.getThreadId() == null) {
                state.getFileTable().add(filename, new MyBuffer(state.getId()));
            }
            else{ if(state.getFileTable().getValue(filename).getThreadId() == state.getId()){
                return null;
            }
            else{
                state.getExeStack().push(this);
            }
            }
        }
        else{
            try {
                new RandomAccessFile(filename, "rw");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            state.getFileTable().add(filename, new MyBuffer(state.getId()));
        }
        return null;
    }

    @Override
    public String toString() {
        return "openFile(" + filename + ")";
    }
}
