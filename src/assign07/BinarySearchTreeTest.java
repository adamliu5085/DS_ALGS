package assign07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeTest {

    // Start Setup

    BinarySearchTree<String> stringBST = new BinarySearchTree<>();

    BinarySearchTree<Integer> intBST = new BinarySearchTree<>();


    @BeforeEach
    public void setup() {
        stringBST.clear();
        intBST.clear();

        stringBST.add("dog");
        stringBST.add("cow");
        stringBST.add("dragon");
        stringBST.add("chupacabra");
        stringBST.add("pelican");
        stringBST.add("zebra");
        stringBST.add("alpaca");
        stringBST.add("cat");

        for (int i = 1; i < 21; i++) {
            intBST.add(i);
        }
    }

    // End Setup

    // Start Add Tests

    @Test
    public void testAddNewString() {
        stringBST.writeDot("dotFile");
        assertTrue(stringBST.add("fish"));
        assertTrue(stringBST.contains("fish"));
        assertEquals(9, stringBST.size());
    }

    @Test
    public void testAddInt() {
        assertTrue(intBST.add(55));
        assertTrue(intBST.contains(55));
        assertEquals(21, intBST.size());
    }

    @Test
    public void testAddDuplicate() {
        assertTrue(intBST.add(55));
        assertFalse(intBST.add(55));
        assertTrue(intBST.contains(55));
    }

    @Test
    public void testAddInDepth() {
        // Before Each Sets up stringBST.
        ArrayList<String> list = stringBST.toArrayList();
        assertEquals("alpaca", list.get(0));
        assertEquals("cat", list.get(1));
        assertEquals("chupacabra", list.get(2));
        assertEquals("cow", list.get(3));
        assertEquals("dog", list.get(4));
        assertEquals("dragon", list.get(5));
        assertEquals("pelican", list.get(6));
        assertEquals("zebra", list.get(7));
    }

    // End Add Tests

    // Start AddAll Tests

    @Test
    public void testAddAllTnt() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(67);
        list.add(68);
        list.add(69);
        assertTrue(intBST.addAll(list));
        assertTrue(intBST.containsAll(list));
        assertEquals(23, intBST.size());
    }

    @Test
    public void testAddAllString() {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        assertTrue(stringBST.addAll(list));
        assertTrue(stringBST.containsAll(list));
        assertEquals(11, stringBST.size());
    }

    // End AddAll Tests

    // Start Clear Test

    @Test
    public void testClearIntAndString() {
        stringBST.clear();
        intBST.clear();
        assertEquals(stringBST.size(), intBST.size());
        assertTrue(stringBST.isEmpty());
        assertTrue(intBST.isEmpty());
    }

    // End Clear Test

    // Start Contains Test

    @Test
    public void testContains() {
        assertTrue(stringBST.contains("alpaca"));
        assertTrue(stringBST.contains("dragon"));
        assertTrue(stringBST.contains("zebra"));

        assertTrue(intBST.contains(1));
        assertTrue(intBST.contains(10));
        assertTrue(intBST.contains(20));
    }

    // End Contains Test

    // Start ContainsAll Test

    @Test
    public void testContainsAll() {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        assertTrue(stringBST.addAll(list));
        assertTrue(stringBST.containsAll(list));
    }

    // End ContainsAll Test

    // Start First Tests

    @Test
    public void testFirstString() {
        assertEquals("alpaca", stringBST.first());
    }

    @Test
    public void testFirstInt() {
        assertEquals(1, intBST.first());
    }

    @Test
    public void testEmptyFirst() {
        BinarySearchTree<String> bST = new BinarySearchTree<>();
        assertThrows(NoSuchElementException.class, () -> bST.first());
    }

    // End First Tests

    // Start isEmpty Test

    @Test
    public void testIsEmpty() {
        BinarySearchTree<Integer> bST = new BinarySearchTree<>();
        assertTrue(bST.isEmpty());
        assertEquals(0, bST.size());
    }

    // End isEmpty Test

    // Start Last Tests

    @Test
    public void testLastString() {
        assertEquals("zebra", stringBST.last());
    }

    @Test
    public void testLastInt() {
        assertEquals(20, intBST.last());
    }

    @Test
    public void testLastEmpty() {
        BinarySearchTree<String> bST = new BinarySearchTree<>();
        assertThrows(NoSuchElementException.class, () -> bST.last());
    }

    // End Last Tests

    // Start Remove Tests

    @Test
    public void testRemoveString() {
        assertTrue(stringBST.contains("alpaca"));
        assertTrue(stringBST.remove("alpaca"));
        assertFalse(stringBST.contains("alpaca"));
        assertEquals(7, stringBST.size());

        assertTrue(stringBST.contains("dragon"));
        assertTrue(stringBST.remove("dragon"));
        assertFalse(stringBST.contains("dragon"));
        assertEquals(6, stringBST.size());

        assertTrue(stringBST.contains("zebra"));
        assertTrue(stringBST.remove("zebra"));
        assertFalse(stringBST.contains("zebra"));
        assertEquals(5, stringBST.size());
    }

    @Test
    public void testRemoveInt() {
        assertTrue(intBST.contains(1));
        assertTrue(intBST.remove(1));
        assertFalse(intBST.contains(1));
        assertEquals(19, intBST.size());

        assertTrue(intBST.contains(10));
        assertTrue(intBST.remove(10));
        assertFalse(intBST.contains(10));
        assertEquals(18, intBST.size());

        assertTrue(intBST.contains(20));
        assertTrue(intBST.remove(20));
        assertFalse(intBST.contains(20));
        assertEquals(17, intBST.size());
    }

    @Test
    public void testRemoveOnlyItem() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(1);
        assertTrue(tree.remove(1));
        assertTrue(tree.isEmpty());
    }

    @Test
    public void testRemoveFalse() {
        assertFalse(intBST.remove(99));
        assertEquals(20, intBST.size());
    }

    // End Remove Tests

    // Start RemoveAll Tests

    @Test
    public void testRemoveAllStrings() {
        ArrayList<String> list = new ArrayList<>();
        list.add("zebra");
        list.add("alpaca");
        list.add("dragon");

        assertTrue(stringBST.containsAll(list));
        assertTrue(stringBST.removeAll(list));
        assertFalse(stringBST.containsAll(list));

        assertEquals(5, stringBST.size());
    }

    @Test
    public void testRemoveAllInt() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(intBST.containsAll(list));
        assertTrue(intBST.removeAll(list));
        assertFalse(intBST.containsAll(list));

        assertEquals(17, intBST.size());
    }

    // End RemoveAll Tests

    // Start Size Test

    @Test
    public void testSizeStringAndInt() {
        assertEquals(8, stringBST.size());
        assertEquals(20, intBST.size());
    }

    // End Size Test

    // Start ToArrayList Test

    @Test
    public void testToArrayList() {
        ArrayList<String> list = stringBST.toArrayList();
        assertEquals("alpaca", list.get(0));
        assertEquals("cat", list.get(1));
        assertEquals("chupacabra", list.get(2));
        assertEquals("cow", list.get(3));
        assertEquals("dog", list.get(4));
        assertEquals("dragon", list.get(5));
        assertEquals("pelican", list.get(6));
        assertEquals("zebra", list.get(7));
    }

    // End ToArrayList Test
}