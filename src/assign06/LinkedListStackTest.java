package assign06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListStackTest {

    LinkedListStack<Integer> largeIntList = new LinkedListStack<>();
    LinkedListStack<Integer> smallIntList = new LinkedListStack<>();
    LinkedListStack<String> stringList = new LinkedListStack<>();

    @BeforeEach
    public void setup() {
        for (int i = 0; i < 50; i++) {
            largeIntList.push(i);
        }

        for (int i = 0; i < 10; i++) {
            smallIntList.push(i);
        }

        for (int i = 0; i < 25; i++) {
            stringList.push("string " + i);
        }
    }

    @Test
    public void testInsertFirstInt() {
        largeIntList.push(-1);
        assertEquals(-1, largeIntList.peek());
        assertEquals(51, largeIntList.size());
    }

    @Test
    public void testInsertFirstString() {
        stringList.push("String -1");
        assertEquals("String -1", stringList.peek());
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
        smallIntList.push(222);
        assertEquals(222, smallIntList.peek());
        assertEquals(11, smallIntList.size());
    }

   
    @Test
    public void testInsertAtBeginningInt() {
        smallIntList.push(222);
        assertEquals(222, smallIntList.peek());
        assertEquals(11, smallIntList.size());
    }

    @Test
    public void testGetFirstInt() {
        assertEquals(49, largeIntList.peek());
    }

    @Test
    public void testGetFirstString() {
        assertEquals("string 24", stringList.peek());
    }

    @Test
    public void testGetFirstNonExistent() {
        LinkedListStack<Integer> list1 = new LinkedListStack<>();
        assertThrows(NoSuchElementException.class, () -> list1.peek());
    }

    @Test
    public void testGetBeginningInt() {
        assertEquals(49, largeIntList.peek());
    }

    @Test
    public void testPopFirstInt() {
        largeIntList.pop();
        assertEquals(49, largeIntList.size());
        assertEquals(48, largeIntList.peek());
    }

    @Test
    public void testPopFirstString() {
        stringList.pop();
        assertEquals(24, stringList.size());
        assertEquals("string 23", stringList.peek());
    }

    @Test
    public void testDeleteFirstNonExistent() {
        LinkedListStack<Integer> list1 = new LinkedListStack<>();
        assertThrows(NoSuchElementException.class, () -> list1.pop());
    }

    @Test
    public void testSize() {
        assertEquals(10, smallIntList.size());
        assertEquals(50, largeIntList.size());
        LinkedListStack<Integer> list1 = new LinkedListStack<>();
        assertEquals(0, list1.size());
    }

    @Test
    public void testIsEmpty() {
        LinkedListStack<Integer> list = new LinkedListStack<>();
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
    
}
