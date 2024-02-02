package assign03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Comprehensive tests for the ArrayCollection class methods.
 *
 * @author Michael Wadley and Adam Liu
 * @version 9/13/22
 */
public class ArrayCollectionTest {

    private ArrayCollection<String> smallStringCollection = new ArrayCollection<>();
    private ArrayCollection<String> mediumStringCollection = new ArrayCollection<>();
    private ArrayCollection<Integer> smallIntegerCollection = new ArrayCollection<>();
    private ArrayCollection<Integer> mediumIntegerCollection = new ArrayCollection<>();
    private ArrayCollection<Integer> largeIntegerCollection = new ArrayCollection<>();

    @BeforeEach
    void setUp() {
        smallStringCollection.add("test1");
        smallStringCollection.add("test2");
        smallStringCollection.add("test3");

        mediumStringCollection.add("test1");
        mediumStringCollection.add("test2");
        mediumStringCollection.add("test3");
        mediumStringCollection.add("test4");
        mediumStringCollection.add("test5");
        mediumStringCollection.add("test6");

        smallIntegerCollection.add(1);
        smallIntegerCollection.add(2);
        smallIntegerCollection.add(3);

        mediumIntegerCollection.add(1);
        mediumIntegerCollection.add(2);
        mediumIntegerCollection.add(3);
        mediumIntegerCollection.add(4);
        mediumIntegerCollection.add(5);
        mediumIntegerCollection.add(6);

        largeIntegerCollection.add(1);
        largeIntegerCollection.add(2);
        largeIntegerCollection.add(3);
        largeIntegerCollection.add(4);
        largeIntegerCollection.add(5);
        largeIntegerCollection.add(6);
        largeIntegerCollection.add(7);
        largeIntegerCollection.add(8);
        largeIntegerCollection.add(9);
        largeIntegerCollection.add(10);

    }

    @Test
    public void testArrayCollectionAddOne() {
        assertEquals(3, smallStringCollection.size());
        assertTrue(smallStringCollection.add("Gates"));
        assertEquals(4, smallStringCollection.size());
        assertTrue(smallStringCollection.contains("test3"));
        assertTrue(smallStringCollection.contains("Gates"));

        assertEquals(3, smallIntegerCollection.size());
        assertTrue(smallIntegerCollection.add(6));
        assertEquals(4, smallIntegerCollection.size());
        assertTrue(smallIntegerCollection.contains(3));
        assertTrue(smallIntegerCollection.contains(6));
    }

    @Test
    public void testArrayCollectionAddMultiple() {
        assertEquals(3, smallStringCollection.size());
        assertTrue(smallStringCollection.add("Bill"));
        assertTrue(smallStringCollection.add("Gates"));
        assertTrue(smallStringCollection.add("swag"));
        assertEquals(6, smallStringCollection.size());
        assertTrue(smallStringCollection.contains("test3"));
        assertTrue(smallStringCollection.contains("Gates"));
        assertTrue(smallStringCollection.contains("swag"));

        assertEquals(3, smallIntegerCollection.size());
        assertTrue(smallIntegerCollection.add(21));
        assertTrue(smallIntegerCollection.add(72));
        assertTrue(smallIntegerCollection.add(69));
        assertEquals(6, smallIntegerCollection.size());
        assertTrue(smallIntegerCollection.contains(3));
        assertTrue(smallIntegerCollection.contains(69));
        assertTrue(smallIntegerCollection.contains(72));
    }

    @Test
    public void testAddAlreadyContainedObject() {
        assertEquals(3, smallStringCollection.size());
        assertFalse(smallStringCollection.add("test1"));
        assertEquals(3, smallStringCollection.size());
    }

    @Test
    public void testAddAllAlreadyContainedObject() {
        ArrayCollection<String> arrayCollectionString = new ArrayCollection<>();
        arrayCollectionString.add("test1");
        arrayCollectionString.add("test2");
        arrayCollectionString.add("test3");
        assertFalse(arrayCollectionString.addAll(smallStringCollection));
        assertEquals(3, arrayCollectionString.size());
    }

    @Test
    public void testAddAllSmall() {
        ArrayCollection<String> arrayCollectionString = new ArrayCollection<>();
        assertTrue(arrayCollectionString.addAll(smallStringCollection));
        assertEquals(3, smallStringCollection.size());
        assertEquals(3, arrayCollectionString.size());
        assertTrue(arrayCollectionString.contains("test1"));
        assertTrue(arrayCollectionString.contains("test3"));

        ArrayCollection<Integer> arrayCollectionInteger = new ArrayCollection<>();
        assertTrue(arrayCollectionInteger.addAll(smallIntegerCollection));
        assertEquals(3, smallIntegerCollection.size());
        assertEquals(3, arrayCollectionInteger.size());
        assertTrue(arrayCollectionInteger.contains(1));
        assertTrue(arrayCollectionInteger.contains(3));
    }

    @Test
    public void testAddAllMedium() {
        ArrayCollection<String> arrayCollectionString = new ArrayCollection<>();
        assertTrue(arrayCollectionString.addAll(mediumStringCollection));
        assertEquals(6, mediumStringCollection.size());
        assertEquals(6, arrayCollectionString.size());
        assertTrue(arrayCollectionString.contains("test1"));
        assertTrue(arrayCollectionString.contains("test3"));

        ArrayCollection<Integer> arrayCollectionInteger = new ArrayCollection<>();
        assertTrue(arrayCollectionInteger.addAll(mediumIntegerCollection));
        assertEquals(6, mediumIntegerCollection.size());
        assertEquals(6, arrayCollectionInteger.size());
        assertTrue(arrayCollectionInteger.contains(1));
        assertTrue(arrayCollectionInteger.contains(3));
    }

    @Test
    public void testGrow() {
        // Original Backing array size is 10, so if we add more items to an array already containing 10 items, we can
        // reasonably expect it to grow the backing array for us and increase the size of our array.
        assertEquals(10, largeIntegerCollection.size());
        largeIntegerCollection.add(11);
        largeIntegerCollection.add(12);
        assertEquals(12, largeIntegerCollection.size());
        assertTrue(largeIntegerCollection.contains(1));
        assertTrue(largeIntegerCollection.contains(12));
    }

    @Test
    public void testClear() {
        assertEquals(6, mediumStringCollection.size());
        mediumStringCollection.clear();
        assertEquals(0, mediumStringCollection.size());
    }

    @Test
    public void testContains() {
        assertTrue(smallStringCollection.contains("test1"));
        assertTrue(smallStringCollection.contains("test3"));

        assertTrue(smallIntegerCollection.contains(1));
        assertTrue(smallIntegerCollection.contains(3));
    }

    @Test
    public void testContainsFalse() {
        assertFalse(smallStringCollection.contains("test97"));

        assertFalse(smallIntegerCollection.contains(69));
    }

    @Test
    public void testContainsAll() {
        assertTrue(mediumStringCollection.containsAll(smallStringCollection));
        assertFalse(smallStringCollection.containsAll(mediumStringCollection));

        assertTrue(mediumIntegerCollection.containsAll(smallIntegerCollection));
        assertFalse(smallIntegerCollection.containsAll(mediumIntegerCollection));

        assertTrue(largeIntegerCollection.containsAll(smallIntegerCollection));
        assertTrue(largeIntegerCollection.containsAll(mediumIntegerCollection));
        assertFalse(smallIntegerCollection.containsAll(largeIntegerCollection));
    }

    @Test
    public void testIsEmpty() {
        ArrayCollection<String> arrayCollection = new ArrayCollection<>();
        assertTrue(arrayCollection.isEmpty());
        mediumStringCollection.clear();
        assertTrue(mediumStringCollection.isEmpty());
    }

    @Test
    public void testRemove() {
        assertEquals(6, mediumStringCollection.size());
        assertTrue(mediumStringCollection.contains("test1"));
        assertTrue(mediumStringCollection.remove("test1"));
        assertEquals(5, mediumStringCollection.size());
        assertFalse(mediumStringCollection.contains("test1"));

        assertEquals(6, mediumIntegerCollection.size());
        assertTrue(mediumIntegerCollection.contains(1));
        assertTrue(mediumIntegerCollection.remove(1));
        assertEquals(5, mediumIntegerCollection.size());
        assertFalse(mediumIntegerCollection.contains(1));
    }

    @Test
    public void testNonExistentRemove() {
        assertFalse(mediumStringCollection.remove("test1234"));
        assertEquals(6, mediumStringCollection.size());
    }

    @Test
    public void testRemoveAll() {
        assertTrue(mediumStringCollection.removeAll(smallStringCollection));
        assertEquals(3, mediumStringCollection.size());
        assertFalse(mediumStringCollection.contains("test1"));
        assertTrue(mediumStringCollection.contains("test6"));

        assertTrue(mediumIntegerCollection.removeAll(smallIntegerCollection));
        assertEquals(3, mediumIntegerCollection.size());
        assertFalse(mediumIntegerCollection.contains(1));
        assertTrue(mediumIntegerCollection.contains(6));
    }

    @Test
    public void testRemoveAllNone() {
        ArrayCollection<String> arrayCollectionEmpty = new ArrayCollection<>();
        assertFalse(mediumStringCollection.removeAll(arrayCollectionEmpty));
        assertEquals(6, mediumStringCollection.size());
        assertTrue(mediumStringCollection.contains("test1"));
        assertTrue(mediumStringCollection.contains("test6"));
    }

    @Test
    public void testRetainAll() {
        assertTrue(mediumStringCollection.retainAll(smallStringCollection));
        assertEquals(3, mediumStringCollection.size());
        assertTrue(mediumStringCollection.contains("test1"));
        assertFalse(mediumStringCollection.contains("test6"));

        assertTrue(mediumIntegerCollection.retainAll(smallIntegerCollection));
        assertEquals(3, mediumIntegerCollection.size());
        assertTrue(mediumIntegerCollection.contains(1));
        assertFalse(mediumIntegerCollection.contains(6));
    }

    @Test
    public void testRetainAllNone() {
        ArrayCollection<String> arrayCollectionEmpty = new ArrayCollection<>();
        arrayCollectionEmpty.addAll(mediumStringCollection);
        assertFalse(mediumStringCollection.retainAll(arrayCollectionEmpty));
        assertEquals(6, mediumStringCollection.size());
        assertTrue(mediumStringCollection.contains("test1"));
        assertTrue(mediumStringCollection.contains("test6"));
    }

    @Test
    public void testSize() {
        assertEquals(3, smallStringCollection.size());
        assertEquals(6, mediumStringCollection.size());

        assertEquals(3, smallIntegerCollection.size());
        assertEquals(6, mediumIntegerCollection.size());
        assertEquals(10, largeIntegerCollection.size());
    }

    @Test
    public void testToArray() {
        Object[] strings = mediumStringCollection.toArray();
        assertEquals(6, strings.length);
        assertEquals("test1", strings[0]);
        assertEquals("test3", strings[2]);
        assertEquals("test6", strings[5]);

        Object[] integers = mediumIntegerCollection.toArray();
        assertEquals(6, integers.length);
        assertEquals(1, integers[0]);
        assertEquals(3, integers[2]);
        assertEquals(6, integers[5]);
    }

    @Test
    public void testToSortedListSimple() {
        ArrayCollection<Integer> unorderedCollection = new ArrayCollection<>();
        unorderedCollection.add(5);
        unorderedCollection.add(1);
        unorderedCollection.add(8);
        IntegerComparator cmp = new IntegerComparator();
        ArrayList<Integer> integerList = unorderedCollection.toSortedList(cmp);
        assertEquals(Integer.valueOf(1), integerList.get(0));
        assertEquals(Integer.valueOf(5), integerList.get(1));
        assertEquals(Integer.valueOf(8), integerList.get(2));
    }

    @Test
    public void testToSortedListComplex() {
        ArrayCollection<Integer> unorderedCollection = new ArrayCollection<>();
        unorderedCollection.add(5);
        unorderedCollection.add(1);
        unorderedCollection.add(8);
        unorderedCollection.add(72);
        unorderedCollection.add(-1);
        unorderedCollection.add(-44);
        IntegerComparator cmp = new IntegerComparator();
        ArrayList<Integer> integerList = unorderedCollection.toSortedList(cmp);
        assertEquals(Integer.valueOf(-44), integerList.get(0));
        assertEquals(Integer.valueOf(-1), integerList.get(1));
        assertEquals(Integer.valueOf(72), integerList.get(5));
    }

    @Test
    public void testIterator() {
        Iterator<String> stringIterator = smallStringCollection.iterator();
        assertTrue(stringIterator.hasNext());
        assertTrue(smallStringCollection.contains(stringIterator.next()));
        assertTrue(smallStringCollection.contains(stringIterator.next()));
        assertEquals("test3", stringIterator.next());
        assertFalse(stringIterator.hasNext());

        Iterator<Integer> integerIterator = smallIntegerCollection.iterator();
        assertTrue(integerIterator.hasNext());
        assertTrue(smallIntegerCollection.contains(integerIterator.next()));
        assertTrue(smallIntegerCollection.contains(integerIterator.next()));
        assertEquals(Integer.valueOf(3), integerIterator.next());
        assertFalse(integerIterator.hasNext());
    }

    @Test
    public void testIteratorIllegalNext() {
        Iterator<String> stringIterator = smallStringCollection.iterator();
        while (stringIterator.hasNext()) {
            stringIterator.next();
        }
        assertThrows(NoSuchElementException.class, () -> stringIterator.next());
    }

    @Test
    public void testIteratorRemove() {
        Iterator<String> stringIterator = smallStringCollection.iterator();
        stringIterator.next();
        stringIterator.remove();
        assertEquals("test2", stringIterator.next());
        assertEquals(2, smallStringCollection.size());

        Iterator<Integer> integerIterator = smallIntegerCollection.iterator();
        integerIterator.next();
        integerIterator.remove();
        assertEquals(Integer.valueOf(2), integerIterator.next());
        assertEquals(2, smallIntegerCollection.size());
    }

    @Test
    public void testIteratorIllegalRemove() {
        Iterator<String> stringIterator = smallStringCollection.iterator();
        assertThrows(IllegalStateException.class, () -> stringIterator.remove());
    }

    @Test
    public void testBinarySearch() {
        ArrayList<Integer> simpleSorted = mediumIntegerCollection.toSortedList(new IntegerComparator());
        assertTrue(SearchUtil.binarySearch(simpleSorted, 3, new IntegerComparator()));
    }

    @Test
    public void testBinarySearchFalse() {
        ArrayList<Integer> simpleSorted = mediumIntegerCollection.toSortedList(new IntegerComparator());
        assertFalse(SearchUtil.binarySearch(simpleSorted, 10, new IntegerComparator()));
    }

}
