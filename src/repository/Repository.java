package repository;

import domain.DataStructures.List.*;
import domain.PrgState;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

/**
 * Created by Dutzi on 10/14/2015.
 */
public class Repository implements IRepository {
    private List<PrgState> states;


    public void setPrgList(List<PrgState> prgStates) {
        this.states = prgStates;
    }

    public List<PrgState> getPrgList(){return this.states;}

    public Repository(List<PrgState> prgStates)
    {
        this.states = prgStates;
    }

    public Repository(){this.states = null;}


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
    public List<PrgState> deserialize() throws IOException, ClassNotFoundException {
        //IList<PrgState> states = null;
        ObjectInputStream in = null;
        try{
            FileInputStream file = new FileInputStream("PrgStateFile.ser");
            in = new ObjectInputStream(file);
            List<PrgState> st = (List<PrgState>) in.readObject();
            this.setPrgList(st);

        }catch (ClassNotFoundException e){
            System.err.println("Serialization failed: " + e.getMessage());
        }
        finally {
            in.close();
            return this.states;
        }
    }

    public void writeToFile() throws IOException, EmptyRepository{
            FileChannel fc = new RandomAccessFile("log.txt", "rw").getChannel();
            fc.position(fc.size());
            fc.write(ByteBuffer.wrap(this.getPrgList().toString().getBytes()));


    }
}
