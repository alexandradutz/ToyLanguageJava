package domain.DataStructures.TestDataStructures;

import domain.DataStructures.Stack.ArrayStack;
import domain.DataStructures.Stack.IStack;
import domain.Expression.ConstExp;
import domain.Expression.Exp;
import domain.Stmt.AssignStmt;
import domain.Stmt.IStmt;
import junit.framework.TestCase;

/**
 * Created by Dutzi on 11/2/2015.
 */
public class ArrayStackTest extends TestCase {

    public void testPush() throws Exception {
        IStack s =  new ArrayStack();
        Exp exp = new ConstExp(5);
        IStmt st = new AssignStmt("a", exp);
        s.push(st);
        assertEquals(st, s.pop());
    }

    public void testPop() throws Exception {
        IStack s =  new ArrayStack();
        Exp exp = new ConstExp(5);
        IStmt st = new AssignStmt("a", exp);
        s.push(st);
        IStmt st2;
        st2 = (IStmt) s.pop();
        assertEquals(st, st2);
    }

    public void testIsEmpty() throws Exception {

    }

    public void testTop() throws Exception {

    }

    public void testToStr() throws Exception {

    }
}