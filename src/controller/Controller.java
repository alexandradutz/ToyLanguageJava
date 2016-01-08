package controller;

import domain.DataStructures.Dictionary.FullMapException;
import domain.DataStructures.Dictionary.IsNotKeyException;
import domain.DataStructures.List.FullListException;
import domain.DataStructures.Stack.EmptyStackException;
import domain.DataStructures.Stack.*;
import domain.Expression.*;
import domain.PrgState;
import domain.Stmt.*;
import repository.EmptyRepository;
import repository.IRepository;
import repository.RepositoryException;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

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
   }


    public void allStep() throws  InterruptedException, IOException, EmptyRepository{
        while(true){
            //remove the completed programs
            List<PrgState>  prgList = removeCompletedPrg(repo.getPrgList());
            if(prgList.size()  == 0){
                return; //complete the execution of all threads
            }
            else oneStepForAllPrg(prgList);
        }
    }

    public void oneStep()throws  InterruptedException, IOException, EmptyRepository{
        List<PrgState>  prgList = removeCompletedPrg(repo.getPrgList());
        oneStepForAllPrg(prgList);
    }

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    public void oneStepForAllPrg(List<PrgState> prgList)throws InterruptedException, IOException, EmptyRepository{
        List<Callable<PrgState>> callList = prgList.stream()
                .map(p -> (Callable<PrgState>) p::oneStep)
                .collect(Collectors.toList());

        //start the execution of the callables
        //it returns the list of new created threads
        ExecutorService executor = Executors.newFixedThreadPool(15);
        List<PrgState> newPrgList =
                executor.invokeAll(callList)
                        .stream()
                        .map(future -> {   try { return future.get(); }
                                            catch(Exception e) {
                                                return null;
                                            }
                                        }
                        )
                        .filter(p -> p!=null)
                        .collect(Collectors.toList());
        //add the new created threads to the list of existing threads

        prgList.forEach(p -> {if (! newPrgList.stream().anyMatch(s -> s.getId() == p.getId())) newPrgList.add(p);});

//       for(PrgState p : prgList){
//           for(PrgState s : newPrgList){
//               if(s.getId() == p.getId())
//           }
//       }

        //prgList.addAll(newPrgList);
        repo.setPrgList(newPrgList);
        if (logFlag) {
            repo.writeToFile();
        }
        else{
            newPrgList.forEach(p -> System.out.println(p.toString()));
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
