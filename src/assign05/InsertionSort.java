package assign05;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * A method for insertionSorting a generic ArrayList in a range of index bounds.
 *
 * @author Michael Wadley and Adam Liu
 * @version 10/4/22
 */
public class InsertionSort {

    /**
     * A generic insertionSort implementation that sorts the input Arraylist in place, between the given index bounds (inclusive).
     *
     * @param arr   The array of items you would like to insertionSort.
     * @param start The lower bound index of the list portion you want to sort.
     * @param start The upper bound index of the list portion you want to sort.
     * @param cmp   A comparator object for the type of item you would like to sort.
     * @param <T>   The type of item in the ArrayList.
     */
    public static <T> void sort(ArrayList<T> arr, int start, int end, Comparator<? super T> cmp) {
        // Treat index 0 as sorted relative to itself, Start looping over unsorted list beginning at index 1.
        for (int i = start + 1; i <= end; i++) {
            // if our unsorted item is less than the sorted item below it, we swap it down as far as it needs to go.
            if (cmp.compare(arr.get(i), arr.get(i - 1)) < 0) {
                swap(arr, i, i - 1);
                for (int j = i - 1; j > start; j--) {
                    if (cmp.compare(arr.get(j), arr.get(j - 1)) < 0) {
                        swap(arr, j, j - 1);
                    } else {
                        break;
                    }
                }
            }
        }
    }

    /**
     * A helper method to swap the elements of two given indices in an ArrayList.
     *
     * @param arr    The array to swap elements in.
     * @param index1 The index of the first element to be swapped.
     * @param index2 The index of the second element to be swapped.
     * @param <T>    The type of the elements in the ArrayList.
     */
    private static <T> void swap(ArrayList<T> arr, int index1, int index2) {
        T item = arr.get(index1);
        arr.set(index1, arr.get(index2));
        arr.set(index2, item);
    }
}