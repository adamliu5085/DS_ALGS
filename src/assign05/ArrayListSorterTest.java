package assign05;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A set of comprehensive tests of the ArrayListSorterClass and its methods.
 *
 * @author Michael Wadley and Adam Liu
 * @version 10/4/22
 */

public class ArrayListSorterTest {

    @Test
    public void testMergesortInteger() {
        ArrayList<Integer> intArrayToBeSorted = ArrayListSorter.generatePermuted(10);
        ArrayListSorter.mergesort(intArrayToBeSorted);
        for (int i = 1; i < intArrayToBeSorted.size(); i++) {
            assertTrue(intArrayToBeSorted.get(i - 1).compareTo(intArrayToBeSorted.get(i)) <= 0);
        }
    }

    @Test
    public void testMergesortLargeIntegerArray() {
        ArrayList<Integer> intArrayToBeSorted = ArrayListSorter.generatePermuted(30);
        ArrayListSorter.mergesort(intArrayToBeSorted);
        for (int i = 1; i < intArrayToBeSorted.size(); i++) {
            assertTrue(intArrayToBeSorted.get(i - 1).compareTo(intArrayToBeSorted.get(i)) <= 0);
        }
    }

    @Test
    public void testMergesortCharacter() {
        ArrayList<Character> arrayToBeSorted = new ArrayList<>(Arrays.asList('l', 'd', 'f', 'g', 'j', 'e', 'y', 'i', 'o', 'y'));
        ArrayListSorter.mergesort(arrayToBeSorted);
        for (int i = 1; i < arrayToBeSorted.size(); i++) {
            assertTrue(arrayToBeSorted.get(i - 1).compareTo(arrayToBeSorted.get(i)) <= 0);
        }
    }

    @Test
    public void testMergeSortEmpty() {
        ArrayList<Integer> emptyIntList = new ArrayList<>();
        ArrayListSorter.mergesort(emptyIntList);
        assertTrue(emptyIntList.size() == 0);
    }

    @Test
    public void testMergesortSorted() {
        ArrayList<Integer> intArrayToBeSorted = ArrayListSorter.generateAscending(10);
        ArrayListSorter.mergesort(intArrayToBeSorted);
        for (int i = 1; i < intArrayToBeSorted.size(); i++) {
            assertTrue(intArrayToBeSorted.get(i - 1).compareTo(intArrayToBeSorted.get(i)) <= 0);
        }
    }

    @Test
    public void testMergesortIntegerDescending() {
        ArrayList<Integer> intArrayToBeSorted = ArrayListSorter.generateDescending(10);
        ArrayListSorter.mergesort(intArrayToBeSorted);
        for (int i = 1; i < intArrayToBeSorted.size(); i++) {
            assertTrue(intArrayToBeSorted.get(i - 1).compareTo(intArrayToBeSorted.get(i)) <= 0);
        }
    }

    @Test
    public void testMergesortNullValues() {
        ArrayList<Integer> intArrayToBeSorted = new ArrayList<>(Arrays.asList(null, null, null, null, null));
        assertThrows(NullPointerException.class, () -> ArrayListSorter.mergesort(intArrayToBeSorted));
    }

    @Test
    public void testGenerateDescending() {
        ArrayList<Integer> descendingArray = ArrayListSorter.generateDescending(10);
        for (int i = descendingArray.size(); i < 0; i--) {
            assertTrue(descendingArray.get(i).compareTo(descendingArray.get(i - 1)) <= 0);
        }
    }

    @Test
    public void testGenerateDescendingSize0() {
        ArrayList<Integer> descendingArray = ArrayListSorter.generateDescending(0);
        assertEquals(0, descendingArray.size());
    }

    @Test
    public void testGenerateAscending() {
        ArrayList<Integer> ascendingArray = ArrayListSorter.generateAscending(10);
        for (int i = 1; i < ascendingArray.size(); i++) {
            assertTrue(ascendingArray.get(i - 1).compareTo(ascendingArray.get(i)) <= 0);
        }
    }

    @Test
    public void testGenerateAscendingSize0() {
        ArrayList<Integer> ascendingArray = ArrayListSorter.generateAscending(0);
        assertEquals(0, ascendingArray.size());
    }


    @Test
    public void testGeneratePermuted() {
        ArrayList<Integer> intArray = ArrayListSorter.generatePermuted(10);
        assertEquals(10, intArray.size());
    }

    @Test
    public void testGeneratePermutedSize0() {
        ArrayList<Integer> permutedArray = ArrayListSorter.generatePermuted(0);
        assertEquals(0, permutedArray.size());
    }

    @Test
    public void testQuicksortInteger() {
        ArrayList<Integer> intArrayToBeSorted = ArrayListSorter.generatePermuted(10);
        ArrayListSorter.quicksort(intArrayToBeSorted);
        for (int i = 1; i < intArrayToBeSorted.size(); i++) {
            assertTrue(intArrayToBeSorted.get(i - 1).compareTo(intArrayToBeSorted.get(i)) <= 0);
        }
    }

    @Test
    public void testQuicksortLargeIntegerArray() {
        ArrayList<Integer> intArrayToBeSorted = ArrayListSorter.generatePermuted(30);
        ArrayListSorter.quicksort(intArrayToBeSorted);
        for (int i = 1; i < intArrayToBeSorted.size(); i++) {
            assertTrue(intArrayToBeSorted.get(i - 1).compareTo(intArrayToBeSorted.get(i)) <= 0);
        }
    }

    @Test
    public void testQuicksortCharacter() {
        ArrayList<Character> arrayToBeSorted = new ArrayList<>(Arrays.asList('l', 'd', 'f', 'g', 'j', 'e', 'y', 'i', 'o', 'y'));
        ArrayListSorter.quicksort(arrayToBeSorted);
        for (int i = 1; i < arrayToBeSorted.size(); i++) {
            assertTrue(arrayToBeSorted.get(i - 1).compareTo(arrayToBeSorted.get(i)) <= 0);
        }
    }

    @Test
    public void testQuickeSortEmpty() {
        ArrayList<Integer> emptyIntList = new ArrayList<>();
        ArrayListSorter.quicksort(emptyIntList);
        assertTrue(emptyIntList.size() == 0);
    }

    @Test
    public void testQuicksortSorted() {
        ArrayList<Integer> intArrayToBeSorted = ArrayListSorter.generateAscending(10);
        ArrayListSorter.quicksort(intArrayToBeSorted);
        for (int i = 1; i < intArrayToBeSorted.size(); i++) {
            assertTrue(intArrayToBeSorted.get(i - 1).compareTo(intArrayToBeSorted.get(i)) <= 0);
        }
    }

    @Test
    public void testQuicksortIntegerDescending() {
        ArrayList<Integer> intArrayToBeSorted = ArrayListSorter.generateDescending(10);
        ArrayListSorter.quicksort(intArrayToBeSorted);
        for (int i = 1; i < intArrayToBeSorted.size(); i++) {
            assertTrue(intArrayToBeSorted.get(i - 1).compareTo(intArrayToBeSorted.get(i)) <= 0);
        }
    }

    @Test
    public void testQuicksortNullValues() {
        ArrayList<Integer> intArrayToBeSorted = new ArrayList<>(Arrays.asList(null, null, null, null, null));
        assertThrows(NullPointerException.class, () -> ArrayListSorter.quicksort(intArrayToBeSorted));
    }

}
