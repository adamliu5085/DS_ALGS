package assign10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class represents a binary max heap backed by an implicit tree.
 * Supports operations for constructing a heap from a given list, adding items, removing the maximum, creating an array representation, and more.
 *
 * @param <E> The generic type of your choosing.
 * @author Michael Wadley & Adam Liu
 * @version November 22, 2022
 */
public class BinaryMaxHeap<E> implements PriorityQueue<E> {

    private E[] heap;

    private int size;

    private Comparator<? super E> comparator = null;

    /**
     * Constructs an empty BinaryMaxHeap.
     * Assumes that the generic item type, E, implements Comparable.
     */
    @SuppressWarnings("unchecked")
    public BinaryMaxHeap() {
        heap = (E[]) new Object[10];
        size = 0;
    }

    /**
     * Constructs an empty BinaryMaxHeap and saves the given comparator for future utilization.
     *
     * @param cmp A comparator for the generic type E.
     */
    @SuppressWarnings("unchecked")
    public BinaryMaxHeap(Comparator<? super E> cmp) {
        heap = (E[]) new Object[10];
        size = 0;
        comparator = cmp;
    }

    /**
     * Constructs a BinaryMaxHeap containing the items in the given parameter list.
     * Assumes that the generic item type, E, implements Comparable.
     *
     * @param list A list of items
     */
    @SuppressWarnings("unchecked")
    public BinaryMaxHeap(List<? extends E> list) {
        heap = (E[]) new Object[list.size()];
        size = list.size();
        buildHeap(list);
    }

    /**
     * Constructs a BinaryMaxHeap containing the items in the given list parameter and saves the given comparator for future utilization.
     *
     * @param list A list of items
     * @param cmp  A comparator for the generic type E.
     */
    @SuppressWarnings("unchecked")
    public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp) {
        heap = (E[]) new Object[list.size()];
        size = list.size();
        comparator = cmp;
        buildHeap(list);
    }


    /**
     * Adds the given item to this heap.
     * O(1) in the average case, O(log N) in the worst case
     *
     * @param item the item to add to the heap.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void add(E item) {
        // If there is no room to add the item, we double the size of the backing array.
        if (size == heap.length) {
            E[] newHeap = (E[]) new Object[heap.length * 2];
            for (int i = 0; i < size; i++) {
                newHeap[i] = heap[i];
            }
            heap = newHeap;
        }
        heap[size] = item;
        size++;
        percolateUp(size - 1);
    }

    /**
     * Returns, but does not remove, the maximum item this heap.
     * O(1)
     *
     * @return The maximum item
     * @throws NoSuchElementException if this heap is empty
     */
    @Override
    public E peek() throws NoSuchElementException {
        if (size == 0)
            throw new NoSuchElementException();
        return heap[0];
    }

    /**
     * Returns and removes the maximum item this heap.
     * O(log N)
     *
     * @return The maximum item
     * @throws NoSuchElementException if this heap is empty
     */
    @Override
    public E extractMax() throws NoSuchElementException {
        if (size == 0)
            throw new NoSuchElementException();
        E max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        if (size > 1)
            percolateDown(0);
        return max;
    }

    /**
     * Returns the number of items in this heap.
     * O(1)
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns true if this heap is empty, false otherwise.
     * O(1)
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Empties this heap of items.
     * O(1)
     */
    @Override
    public void clear() {
        size = 0;
    }

    /**
     * Creates and returns an array of the items in this heap,
     * in the same order they appear in the backing array.
     * O(N)
     *
     * @return An array of the items in the max heap in the same order they appear in the backing array.
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        for (int i = 0; i < size; i++) {
            array[i] = heap[i];
        }
        return array;
    }

    /**
     * Compares two given E items depending on whether a comparator was provided.
     * If a comparator was provided during construction, the comparator is used to compare the items.
     * Otherwise, the items are compared using Comparable.
     *
     * @param item1 The first item to compare.
     * @param item2 The second item to compare.
     * @return The comparison result between the two items.
     */
    @SuppressWarnings("unchecked")
    private int innerCompare(E item1, E item2) {
        if (comparator != null)
            return comparator.compare(item1, item2);
        return ((Comparable<? super E>) item1).compareTo(item2);
    }

    /**
     * Adds values to a heap from a list of given values and ensures adherence to structure and order properties.
     *
     * @param list a list of values to add into the heap.
     */
    private void buildHeap(List<? extends E> list) {
        for (int i = 0; i < list.size(); i++) {
            heap[i] = list.get(i);
        }
        int currentIndex = (size / 2) - 1;
        // Percolate down starting from the last non-leaf node, working backwards until the root node has been percolated.
        while (currentIndex >= 0) {
            percolateDown(currentIndex--);
        }
    }

    /**
     * Maintains the heap order by swapping values the parent value is less than its child value.
     *
     * @param currentIndex the current index of the newly added value
     */
    private void percolateUp(int currentIndex) {
        int parent = findParent(currentIndex);
        // If the current item is greater than its parent, we swap, then percolate up further as necessary.
        while (currentIndex >= 1 && innerCompare(heap[currentIndex], heap[parent]) > 0) {
            swap(currentIndex, parent);
            currentIndex = parent;
            parent = findParent(currentIndex);
        }
    }

    /**
     * Maintains the heap order property by swapping the parent value with its greater child's value, if the parent value is less than the child.
     *
     * @param currentIndex The index of the item to percolate down.
     */
    private void percolateDown(int currentIndex) {
        int left = findLeftChild(currentIndex);
        int right = findRightChild(currentIndex);
        int biggest = currentIndex;
        // If the left child is within the valid range of indices
        if (left < size) {
            // If the left child is greater than its parent, it is the biggest so far.
            if (innerCompare(heap[left], heap[biggest]) > 0)
                biggest = left;
            // If the right child is also within the valid index range, and it is greater than the biggest so far, it is the new biggest.
            if (right < size && innerCompare(heap[right], heap[biggest]) > 0)
                biggest = right;
        }
        // If our current index is not the largest value out of itself and its children, we swap with the greater child and percolate down further as necessary.
        if (currentIndex != biggest) {
            swap(currentIndex, biggest);
            percolateDown(biggest);
        }
    }

    /**
     * Returns the leftChild index of a given index in the implicit tree.
     *
     * @param index The parent index of the leftChild.
     * @return The index of the leftChild.
     */
    private int findLeftChild(int index) {
        return (2 * index) + 1;
    }

    /**
     * Returns the rightChild index of a given index in the implicit tree.
     *
     * @param index The parent index of the rightChild.
     * @return The index of the rightChild.
     */
    private int findRightChild(int index) {
        return (2 * index) + 2;
    }

    /**
     * Returns the parent index of a given index in the implicit tree.
     *
     * @param index The index to find the parent of.
     * @return The index of the parent.
     */
    private int findParent(int index) {
        return (index - 1) / 2;
    }

    /**
     * Swaps two items in the backing array using the given indices.
     *
     * @param index1 The first item to swap.
     * @param index2 The second item to swap.
     */
    private void swap(int index1, int index2) {
        E temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

}
