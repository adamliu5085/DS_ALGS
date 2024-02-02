package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * A generic Singly-Linked-List class containing methods to create and perform operations on a linked list.
 *
 * @author Michael Wadley & Adam Liu
 * @version 10/18/22
 * @param <E> The type of data to be stored in the list.
 */
public class SinglyLinkedList <E> implements List<E> {

/**
 * A nested Node class containing a Node constructor, the nodes data, and next node.
 *
 * @author Michael Wadley & Adam Liu
 * @version 10/18/22
*/
    protected class Node {

        private E item;
        private Node next;

    /**
     * A Node constructor that sets the item instance variable to the item parameter, and sets the next variable to null.
     *
     * @param item The first item you would like to add to the list.
     */
        protected Node(E item) {
            this.item = item;
            next = null;
        }
    }

    private Node head = null;
    private int size = 0;

    public SinglyLinkedList() {
    }

	/**
	 * Inserts an element at the beginning of the list.
	 * O(1) for a singly-linked list.
	 *
	 * @param element - the element to add
	 */
    @Override
    public void insertFirst(E element) {
        if (size == 0) {
            head = new Node(element);
        }
        else {
            Node node = new Node(element);
            node.next = head;
            head = node;
        }
        size++;
    }

	/**
	 * Inserts an element at a specific position in the list.
	 * O(N) for a singly-linked list.
	 *
	 * @param index - the specified position
	 * @param element - the element to add
	 * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
	 */
    @Override
    public void insert(int index, E element) throws IndexOutOfBoundsException {
        if (index == 0) {
            insertFirst(element);
            return;
        }
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException();
        Node temp = head;
        for (int i = 1; i < index; i++){
             temp = temp.next;
        }
        Node newNode = new Node(element);
        newNode.next = temp.next;
        temp.next = newNode;
        size++;
    }

    /**
     * Gets the first element in the list.
     * O(1) for a singly-linked list.
     *
     * @return the first element in the list
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public E getFirst() throws NoSuchElementException {
        if (head == null)
            throw new NoSuchElementException();
        return head.item;
    }


    /**
     * Gets the element at a specific position in the list.
     * O(N) for a singly-linked list.
     *
     * @param index - the specified position
     * @return the element at the position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException();
        if (index == 0)
            return getFirst();
        Node temp = head;
        for (int i = 1; i <= index; i++) {
            temp = temp.next;
        }
        return temp.item;
    }

    /**
     * Deletes and returns the first element from the list.
     * O(1) for a singly-linked list.
     *
     * @return the first element
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public E deleteFirst() throws NoSuchElementException {
        if (head == null)
            throw new NoSuchElementException();
		E toReturn = head.item;
        head = head.next;
        size--;
        return toReturn;
    }

    /**
     * Deletes and returns the element at a specific position in the list.
     * O(N) for a singly-linked list.
     *
     * @param index - the specified position
     * @return the element at the position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
     */
    @Override
    public E delete(int index) throws IndexOutOfBoundsException {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException();
        if (index == 0)
            return deleteFirst();
        Node temp = head;
        for (int i = 1; i < index; i++){
            temp = temp.next;
        }
        E toReturn = temp.next.item;
        temp.next = temp.next.next;
        size--;
        return toReturn;
    }

	/**
	 * Determines the index of the first occurrence of the specified element in the list,
	 * or -1 if this list does not contain the element.
	 * O(N) for a singly-linked list.
	 *
	 * @param element - the element to search for
	 * @return the index of the first occurrence; -1 if the element is not found
	 */
    @Override
    public int indexOf(E element) {
        Iterator<E> iterator = new SinglyLinkedListIterator();
        int index = 0;
        boolean flag = false;
        while (iterator.hasNext()) {
            if (iterator.next() == element) {
                flag = true;
                break;
            }
            index++;
        }
        if (!flag)
            return -1;
        return index;
    }

	/**
	 * O(1) for a singly-linked list.
	 *
	 * @return the number of elements in this list
	 */
    @Override
    public int size() {
        return size;
    }

	/**
	 * O(1) for a singly-linked list.
	 *
	 * @return true if this collection contains no elements; false, otherwise
	 */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

	/**
	 * Removes all of the elements from this list.
	 * O(1) for a singly-linked list.
	 */
    public void clear() {
        size = 0;
        head = null;
    }

	/**
	 * Generates an array containing all of the elements in this list in proper sequence
	 * (from first element to last element).
	 * O(N) for a singly-linked list.
	 *
	 * @return an array containing all of the elements in this list, in order
	 */
    @Override
    public Object[] toArray() {
        Iterator<E> iterator = new SinglyLinkedListIterator();
        Object[] arr = new Object[size];
        int index = 0;
        while (iterator.hasNext())
            arr[index++] = iterator.next();
        return arr;
    }

	/**
     * Creates and returns an iterator for the SinglyLinkedList class.
     *
	 * @return an iterator over the elements in this list in proper sequence (from first
	 * element to last element)
	 */
    @Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator();
    }

    /**
     * An iterator specific to the SinglyLinkedList class.
     *
     * @author Michael Wadley & Adam Liu
     * @version 10/18/22
     */
    protected class SinglyLinkedListIterator implements Iterator<E> {
        protected int index = 0;
        protected boolean canRemove = false;

        protected Node currentNode;

        protected Node nodeBeforeCurrentNode;

        public SinglyLinkedListIterator() {
        }

        /**
         * An Iterator method that returns true if there is a following item, false otherwise.
         *
         * @return true if there is a following item, false otherwise.
         */
        @Override
        public boolean hasNext() {
            return index < size;
        }

        /**
         * An iterator method to return the next unseen item in the list.
         *
         * @return The next item from the list.
         * @throws NoSuchElementException If there are no more items to return from the list.
         */
        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext())
                throw new NoSuchElementException();
            canRemove = true;
            if (index == 0) {
                currentNode = head;
            } else {
                Node temp = head;
                for (int i = 1; i < index; i++) {
                    temp = temp.next;
                }
                nodeBeforeCurrentNode = temp;
                currentNode = temp.next;
            }
            index++;
            return currentNode.item;
        }

        /**
         * An iterator method that removes the item that was last returned from next.
         *
         * @throws IllegalStateException if next has not been called since the last time remove was called.
         */
        @Override
        public void remove() throws IllegalStateException {
            if (!canRemove) {
                throw new IllegalStateException();
            }
            if (size == 1) {
                clear();
                return;
            }
            if (index == 1){
            	head = head.next;
            }
            else {
                nodeBeforeCurrentNode.next = nodeBeforeCurrentNode.next.next;
            }
            index--;
            size--;
            canRemove = false;
        }
    }

}

