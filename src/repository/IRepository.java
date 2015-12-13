package repository;

import domain.DataStructures.Dictionary.IsNotKeyException;
import domain.DataStructures.List.IList;
import domain.DataStructures.List.IndexOutOfBoundException;
import domain.PrgState;

import java.io.IOException;

/**
 * Created by Dutzi on 10/14/2015.
 */
public interface IRepository {
    PrgState getCrtPrg() throws EmptyRepository;
    void setCrtPrg(IList<PrgState> state);
    void serialize();
    IList<PrgState> deserialize() throws IOException, ClassNotFoundException;
    void writeToFile() throws IOException, EmptyRepository;
}
