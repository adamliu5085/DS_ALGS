package assign04;


import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A comprehensive set of tests for the AnagramChecker class.
 *
 * @author Michael Wadley and Adam Liu
 * @version 9/21/22
 */
public class AnagramCheckerTest {

    @Test
    public void testStringSort() {
        assertEquals("estt", AnagramChecker.sort("test"));
        assertEquals("orst", AnagramChecker.sort("sort"));
        assertEquals("bdeln", AnagramChecker.sort("blend"));
        assertEquals("abelt", AnagramChecker.sort("table"));
        assertEquals("belott", AnagramChecker.sort("bottle"));
        assertEquals("bbbelu", AnagramChecker.sort("bubble"));
        assertEquals("aalmmm", AnagramChecker.sort("mammal"));
        assertEquals("abilrry", AnagramChecker.sort("library"));
        assertEquals("adeelrt", AnagramChecker.sort("alerted"));
        assertEquals("eginstt", AnagramChecker.sort("testing"));
        assertEquals("aaadmnt", AnagramChecker.sort("adamant"));
        assertEquals("gghhhiilt", AnagramChecker.sort("highlight"));
        assertEquals("aeorsssss", AnagramChecker.sort("assessors"));
        assertEquals("ccceenoru", AnagramChecker.sort("occurence"));
        assertEquals("iiiimppssss", AnagramChecker.sort("mississippi"));
        assertEquals("aaccccffhiiiiiiiiilllnnnooptu", AnagramChecker.sort("floccinaucinihilipilification"));
        assertEquals("aacccccceiiiiiilllmmnnnnooooooooopprrsssstuuv", AnagramChecker.sort("pneumonoultramicroscopicsilicovolcanoconiosis"));
        assertEquals("aaacccdeefgiiiiiiillloopprrssstuux", AnagramChecker.sort("supercalifragilisticexpialidocious"));
        assertEquals("aabbcdfhiiiiiiilnnoorstttuu", AnagramChecker.sort("honorificabilitudinitatibus"));
    }

    @Test
    public void testSortedStringSort() {
        assertEquals("estt", AnagramChecker.sort("estt"));
        assertEquals("orst", AnagramChecker.sort("orst"));
    }

    @Test
    public void testEmptyStringSort() {
        assertEquals("", AnagramChecker.sort(""));
    }

    @Test
    public void testInsertionSortInteger() {
        Integer[] intArray = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        Integer[] sortedIntArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        AnagramChecker.insertionSort(intArray, (Integer o1, Integer o2) -> o1.compareTo(o2));
        boolean flag = true;
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] != sortedIntArray[i])
                flag = false;
        }
        assertTrue(flag);
    }

    @Test
    public void testInsertionSortCharacter() {
        Character[] array = {'b', 'd', 'f', 'g', 'j', 'e', 'y', 'i', 'o', 'y'};
        Character[] sortedArray = {'b', 'd', 'e', 'f', 'g', 'i', 'j', 'o', 'y', 'y'};
        AnagramChecker.insertionSort(array, (Character o1, Character o2) -> o1.compareTo(o2));
        boolean flag = true;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != sortedArray[i])
                flag = false;
        }
        assertTrue(flag);
    }

    @Test
    public void testInsertionSortSorted() {
        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer[] sortedIntArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        AnagramChecker.insertionSort(intArray, (Integer o1, Integer o2) -> o1.compareTo(o2));
        boolean flag = true;
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] != sortedIntArray[i])
                flag = false;
        }
        assertTrue(flag);
    }

    @Test
    public void testInsertionSortEmpty() {
        Integer[] intArray = {};
        Integer[] sortedIntArray = {};
        AnagramChecker.insertionSort(intArray, (Integer o1, Integer o2) -> o1.compareTo(o2));
        assertEquals(intArray.length, sortedIntArray.length);
    }

    @Test
    public void testAreAnagramsLowercase() {
        assertTrue(AnagramChecker.areAnagrams("beat", "beta"));
        assertTrue(AnagramChecker.areAnagrams("assert", "stares"));
        assertTrue(AnagramChecker.areAnagrams("begin", "being"));
    }

    @Test
    public void testAreAnagramsMixedUppercase() {
        assertTrue(AnagramChecker.areAnagrams("Beat", "beTa"));
        assertTrue(AnagramChecker.areAnagrams("aSseRt", "sTAres"));
        assertTrue(AnagramChecker.areAnagrams("bEgin", "beIng"));
    }

    @Test
    public void testAreNotAnagrams() {
        assertFalse(AnagramChecker.areAnagrams("Beat", "wows"));
        assertFalse(AnagramChecker.areAnagrams("aSseRt", "bar"));
        assertFalse(AnagramChecker.areAnagrams("bEgin", "tents"));
    }

    @Test
    public void testAreAnagramsEmpty() {
        assertTrue(AnagramChecker.areAnagrams("", ""));
    }

    @Test
    public void testGetLargestAnagramGroup() {
        String[] anagramGroup = {"Strange", "argents", "garnets", "master", "stream", "ramets", "maters"};
        String[] largestGroup = {"master", "stream", "ramets", "maters"};
        String[] checkGroup = AnagramChecker.getLargestAnagramGroup(anagramGroup);
        boolean flag = true;
        for (int i = 0; i < checkGroup.length; i++) {
            if (!contains(largestGroup, checkGroup[i]))
                flag = false;
        }
        assertTrue(flag);
    }

    @Test
    public void testGetLargestAnagramGroupNoAnagrams() {
        String[] anagramGroup = {"Strange", "test", "testing", "loser", "fork"};
        String[] largestGroup = {};
        String[] checkGroup = AnagramChecker.getLargestAnagramGroup(anagramGroup);
        assertTrue(checkGroup.length == largestGroup.length);
    }

    @Test
    public void testGetLargestAnagramGroupEmpty() {
        String[] anagramGroup = {};
        String[] largestGroup = {};
        String[] checkGroup = AnagramChecker.getLargestAnagramGroup(anagramGroup);
        assertTrue(checkGroup.length == largestGroup.length);
    }

    @Test
    public void testGetLargestAnagramGroupEmptyStrings() {
        String[] anagramGroup = {"", "", "", "test", "estt"};
        String[] largestGroup = {"", "", ""};
        String[] checkGroup = AnagramChecker.getLargestAnagramGroup(anagramGroup);
        boolean flag = true;
        for (int i = 0; i < checkGroup.length; i++) {
            if (!contains(largestGroup, checkGroup[i]))
                flag = false;
        }
        assertTrue(flag);
    }

    @Test
    public void testGetLargestAnagramGroupFile() {
        String[] largestGroup = {"carets", "Caters", "caster", "crates", "Reacts", "recast", "traces"};
        String[] checkGroup = AnagramChecker.getLargestAnagramGroup("C:/Drive/School/University Of Utah/Fall 2022/CS 2420/Projects/src/HW/assign04/sample_word_list.txt.txt");
        boolean flag = true;
        for (int i = 0; i < checkGroup.length; i++) {
            if (!contains(largestGroup, checkGroup[i]))
                flag = false;
        }
        assertTrue(flag);
    }

    @Test
    public void testGetLargestAnagramGroupNonExistFile() {
        String[] largestGroup = {};
        String[] checkGroup = AnagramChecker.getLargestAnagramGroup("asdfasdf.txt");
        boolean flag = true;
        for (int i = 0; i < checkGroup.length; i++) {
            if (!contains(largestGroup, checkGroup[i]))
                flag = false;
        }
        assertTrue(flag);
    }

    // Contains Helper method
    public boolean contains(String[] arr, String toFind) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(toFind))
                return true;
        }
        return false;
    }
}