package assign07;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SpellCheckerTest {

    // Start BuildDictionary Test

    @Test
    public void testBuildDictionary() {
        ArrayList<String> dictionary = new ArrayList<>();
        dictionary.add("test");
        dictionary.add("tests");
        dictionary.add("testing");
        // The following line constructs a spellChecker which uses buildDictionary.
        SpellChecker checker = new SpellChecker(dictionary);
        List<String> list = checker.spellCheck(new File("src/assign07/spellCheckerTest.txt"));
        assertTrue(list.isEmpty());
    }

    // End BuildDictionary Test

    // Start Add Test

    @Test
    public void testAddToDictionary() {
        SpellChecker checker = new SpellChecker();
        checker.addToDictionary("test");
        checker.addToDictionary("tests");
        List<String> list = checker.spellCheck(new File("src/assign07/spellCheckerTest.txt"));
        assertFalse(list.isEmpty());

        checker.addToDictionary("testing");
        list = checker.spellCheck(new File("src/assign07/spellCheckerTest.txt"));
        assertTrue(list.isEmpty());
    }

    // End Add Test

    // Start Remove Test

    @Test
    public void testRemoveFromDictionary() {
        SpellChecker checker = new SpellChecker();
        checker.addToDictionary("test");
        checker.addToDictionary("tests");
        checker.addToDictionary("testing");
        List<String> list = checker.spellCheck(new File("src/assign07/spellCheckerTest.txt"));
        assertTrue(list.isEmpty());

        checker.removeFromDictionary("testing");
        list = checker.spellCheck(new File("src/assign07/spellCheckerTest.txt"));
        assertFalse(list.isEmpty());
    }

    // End Remove Test

    // Start SpellCheck Test

    @Test
    public void testSpellCheck() {
        SpellChecker checker = new SpellChecker();
        checker.addToDictionary("test");
        checker.addToDictionary("tests");
        checker.addToDictionary("testing");
        List<String> list = checker.spellCheck(new File("src/assign07/spellCheckerTest.txt"));
        assertTrue(list.isEmpty());

        checker.removeFromDictionary("testing");
        list = checker.spellCheck(new File("src/assign07/spellCheckerTest.txt"));
        assertEquals("testing", list.get(0));

        checker.removeFromDictionary("tests");
        list = checker.spellCheck(new File("src/assign07/spellCheckerTest.txt"));
        assertEquals("testing", list.get(1));
        assertEquals("tests", list.get(0));
    }

    // End SpellCheck Test

}