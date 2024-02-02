package assign06;

import java.util.NoSuchElementException;

/**
 * This interface specifies the behavior of a last-in-first-out (LIFO)
 * stack of elements.
 * 
 * @author Erin Parker, Michael Wadley, Adam Liu.
 * @version 10/18/22
 * 
 * @param <E> - the type of elements contained in the stack
 */
public class LinkedListStack <E> implements Stack<E> {
	
	public LinkedListStack() {list = new SinglyLinkedList<>();}
	
	// A private Instance variable holding the size of the LikedListStack.
	private int size = 0;
	// An generic instance of our SinglyLinkedList class as our Stack. 
	private SinglyLinkedList<E> list;

	/**
	 * Removes all of the elements from the stack.
	 */
	@Override
	public void clear() {
		list.clear();
	}

	/**
	 * @return true if the stack contains no elements; false, otherwise.
	 */
	@Override
	public boolean isEmpty() {
		if (list.size()==0)
			return true;
		return false;
	}

	/**
	 * Returns, but does not remove, the element at the top of the stack.
	 * 
	 * @return the element at the top of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */
	@Override
	public E peek() throws NoSuchElementException {
		if (list.isEmpty())
			throw new NoSuchElementException();
		return list.getFirst(); 
	}

	/**
	 * Returns and removes the item at the top of the stack.
	 * 
	 * @return the element at the top of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */
	@Override
	public E pop() throws NoSuchElementException {
		if (list.isEmpty())
			throw new NoSuchElementException();
		E popped = list.getFirst();
		list.deleteFirst();
		size--;
		return popped;
	}

	/**
	 * Adds a given element to the stack, putting it at the top of the stack.
	 * 
	 * @param element - the element to be added
	 */
	@Override
	public void push(E element) {
		list.insertFirst(element);
		size++;
	}

	/**
	 * @return the number of elements in the stack
	 */
	@Override
	public int size() {
		return size;
	}

}