package assign04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * A class containing methods to sort items, check if items are anagrams, and find the largest anagram group in an array.
 *
 * @author Michael Wadley and Adam Liu
 * @version 9/21/22
 */
public class AnagramChecker {

    /**
     * Sorts a string in lexicographic order and returns it.
     *
     * @param string The string you would like to be lexicographically sorted.
     * @return The string sorted in lexicographic order
     */
    public static String sort(String string) {
        Character[] arr = new Character[string.length()];
        for (int i = 0; i < string.length(); i++) {
            arr[i] = string.charAt(i);
        }
        insertionSort(arr, (Character char1, Character char2) -> char1.compareTo(char2));
        String sortedString = "";
        for (Character item : arr)
            sortedString += item;
        return sortedString;
    }

    /**
     * A generic insertionSort implementation that sorts the input array in place.
     *
     * @param arr The array of items you would like to insertionSort.
     * @param cmp A comparator object for the type of item you would like to sort.
     * @param <T> The type of item in the array that you would like to sort.
     */
    public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
        // Treat index 0 as sorted relative to itself, Start looping over unsorted list beginning at index 1.
        for (int i = 1; i < arr.length; i++) {
            // if our unsorted item is less than the sorted item below it, we swap it down as far as it needs to go.
            if (cmp.compare(arr[i], arr[i - 1]) < 0) {
                swap(arr, i, i - 1);
                for (int j = i - 1; j > 0; j--) {
                    if (cmp.compare(arr[j], arr[j - 1]) < 0) {
                        swap(arr, j, j - 1);
                    }
                }
            }
        }
    }

    /**
     * Determines whether two strings are anagrams of each other.
     *
     * @param string1 The first string.
     * @param string2 The second string.
     * @return Whether string1 and string2 are anagrams of each other.
     */
    public static boolean areAnagrams(String string1, String string2) {
        return sort(string1.toLowerCase()).equals(sort(string2.toLowerCase()));
    }

    /**
     * Returns an array containing the largest group of anagrams from the input file of anagram words.
     *
     * @param originalArrayOfStrings A list of strings containing anagram words.
     * @return An array of strings containing the largest anagram group from the input file.
     */
    public static String[] getLargestAnagramGroup(String[] originalArrayOfStrings) {
        String[] sortedCloneArray = originalArrayOfStrings.clone();
        for (int i = 0; i < sortedCloneArray.length; i++) {
            sortedCloneArray[i] = sort(sortedCloneArray[i].toLowerCase());
        }
      //  insertionSort(sortedCloneArray, (String o1, String o2) -> o1.compareTo(o2));
        Arrays.sort(sortedCloneArray, (String o1, String o2) -> o1.compareTo(o2));

        int largestGroupCount = 0;
        String largestGroupOfStrings = "";
        for (int i = 0; i < sortedCloneArray.length; i++) {
            int currentCount = 0;
            for (int j = i + 1; j < sortedCloneArray.length; j++) {
                if (areAnagrams(sortedCloneArray[i], sortedCloneArray[j]))
                    currentCount += 1;
            }
            if (currentCount > largestGroupCount) {
                largestGroupCount = currentCount;
                largestGroupOfStrings = sortedCloneArray[i];
            }
        }
        String[] largestAnagramGroup = new String[largestGroupCount];
        if (largestGroupCount != 0)
            largestAnagramGroup = new String[largestGroupCount + 1];
        int count = 0;
        for (int i = 0; i < originalArrayOfStrings.length; i++) {
            if (areAnagrams(largestGroupOfStrings, originalArrayOfStrings[i]))
                largestAnagramGroup[count++] = originalArrayOfStrings[i];
        }
        return largestAnagramGroup;
    }

    /**
     * This method will return an array of strings containing the largest anagram group found within a file.
     *
     * @param filename A text file containing anagram words, each word on its own line.
     * @return An array of strings containing the largest anagram group found from the text file.
     */
    public static String[] getLargestAnagramGroup(String filename) {
        File file = new File(filename);
        String[] arrayOfStrings;
        ArrayList<String> arrayListOfStrings = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                arrayListOfStrings.add(scanner.next());
            }
            arrayOfStrings = new String[arrayListOfStrings.size()];
            for (int i = 0; i < arrayListOfStrings.size(); i++) {
                arrayOfStrings[i] = arrayListOfStrings.get(i);
            }
        } catch (FileNotFoundException e) {
            return new String[0];
        }
        return getLargestAnagramGroup(arrayOfStrings);
    }

    /**
     * This is a helper method to swap indicies within a generic type array
     *
     * @param arr    A generic array type.
     * @param index1 the first index to swap with.{@code}.
     * @param index2 the second index to swap with.
     * @param <T>    Generic Object type.
     */
    public static <T> void swap(T[] arr, int index1, int index2) {
        T item = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = item;
    }
}