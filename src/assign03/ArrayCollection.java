package assign03;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @param <T> - generic type placeholder
 * @author Daniel Kopta and Michael Wadley and Adam Liu
 * Implements the Collection interface using an array as storage.
 * The array must grow as needed.
 * An ArrayCollection can not contain duplicates.
 * All methods should be implemented as defined by the Java API, unless otherwise specified.
 * <p>
 * It is your job to fill in the missing implementations and to comment this class.
 * Every method should have comments (Javadoc-style prefered).
 * The comments should at least indicate what the method does,
 * what the arguments mean, and what the returned value is.
 * It should specify any special cases that the method
 * handles. Any code that is not self-explanatory should be commented.
 */
public class ArrayCollection<T> implements Collection<T> {

    private T data[]; // Storage for the items in the collection
    private int size; // Keep track of how many items this collection holds

    // There is no clean way to convert between T and Object, so we suppress the warning.
    @SuppressWarnings("unchecked")
    public ArrayCollection() {
        size = 0;
        // We can't instantiate an array of unknown type T, so we must create an Object array, and typecast
        data = (T[]) new Object[10]; // Start with an initial capacity of 10
    }

    /**
     * This is a helper method specific to ArrayCollection
     * Doubles the size of the data storage array, retaining its current contents.
     */
    @SuppressWarnings("unchecked")
    private void grow() {
        T[] expandedArray = (T[]) new Object[data.length * 2];
        for (int index = 0; index < size(); index++) {
            expandedArray[index] = data[index];
        }
        data = expandedArray;
    }

    /**
     * Adds an item of type T to the collection, if it is not already in the collection.
     *
     * @param arg0 The item to be added to the collection.
     * @return True if the item was added to the collection, false otherwise.
     */
    public boolean add(T arg0) {
        if (contains(arg0))
            return false;
        if (size == data.length)
            grow();
        data[size++] = arg0;
        return true;
    }

    /**
     * Adds all objects within the parameter collection to the collection.
     *
     * @param args a Collection of items of type T or extends T.
     * @return True if items were added, false otherwise.
     */
    public boolean addAll(Collection<? extends T> arg0) {
        boolean added = false;
        Iterator<? extends T> iterator = arg0.iterator();
        while (iterator.hasNext()) {
            if (add(iterator.next())) {
                added = true;
            }
        }
        if (added)
            return true;
        return false;
    }

    /**
     * Clears all data stores within the collection.
     */
    public void clear() {
        for (int index = 0; index < size(); index++) {
            data[index] = null;
        }
        size = 0;
    }

    /**
     * Determines whether an object parameter can be found in the collection.
     *
     * @param arg0 Item whose presence in this collection is to be tested.
     * @return True, if arg0 is found in the collection, false otherwise.
     */
    public boolean contains(Object arg0) {
        for (int index = 0; index < size(); index++) {
            if (data[index].equals(arg0))
                return true;
        }
        return false;
    }

    /**
     * Determines whether this collection contains all the objects in the parameter collection.
     *
     * @param arg0 Collection to be checked for containment in this collection.
     * @return False if an item is not contained both arg0 and this collection, true otherwise.
     */
    public boolean containsAll(Collection<?> arg0) {
        for (Object arg : arg0) {
            if (!contains(arg)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determines whether the collection is empty.
     *
     * @return True if the size of the collection is 0, false otherwise.
     */
    public boolean isEmpty() {
        if (size == 0)
            return true;
        return false;
    }

    /**
     * Creates and returns a new ArrayCollection iterator object.
     *
     * @return The ArrayCollection Iterator object.
     */
    public Iterator<T> iterator() {
        return new ArrayCollectionIterator();
    }

    /**
     * Removes a parameter object from the collection, if present.
     *
     * @param arg0 element to be removed from this collection.
     * @return True if the item is removed from this collection, false if it is not found.
     */
    public boolean remove(Object arg0) {
        if (!contains(arg0))
            return false;
        boolean foundItem = false;
        for (int i = 0; i < size; i++) {
            if (data[i].equals(arg0)) {
                foundItem = true;
            }
            // Once we have found the item we must remove, we begin overwriting each index with the index above it.
            // Essentially shifting down each item in the backing array by 1 index.
            if (foundItem) {
                data[i] = data[i + 1];
            }
        }
        if (foundItem)
            size--;
        return true;
    }

    /**
     * Removes all items in this collection that are present in the parameter collection.
     *
     * @param arg0 collection containing elements to be removed from this collection.
     * @return True if any items were removed from this collection, false otherwise.
     */
    public boolean removeAll(Collection<?> arg0) {
        boolean removed = false;
        Iterator<?> iterator = arg0.iterator();
        while (iterator.hasNext()) {
            Object item = iterator.next();
            if (this.contains(item)) {
                remove(item);
                removed = true;
            }
        }
        if (removed)
            return true;
        return false;
    }

    /**
     * Removes all items from this collection that are not contained in the parameter collection.
     *
     * @param arg0 collection containing elements to be retained in this collection.
     * @return True if any items were removed from this collection, and false otherwise.
     */
    public boolean retainAll(Collection<?> arg0) {
        boolean removed = false;
        Iterator<?> iterator = iterator();
        while (iterator.hasNext()) {
            Object item = iterator.next();
            if (!arg0.contains(item)) {
                iterator.remove();
                removed = true;
            }
        }
        if (removed)
            return true;
        return false;
    }

    /**
     * Returns the number of items in this collection.
     *
     * @return The size of the collection.
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns an array containing all items in this collection.
     *
     * @return An array of items in this collection.
     */
    public Object[] toArray() {
        Object[] array = new Object[size];
        for (int i = 0; i < size; i++) {
            array[i] = data[i];
        }
        return array;
    }

    /*
     * Don't implement this method (unless you want to).
     * It must be here to complete the Collection interface.
     * We will not test this method.
     */
    public <T> T[] toArray(T[] arg0) {
        return null;
    }



    /*

     */

    /**
     * Sorting method specific to ArrayCollection - not part of the Collection interface.
     * Must implement a selection sort (see Assignment 2 for code).
     * Must not modify this ArrayCollection.
     *
     * @param cmp - the comparator that defines item ordering
     * @return - the sorted list
     */
    public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
        ArrayList<T> list = new ArrayList<>();
        list.addAll(this);
        for (int i = 0; i < list.size() - 1; i++) {
            int j, minIndex;
            for (j = i + 1, minIndex = i; j < list.size(); j++)
                if (cmp.compare(list.get(j), list.get(minIndex)) < 0)
                    minIndex = j;
            T temp = list.get(i);
            list.set(i, list.get(minIndex));
            list.set(minIndex, temp);
        }
        return list;
    }


    /**
     * @author Michael Wadley and Adam Liu
     * An iterator class specific to ArrayCollections.
     * Supports iteration using hasNext(), next() and remove() methods.
     */
    private class ArrayCollectionIterator implements Iterator<T> {
        int index = 0;
        boolean canRemove = false;
        T lastObjectFound;

        public ArrayCollectionIterator() {
        }

        /**
         * Determines if the iterator has a proceeding item.
         *
         * @return True if there is another item to check in the collection.
         */
        public boolean hasNext() {
            if (index < size)
                return true;
            return false;
        }

        /**
         * Returns the next item in the collection iteration, sets its removable status, and updates the iterator pos.
         *
         * @return The next item in the collection iteration.
         */
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            canRemove = true;
            lastObjectFound = data[index++];
            return lastObjectFound;
        }

        /**
         * Removes the item at next() if acceptable. If an item is removed, updates the removable status.
         * Updates the iterator position.
         */
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException();
            }
            ArrayCollection.this.remove(lastObjectFound);
            index--;
            canRemove = false;
        }
    }

}
