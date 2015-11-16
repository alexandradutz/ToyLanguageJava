package repository;

import domain.PrgState;

/**
 * Created by Dutzi on 10/14/2015.
 */
public interface IRepository {
    public PrgState getCrtPrg();
    public void setCrtPrg(PrgState state);
    public void example1();
    public void example2();
    public void serialize();
}
