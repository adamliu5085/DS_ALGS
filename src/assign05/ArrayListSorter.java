package assign05;

import java.util.*;

/**
 * A class that generates the Mergesort and quicksort algorithms.
 * Additionally, generates Ascending, descending, and permuted lists for testing purposes.
 *
 * @author Michael Wadley and Adam Liu
 * @version 10/4/22
 */

public class ArrayListSorter {

//    private static final int insertionThreshold = 50;
    public static int insertionThreshold = 10;
    public static int pivotSelection = 0;
    
    /**
     * Performs a merge sort on the generic ArrayList given as input.
     *
     * @param list An ArrayList of items you would like to sort.
     * @param <T>  The type of your choosing.
     */
    public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> list) {
        if (list.size() == 0 || list.size() == 1)
            return;
        ArrayList<T> tempList = new ArrayList<>(list.size());
        for (int i = 0; i < list.size(); i++) {
            tempList.add(null);
        }
        // call internal overloaded method for entire array, and merge space
        // NOTE: size()-1, not size
        mergesort(list, tempList, 0, list.size() - 1);
    }

    /**
     * A recursive mergesort helper method.
     * If the threshold is met, switch from mergesort to InsertionSort to finish sorting, and then merge the sorted halves.
     * Otherwise, mergesort, and merge once finished.
     *
     * @param arr   The ArrayList of the specified type.
     * @param temp  A temporary copy of the ArrayList.
     * @param start The left bound index of the portion of the list you would like to sort.
     * @param end   The right bound index of the portion of the list you would like to sort.
     * @param <T>   The type of your choosing.
     */
    private static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr, ArrayList<T> temp, int start, int end) {
        if ((end - start) < insertionThreshold) {
            insertionSort(arr, start, end);
            merge(arr, temp, start, (start + (end - start) / 2), end);
            return;
        }

        int mid = start + (end - start) / 2;

        mergesort(arr, temp, start, mid);
        mergesort(arr, temp, mid + 1, end);

        merge(arr, temp, start, mid, end);
    }

    /**
     * A mergesort helper method.
     * Merges the contents of start to mid with the contents of mid to end into sorted positions, then places the sorted portion back into the array from start to end.
     *
     * @param arr   The ArrayList of items of the specified type.
     * @param temp  A copy of the ArrayList.
     * @param start The left bound index of the portion of the list you would like to merge.
     * @param mid   The midpoint between the given left and right bounds.
     * @param end   The right bound index of the portion of the list you would like to merge.
     * @param <T>   The type of your choosing.
     */
    private static <T extends Comparable<? super T>> void merge(ArrayList<T> arr, ArrayList<T> temp, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        int mergePos = start;

        while (i <= mid && j <= end) {
            if (arr.get(i).compareTo(arr.get(j)) <= 0)
                temp.set(mergePos++, arr.get(i++));
            else
                temp.set(mergePos++, arr.get(j++));
        }

        while (i <= mid) {
            temp.set(mergePos++, arr.get(i++));
        }
        while (j <= end) {
            temp.set(mergePos++, arr.get(j++));
        }

        //copy temp (from start to end) back into arr (from start to end)
        for (int k = start; k <= end; k++) {
            arr.set(k, temp.get(k));
        }
    }

    /**
     * Performs a QuickSort on the generic ArrayList give as input.
     *
     * @param list An ArrayList of items you would like to sort.
     * @param <T>  The type of your choosing.
     */
    public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> list) {
        if (list.size() == 0 || list.size() == 1)
            return;
        // Call quickSort with size() - 1 as the upper bound.
        quicksort(list, 0, list.size() - 1);
    }

    /**
     * A recursive helper method for QuickSort.
     * Partitions the list by the pivot, then QuickSorts each partition.
     *
     * @param list       An ArrayList of the specified type.
     * @param leftBound  The left bound index of the portion of the list you would like to sort.
     * @param rightBound The right bound index of the portion of the list you would like to sort.
     * @param <T>        The type of your choosing.
     */
    private static <T extends Comparable<? super T>> void quicksort(ArrayList<T> list, int leftBound, int rightBound) {
        if (leftBound >= rightBound)
            return;

        int pivot = partition(list, leftBound, rightBound);

        quicksort(list, leftBound, pivot - 1);
        
        quicksort(list, pivot + 1, rightBound);
    }

    /**
     * A helper method for QuickSort.
     * Find the pivot, then swap items to the left and right of the pivot as necessary.
     *
     * @param list       An ArrayList of the specified type.
     * @param leftBound  The left bound index of the portion of the list you would like to sort.
     * @param rightBound The right bound index of the portion of the list you would like to sort.
     * @param <T>        The type of your choosing.
     * @return The index of the pivot.
     */
    private static <T extends Comparable<? super T>> int partition(ArrayList<T> list, int leftBound, int rightBound) {
        int pivot; // = findPivot(list, leftBound, rightBound);
        if (pivotSelection == 0) {
   		 	pivot = findPivot1(list, leftBound, rightBound);
        }
        if (pivotSelection == 1) {
        	pivot = findPivot2(list, leftBound, rightBound);
        }
        else {
        	pivot = findPivot3(list, leftBound, rightBound);
        }


        swapReferences(list, pivot, rightBound);

        int L = leftBound;
        int R = rightBound - 1;

        while (L <= R) {
            while (L < rightBound && list.get(L).compareTo(list.get(rightBound)) <= 0)
                L++;
            while (R >= leftBound && list.get(R).compareTo(list.get(rightBound)) >= 0)
                R--;

            if (L < R)
                swapReferences(list, L, R);
        }

        swapReferences(list, L, rightBound);

        return L;
    }

    /**
     * A helper method for quicksort.
     * Determines and returns a good pivot selection.
     *
     * @param list       An ArrayList of the specified type.
     * @param leftBound  The left bound index of the portion of the list you would like to sort.
     * @param rightBound The right bound index of the portion of the list you would like to sort.
     * @param <T>        The type of your choosing.
     * @return The pivot selection.
     */
    public static <T extends Comparable<? super T>> int findPivot1(ArrayList<T> list, int leftBound, int rightBound) {
        int pivot = leftBound + (rightBound - leftBound) / 2;
//        int pivot = medianOfThree(list, int leftBound, int rightBound);
//        int pivot = new Random();      We need upper and lower bounds?
        return pivot;
    }
    
    public static <T extends Comparable<? super T>> int findPivot2(ArrayList<T> list, int leftBound, int rightBound) {
        int pivot = randomMedianOfThree(list, leftBound, rightBound);
        return pivot;
    }
    
    public static <T extends Comparable<? super T>> int findPivot3(ArrayList<T> list, int leftBound, int rightBound) {
    	int pivot = medianOfThree(list, leftBound, rightBound);
//      int pivot = new Random();      We need upper and lower bounds?
      return pivot;
  }


    /**
     * A generic insertionSort implementation that sorts the input Arraylist in place, between the given index bounds (inclusive).
     *
     * @param arr   The array of items you would like to insertionSort.
     * @param start The left bound index of the portion of the list you would like to sort.
     * @param end   The right bound index of the portion of the list you would like to sort.
     * @param <T>   The type of item in the ArrayList.
     */
    private static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> arr, int start, int end) {
        // Treat index 0 as sorted relative to itself, Start looping over unsorted list beginning at index 1.
        for (int i = start + 1; i <= end; i++) {
            // if our unsorted item is less than the sorted item below it, we swap it down as far as it needs to go.
            if (arr.get(i).compareTo(arr.get(i - 1)) < 0) {
                swapReferences(arr, i, i - 1);
                for (int j = i - 1; j > start; j--) {
                    if (arr.get(j).compareTo(arr.get(j - 1)) < 0) {
                        swapReferences(arr, j, j - 1);
                    } else {
                        break;
                    }
                }
            }
        }
    }

    /**
     * Swaps the elements at the parameter indices within the ArrayList.
     *
     * @param list       An ArrayList of the specified type.
     * @param leftIndex  the left index to swap
     * @param rightIndex the right index to swap
     * @param <T>        The type of your choosing.
     */
    private static <T> void swapReferences(ArrayList<T> list, int leftIndex, int rightIndex) {
        T item = list.get(leftIndex);
        list.set(leftIndex, list.get(rightIndex));
        list.set(rightIndex, item);
    }

    /**
     * Generates a list of numbers from 1 to the specified size, in ascending order.
     *
     * @param size The size of the generated Array.
     * @return A list of numbers from 1 to size.
     */
    public static ArrayList<Integer> generateAscending(int size) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            list.add(i);
        }
        return list;
    }

    /**
     * Generates a list of numbers from 1 to the specified size, in random order.
     *
     * @param size The size of the generated Array.
     * @return The generated permuted list.
     */
    public static ArrayList<Integer> generatePermuted(int size) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        return list;
    }

    /**
     * Generates a list of numbers from the specified size to 1, in descending order.
     *
     * @param size The size of the generated Array.
     * @return A list of numbers from size to 1.
     */
    public static ArrayList<Integer> generateDescending(int size) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = size; i >= 1; i--) {
            list.add(i);
        }
        return list;
    }

    /**
     * Finds the index of the median of three randomly selected items in an Array.
     *
     * @param list       An ArrayList of the specified type.
     * @param leftBound  The left bound index of the ArrayList.
     * @param rightBound The right bound index of the ArrayList.
     * @param <T>        The type of your choosing.
     * @return The index of the median of the three randomly selected items.
     */
    private static <T extends Comparable<? super T>> int randomMedianOfThree(ArrayList<T> list, int leftBound, int rightBound) {
        Random gen = new Random();
        int index1 = leftBound + gen.nextInt((rightBound - leftBound));
        int index2 = leftBound + gen.nextInt((rightBound - leftBound));
        int index3 = leftBound + gen.nextInt((rightBound - leftBound));
        ArrayList<T> sortedList = new ArrayList<>(Arrays.asList(list.get(index1), list.get(index2), list.get(index3)));
        mergesort(sortedList);
        if (sortedList.get(1).equals(list.get(index1)))
            return index1;
        if (sortedList.get(1).equals(list.get(index2)))
            return index2;
        return index3;
    }

    /**
     * Finds the index of the median item between the first, middle, and last indices of the given ArrayList.
     *
     * @param list       An Arraylist of the specific type.
     * @param leftBound  The left bound index of the ArrayList.
     * @param rightBound The right bound index of the ArrayList.
     * @param <T>        The type of your choosing.
     * @return The index of the median of the three items.
     */
    private static <T extends Comparable<? super T>> int medianOfThree(ArrayList<T> list, int leftBound, int rightBound) {
        int index2 = (rightBound - leftBound) / 2;
        ArrayList<T> sortedList = new ArrayList<>(Arrays.asList(list.get(leftBound), list.get(index2), list.get(rightBound)));
        mergesort(sortedList);
        if (sortedList.get(1).equals(list.get(leftBound)))
            return leftBound;
        if (sortedList.get(1).equals(list.get(index2)))
            return index2;
        return rightBound;
    }

}