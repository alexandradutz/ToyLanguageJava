package test;

import domain.DataStructures.ArrayDictionary;
import domain.DataStructures.ArrayList;
import domain.DataStructures.ArrayStack;
import domain.Expression.ConstExp;
import domain.Stmt.AssignStmt;
import domain.Stmt.IStmt;
import java.util.Random;

/**
 * Created by Dutzi on 10/27/2015.
 */
public class TestClass {
    public void TestStack()
    {
        ArrayStack stack = new ArrayStack();
        IStmt st = new AssignStmt("a", new ConstExp(5));

        stack.push(st);

        while (!stack.isEmpty()) {
            assert stack.pop().toStr().equals("a=5");
        }
        System.out.println("teste");
    }

    public void TestDictionary(){
        ArrayDictionary d = new ArrayDictionary();
        assert d.isEmpty();
        d.add("a", 5);
        assert d.isKey("a");

    }

    public void TestList(){
        ArrayList l = new ArrayList();
        assert l.isEmpty();
        l.add("Output");
        assert l.get(0).equals("Output");
    }

    public void run(){
        TestDictionary();
        TestList();
        TestStack();
    }
}
