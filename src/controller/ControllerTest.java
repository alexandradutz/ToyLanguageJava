package controller;

import domain.Expression.ConstExp;
import domain.Expression.Exp;
import domain.Stmt.AssignStmt;
import domain.Stmt.IStmt;
import junit.framework.TestCase;
import repository.IRepository;
import repository.Repository;

/**
 * Created by Dutzi on 11/2/2015.
 */
public class ControllerTest extends TestCase {

    public void testChangeDebugFlag() throws Exception {
        IRepository repo = new Repository();
        Controller ctrl = new Controller(repo);
        assertEquals(false, ctrl.isDebugFlag());
        ctrl.changeDebugFlag();
        assertEquals(true, ctrl.isDebugFlag());

    }

//    public void testOneStep() throws Exception {
//        IRepository repo = new Repository();
//        Controller ctrl = new Controller(repo);
//        Exp exp = new ConstExp(5);
//        IStmt st = new AssignStmt("a", exp);
//        ctrl.get
//
//    }

    public void testIsDebugFlag() throws Exception {
        IRepository repo = new Repository();
        Controller ctrl = new Controller(repo);
        assertEquals(false, ctrl.isDebugFlag());
    }
}