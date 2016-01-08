package repository;

import domain.DataStructures.Dictionary.IsNotKeyException;
import domain.DataStructures.List.IList;
import domain.DataStructures.List.IndexOutOfBoundException;
import domain.PrgState;

import java.io.IOException;
import java.util.List;

/**
 * Created by Dutzi on 10/14/2015.
 */
public interface IRepository {
    void setPrgList(List<PrgState> state);
    List<PrgState> getPrgList();
    void serialize();
    List<PrgState> deserialize() throws IOException, ClassNotFoundException;
    void writeToFile() throws IOException, EmptyRepository;
}
