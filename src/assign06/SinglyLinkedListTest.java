package assign06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class SinglyLinkedListTest {

    SinglyLinkedList<Integer> largeIntList = new SinglyLinkedList<>();
    SinglyLinkedList<Integer> smallIntList = new SinglyLinkedList<>();
    SinglyLinkedList<String> stringList = new SinglyLinkedList<>();

    @BeforeEach
    public void setup() {
        for (int i = 0; i < 50; i++) {
            largeIntList.insertFirst(i);
        }

        for (int i = 0; i < 10; i++) {
            smallIntList.insertFirst(i);
        }

        for (int i = 0; i < 25; i++) {
            stringList.insertFirst("string " + i);
        }
    }

    @Test
    public void testInsertFirstInt() {
        largeIntList.insertFirst(-1);
        assertEquals(-1, largeIntList.get(0));
        assertEquals(51, largeIntList.size());
    }

    @Test
    public void testInsertFirstString() {
        stringList.insertFirst("String -1");
        assertEquals("String -1", stringList.get(0));
        assertEquals(26, stringList.size());
    }

    @Test
    public void testInsertFirstEmptyList() {
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
        list1.insertFirst(1);
        assertEquals(1, list1.get(0));
        assertEquals(1, list1.size());
    }

    @Test
    public void testInsertFirstSmallList() {
        smallIntList.insertFirst(222);
        assertEquals(222, smallIntList.get(0));
        assertEquals(11, smallIntList.size());
    }

    @Test
    public void testInsertLastInt() {
        largeIntList.insert(largeIntList.size(), 50);
        assertEquals(51, largeIntList.size());
    }

    @Test
    public void testInsertLastString() {
        stringList.insert(stringList.size(), "String 26");
        assertEquals("String 26", stringList.get(stringList.size() - 1));
    }

    @Test
    public void testInsertMiddleInt() {
        largeIntList.insert(largeIntList.size() / 2, 599);
        assertEquals(599, largeIntList.get(largeIntList.size() / 2));
    }

    @Test
    public void testInsertMiddleString() {
        stringList.insert(12, "Middle String");
        assertEquals("Middle String", stringList.get(12));
    }

    @Test
    public void testInsertAtBeginningInt() {
        smallIntList.insert(0, 222);
        assertEquals(222, smallIntList.get(0));
        assertEquals(11, smallIntList.size());
    }

    @Test
    public void testInsertAtIndex1Int() {
        smallIntList.insert(1, 222);
        assertEquals(222, smallIntList.get(1));
        assertEquals(11, smallIntList.size());
    }

    @Test
    public void testInsertOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> smallIntList.insert(12, 1));
    }

    @Test
    public void testGetFirstInt() {
        assertEquals(49, largeIntList.getFirst());
    }

    @Test
    public void testGetFirstString() {
        assertEquals("string 24", stringList.getFirst());
    }

    @Test
    public void testGetFirstNonExistent() {
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
        assertThrows(NoSuchElementException.class, () -> list1.getFirst());
    }

    @Test
    public void testGetOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> smallIntList.get(11));
    }

    @Test
    public void testGetLastInt() {
        assertEquals(0, largeIntList.get(largeIntList.size() - 1));
    }

    @Test
    public void testGetMiddleInt() {
        assertEquals(24, largeIntList.get(25));
    }

    @Test
    public void testGetBeginningInt() {
        assertEquals(49, largeIntList.get(0));
    }

    @Test
    public void testDeleteFirstInt() {
        largeIntList.deleteFirst();
        assertEquals(49, largeIntList.size());
        assertEquals(48, largeIntList.getFirst());
    }

    @Test
    public void testDeleteFirstString() {
        stringList.deleteFirst();
        assertEquals(24, stringList.size());
        assertEquals("string 23", stringList.getFirst());
    }

    @Test
    public void testDeleteFirstNonExistent() {
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
        assertThrows(NoSuchElementException.class, () -> list1.deleteFirst());
    }

    @Test
    public void testDeleteLastInt() {
        largeIntList.delete(largeIntList.size() - 1);
        assertEquals(1, largeIntList.get(48));
        assertEquals(49, largeIntList.size());
    }

    @Test
    public void testDeleteLastString() {
        stringList.delete(stringList.size() - 1);
        assertEquals("string 1", stringList.get(23));
        assertEquals(24, stringList.size());
    }

    @Test
    public void testDeleteOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> largeIntList.delete(50));
    }

    @Test
    public void testIndexOfFirstInt() {
        assertEquals(9, smallIntList.indexOf(0));
    }

    @Test
    public void testIndexOfLastInt() {
        assertEquals(0, smallIntList.indexOf(smallIntList.size() - 1));
    }

    @Test
    public void testIndexOfLastString() {
        assertEquals(24, stringList.indexOf(stringList.get(stringList.size() - 1)));
    }

    @Test
    public void testIndexOfNonExistent() {
        assertEquals(-1, smallIntList.indexOf(999));
    }

    @Test
    public void testIndexOfSameItems() {
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
        list1.insertFirst(1);
        list1.insertFirst(1);
        assertEquals(0, list1.indexOf(1));
    }

    @Test
    public void testSize() {
        assertEquals(10, smallIntList.size());
        assertEquals(50, largeIntList.size());
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
        assertEquals(0, list1.size());
    }

    @Test
    public void testIsEmpty() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertTrue(list.isEmpty());
        assertFalse(largeIntList.isEmpty());
    }

    @Test
    public void testClearInt() {
        largeIntList.clear();
        assertTrue(largeIntList.isEmpty());
    }

    @Test
    public void testClearString() {
        stringList.clear();
        assertTrue(stringList.isEmpty());
    }

    @Test
    public void testToArrayInt() {
        Integer[] arr = new Integer[smallIntList.size()];
        for (int i = 0; i < smallIntList.size(); i++) {
            arr[i] = smallIntList.get(i);
        }
        Object[] temp = smallIntList.toArray();
        for (int i = 0; i < arr.length; i++) {
            assertEquals(arr[i], temp[i]);
        }
    }

    @Test
    public void testToArrayString() {
        String[] arr = new String[stringList.size()];
        for (int i = 0; i < stringList.size(); i++) {
            arr[i] = stringList.get(i);
        }
        Object[] temp = stringList.toArray();
        for (int i = 0; i < arr.length; i++) {
            assertEquals(arr[i], temp[i]);
        }
    }

    @Test
    public void testIteratorHasNext() {
        Iterator<Integer> intIterator = smallIntList.iterator();
        assertTrue(intIterator.hasNext());
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        Iterator<String> stringIterator = list.iterator();
        assertFalse(stringIterator.hasNext());
    }

    @Test
    public void testIteratorGetNextNonExistent() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertThrows(NoSuchElementException.class, () -> list.iterator().next());
    }

    @Test
    public void testIteratorGetNext() {
        Iterator<Integer> intIterator = smallIntList.iterator();
        int index = 0;
        while (intIterator.hasNext()) {
            assertEquals(smallIntList.get(index), intIterator.next());
            index++;
        }
    }

    @Test
    public void testIteratorRemoveFirst() {
        Iterator<Integer> intIterator = smallIntList.iterator();
        int temp = intIterator.next();
        intIterator.remove();
        assertEquals(9, smallIntList.size());
        assertEquals(smallIntList.get(0), intIterator.next());
        assertEquals(7, intIterator.next());
    }

    @Test
    public void testIteratorRemoveMiddle() {
        Iterator<Integer> intIterator = smallIntList.iterator();
        intIterator.next();
        intIterator.next();
        intIterator.remove();
        assertEquals(9, smallIntList.size());
        assertEquals(smallIntList.get(1), intIterator.next());
        assertEquals(6, intIterator.next());
    }

    @Test
    public void testIteratorRemoveLast() {
        Iterator<Integer> intIterator = smallIntList.iterator();
        int temp = intIterator.next();
        while (intIterator.hasNext()) {
            temp = intIterator.next();
        }
        intIterator.remove();
        assertEquals(9, smallIntList.size());
        assertEquals(1, smallIntList.get(8));
    }

}
