package repository;

import domain.DataStructures.List.IList;
import domain.PrgState;

import java.io.IOException;

/**
 * Created by Dutzi on 10/14/2015.
 */
public interface IRepository {
    PrgState getCrtPrg() throws RepositoryException;
    void setCrtPrg(IList<PrgState> state);
    void example1();
    void serialize() throws IOException;
    IList<PrgState> deserialize() throws IOException, ClassNotFoundException;
}
