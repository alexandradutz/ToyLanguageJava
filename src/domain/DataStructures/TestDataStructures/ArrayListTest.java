package domain.DataStructures.TestDataStructures;

import domain.DataStructures.List.ArrayList;
import domain.DataStructures.List.IList;
import junit.framework.TestCase;

/**
 * Created by Dutzi on 11/2/2015.
 */
public class ArrayListTest extends TestCase {

    public void testAdd() throws Exception {
        IList l = new ArrayList();
        l.add("output");
        assertEquals("output", l.get(0));
    }


    public void testIsEmpty() throws Exception {
        IList l = new ArrayList();
        assertEquals(true, l.isEmpty());
    }

    public void testSize() throws Exception {
        IList l = new ArrayList();
        assertEquals(0, l.size());
        l.add("output");
        assertEquals(1, l.size());

    }

    public void testGet() throws Exception {
        IList l = new ArrayList();
        l.add("1");
        l.add("3");
        l.add("2");
        assertEquals("3", l.get(1));
    }

}