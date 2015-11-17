package domain.DataStructures.TestDataStructures;

import domain.DataStructures.Dictionary.ArrayDictionary;
import domain.DataStructures.Dictionary.IDictionary;
import junit.framework.TestCase;

/**
 * Created by Dutzi on 11/2/2015.
 */
public class ArrayDictionaryTest extends TestCase {

    public void testIsEmpty() throws Exception {
        IDictionary d = new ArrayDictionary();
        assertEquals(0, d.size());
        assertEquals(true, d.isEmpty());
        d.add("a", 1);
        assertEquals(1, d.size());
        assertEquals(false, d.isEmpty());

    }

    public void testAdd() throws Exception {
        IDictionary d = new ArrayDictionary();
        d.add("a", 5);
        assertEquals(5, d.getValue("a"));
    }


    public void testSize() throws Exception {
        IDictionary d = new ArrayDictionary();
        assertEquals(0, d.size());
        d.add("a", 1);
        assertEquals(1, d.size());
    }

    public void testIsKey() throws Exception {
        IDictionary d = new ArrayDictionary();
        assertEquals(false, d.isKey("a"));
        d.add("a", 1);
        assertEquals(true, d.isKey("a"));
    }

    public void testModify() throws Exception {
        IDictionary d = new ArrayDictionary();
        d.add("a", 1);
        assertEquals(5, d.getValue("a"));
    }
}