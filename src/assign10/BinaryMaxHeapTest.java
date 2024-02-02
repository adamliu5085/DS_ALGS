package assign10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryMaxHeapTest {

    BinaryMaxHeap<Integer> largeIntHeap = new BinaryMaxHeap<>();

    ArrayList<Integer> largeIntList = new ArrayList<>();

    BinaryMaxHeap<String> smallStringHeap = new BinaryMaxHeap<>();

    ArrayList<String> smallStringList = new ArrayList<>();

    BinaryMaxHeap<Integer> largeRandomIntHeap = new BinaryMaxHeap<>();

    ArrayList<Integer> largeRandomIntList;

    @BeforeEach
    public void setup() {
        for (int i = 0; i <= 49; i++) {
            largeIntHeap.add(i);
            largeIntList.add(i);
        }

        for (int i = 0; i <= 9; i++) {
            smallStringHeap.add("string" + i);
            smallStringList.add("string" + i);
        }

        largeRandomIntList = new ArrayList<>(largeIntList);
        Collections.shuffle(largeRandomIntList);
        for (int i = 0; i < largeRandomIntList.size(); i++) {
            largeRandomIntHeap.add(largeRandomIntList.get(i));
        }
    }

    // Start Constructor / Buildheap Tests

    @Test
    public void testConstructLargeIntegerHeapUsingList() {
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>(largeIntList);
        for (int i = 49; i >= 0; i--) {
            assertEquals(i + 1, heap.size());
            assertEquals(largeIntList.get(i), heap.extractMax());
        }
    }

    @Test
    public void testConstructLargeIntegerHeapUsingListComparator() {
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>(largeIntList, Comparator.naturalOrder());
        for (int i = 49; i >= 0; i--) {
            assertEquals(i + 1, heap.size());
            assertEquals(largeIntList.get(i), heap.extractMax());
        }
    }

    @Test
    public void testConstructSmallStringHeapUsingList() {
        BinaryMaxHeap<String> heap = new BinaryMaxHeap<>(smallStringList);
        for (int i = 9; i >= 0; i--) {
            assertEquals(i + 1, heap.size());
            assertEquals(smallStringList.get(i), heap.extractMax());
        }
    }

    @Test
    public void testConstructSmallStringHeapUsingListComparator() {
        BinaryMaxHeap<String> heap = new BinaryMaxHeap<>(smallStringList, Comparator.naturalOrder());
        for (int i = 9; i >= 0; i--) {
            assertEquals(i + 1, heap.size());
            assertEquals(smallStringList.get(i), heap.extractMax());
        }
    }

    @Test
    public void testConstructEmpty() {
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
        assertEquals(0, heap.size());
    }

    @Test
    public void testConstructEmptyComparator() {
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>(Comparator.naturalOrder());
        assertEquals(0, heap.size());
    }

    // End Constructor / Buildheap Tests

    // Start Add Tests

    @Test
    public void testAddIntHeap() {
        largeIntHeap.add(50);
        largeIntList.add(50);
        for (int i = 50; i >= 0; i--) {
            assertEquals(i + 1, largeIntHeap.size());
            assertEquals(largeIntList.get(i), largeIntHeap.extractMax());
        }
    }

    @Test
    public void testAddStringHeap() {
        smallStringHeap.add("string99");
        smallStringList.add("string99");
        for (int i = 10; i >= 0; i--) {
            assertEquals(i + 1, smallStringHeap.size());
            assertEquals(smallStringList.get(i), smallStringHeap.extractMax());
        }
    }

    @Test
    public void testAddRandom() {
        largeRandomIntHeap.add(99);
        largeIntList.add(99);
        for (int i = 50; i >= 0; i--) {
            assertEquals(i + 1, largeRandomIntHeap.size());
            assertEquals(largeIntList.get(i), largeRandomIntHeap.extractMax());
        }
    }

    @Test
    public void addToEmpty() {
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
        assertEquals(0, heap.size());
        heap.add(1);
        assertEquals(1, heap.size());
        heap.add(2);
        assertEquals(2, heap.size());
        assertEquals(2, heap.extractMax());
        assertEquals(1, heap.extractMax());
    }

    // End Add Tests


    // Start Peek Tests

    @Test
    public void testPeekEmptyHeap() {
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
        assertThrows(NoSuchElementException.class, () -> heap.peek());
    }

    @Test
    public void testPeekIntHeap() {
        assertEquals(50, largeIntHeap.size());
        assertEquals(49, largeIntHeap.peek());
        assertEquals(50, largeIntHeap.size());
        assertEquals(49, largeIntHeap.peek());
    }

    @Test
    public void testPeekStringHeap() {
        assertEquals(10, smallStringHeap.size());
        assertEquals("string9", smallStringHeap.peek());
        assertEquals(10, smallStringHeap.size());
        assertEquals("string9", smallStringHeap.peek());
    }

    // End Peek Tests

    // Start extractMax Tests

    @Test
    public void testExtractMaxEmptyHeap() {
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
        assertThrows(NoSuchElementException.class, () -> heap.extractMax());
    }

    @Test
    public void testExtractMaxIntHeap() {
        for (int i = 49; i >= 0; i--) {
            assertEquals(i + 1, largeIntHeap.size());
            assertEquals(largeIntList.get(i), largeIntHeap.extractMax());
        }
    }

    @Test
    public void testExtractMaxStringHeap() {
        for (int i = 9; i >= 0; i--) {
            assertEquals(i + 1, smallStringHeap.size());
            assertEquals(smallStringList.get(i), smallStringHeap.extractMax());
        }
    }

    @Test
    public void testExtractMaxRandom() {
        for (int i = 49; i >= 0; i--) {
            assertEquals(i + 1, largeRandomIntHeap.size());
            assertEquals(largeIntList.get(i), largeRandomIntHeap.extractMax());
        }
    }

    // End ExtractMax Tests

    // Start Size Tests

    @Test
    public void testSizeZero() {
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
        assertEquals(0, heap.size());
        assertTrue(heap.isEmpty());

        heap.add(1);
        assertEquals(1, heap.size());
    }

    @Test
    public void testSize() {
        assertEquals(50, largeIntHeap.size());
        largeIntHeap.add(100);
        assertEquals(51, largeIntHeap.size());
        largeIntHeap.extractMax();
        assertEquals(50, largeIntHeap.size());

        assertEquals(10, smallStringHeap.size());
        smallStringHeap.add("abc");
        assertEquals(11, smallStringHeap.size());
        smallStringHeap.extractMax();
        assertEquals(10, smallStringHeap.size());

        assertEquals(50, largeRandomIntHeap.size());
        largeRandomIntHeap.add(100);
        assertEquals(51, largeRandomIntHeap.size());
        largeRandomIntHeap.extractMax();
        assertEquals(50, largeRandomIntHeap.size());

    }

    // End Size Tests

    // Start isEmpty Tests

    @Test
    public void testisEmpty() {
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
        assertEquals(0, heap.size());
        assertTrue(heap.isEmpty());

        largeIntHeap.clear();
        assertTrue(largeIntHeap.isEmpty());
    }

    @Test
    public void testNotEmptyIntHeap() {
        assertEquals(50, largeIntHeap.size());
        assertFalse(largeIntHeap.isEmpty());
    }

    @Test
    public void testNotEmptyStringHeap() {
        assertEquals(10, smallStringHeap.size());
        assertFalse(smallStringHeap.isEmpty());
    }

    // End isEmpty Tests


    // Start Clear Tests

    @Test
    public void testClearIntHeap() {
        assertEquals(50, largeIntHeap.size());
        largeIntHeap.clear();
        assertTrue(largeIntHeap.isEmpty());
        assertEquals(0, largeIntHeap.size());
        assertThrows(NoSuchElementException.class, () -> largeIntHeap.extractMax());
    }

    @Test
    public void testClearStringHeap() {
        assertEquals(10, smallStringHeap.size());
        smallStringHeap.clear();
        assertTrue(smallStringHeap.isEmpty());
        assertEquals(0, smallStringHeap.size());
        assertThrows(NoSuchElementException.class, () -> smallStringHeap.extractMax());
    }

    // End Clear Tests

    // Start ToArray Tests

    @Test
    public void testIntHeapToArray() {
        Object[] array = largeIntHeap.toArray();
        assertEquals(array[0], 49);
        List<Object> intList = Arrays.asList(array);
        for (int i = 0; i < array.length; i++) {
            assertTrue(intList.contains(largeIntList.get(i)));
        }
    }

    @Test
    public void testStringHeapToArray() {
        Object[] array = smallStringHeap.toArray();
        assertEquals(array[0], "string9");
        List<Object> strList = Arrays.asList(array);
        for (int i = 0; i < array.length; i++) {
            assertTrue(strList.contains(smallStringList.get(i)));
        }
    }

    // End toArray Tests

}
