package assign09;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class HashTableTest {

    StudentGoodHash jess = new StudentGoodHash(1338990, "jess", "lark");
    MapEntry<StudentGoodHash, Integer> jessEntry;
    StudentGoodHash james = new StudentGoodHash(1338990, "james", "bark");
    MapEntry<StudentGoodHash, Integer> jamesEntry;
    StudentGoodHash jack = new StudentGoodHash(1336890, "jack", "tops");
    MapEntry<StudentGoodHash, Integer> jackEntry;
    StudentGoodHash jello = new StudentGoodHash(1338960, "jello", "hello");
    MapEntry<StudentGoodHash, Integer> jelloEntry;

    StudentGoodHash frank = new StudentGoodHash(1337889, "frank", "stodd");
    MapEntry<StudentGoodHash, Integer> frankEntry;
    StudentGoodHash frankie = new StudentGoodHash(1337887, "frankie", "stoddard");
    MapEntry<StudentGoodHash, Integer> frankieEntry;

    HashTable<StudentGoodHash, Integer> studentTable;

    HashTable<StudentGoodHash, Integer> emptyHashTable;

    @BeforeEach
    public void setup() {
        studentTable = new HashTable<>();
        studentTable.put(jess, 1);
        jessEntry = new MapEntry<>(jess, 1);
        studentTable.put(james, 2);
        jamesEntry = new MapEntry<>(james, 2);
        studentTable.put(jack, 3);
        jackEntry = new MapEntry<>(jack, 3);
        studentTable.put(jello, 4);
        jelloEntry = new MapEntry<>(jello, 4);

        frankEntry = new MapEntry<>(frank, 0);
        frankieEntry = new MapEntry<>(frankie, 0);

        emptyHashTable = new HashTable<>();
    }

    @Test
    public void testClear() {
        assertEquals(4, studentTable.size());
        studentTable.clear();
        assertEquals(0, studentTable.size());
    }

    @Test
    public void testClearEmpty() {
        assertEquals(0, emptyHashTable.size());
        studentTable.clear();
        assertEquals(0, emptyHashTable.size());
    }

    @Test
    public void testContainsKey() {
        assertTrue(studentTable.containsKey(jess));
        assertTrue(studentTable.containsKey(james));
        assertTrue(studentTable.containsKey(jack));
        assertTrue(studentTable.containsKey(jello));
    }

    @Test
    public void testContainsKeyFalse() {
        assertFalse(studentTable.containsKey(frank));
        assertFalse(studentTable.containsKey(frankie));
    }

    @Test
    public void testContainsValue() {
        assertTrue(studentTable.containsValue(1));
        assertTrue((studentTable.containsValue(2)));
        assertTrue((studentTable.containsValue(3)));
        assertTrue((studentTable.containsValue(4)));
    }

    @Test
    public void testContainsValueFalse() {
        assertFalse(studentTable.containsValue(5));
        assertFalse(studentTable.containsValue(0));
        assertFalse(studentTable.containsValue(10));
    }

    @Test
    public void testEntries() {
        ArrayList<MapEntry<StudentGoodHash, Integer>> list = new ArrayList<>(studentTable.entries());
        assertTrue(list.contains(jessEntry));
        assertTrue(list.contains(jackEntry));
        assertTrue(list.contains(jamesEntry));
        assertTrue(list.contains(jelloEntry));
        assertEquals(list.size(), studentTable.size());
    }

    @Test
    public void testWrongEntries() {
        ArrayList<MapEntry<StudentGoodHash, Integer>> list = new ArrayList<>(studentTable.entries());
        assertFalse(list.contains(frankEntry));
        assertFalse(list.contains(frankieEntry));
        assertEquals(list.size(), studentTable.size());
    }

    @Test
    public void testNoEntries() {
        ArrayList<MapEntry<StudentGoodHash, Integer>> list = new ArrayList<>(emptyHashTable.entries());
        assertEquals(list.size(), emptyHashTable.size());
    }

    @Test
    public void testGet() {
        assertEquals(1, studentTable.get(jess));
        assertEquals(2, studentTable.get(james));
        assertEquals(3, studentTable.get(jack));
        assertEquals(4, studentTable.get(jello));
    }

    @Test
    public void testGetNonExistent() {
        assertNull(studentTable.get(frank));
        assertNull(studentTable.get(frankie));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(emptyHashTable.isEmpty());
        assertFalse(studentTable.isEmpty());
    }

    @Test
    public void testPut() {
        assertEquals(4, studentTable.size());

        assertNull(studentTable.put(frank, 5));
        assertEquals(5, studentTable.size());
        assertEquals(5, studentTable.get(frank));

        assertNull(studentTable.put(frankie, 6));
        assertEquals(6, studentTable.size());
        assertEquals(6, studentTable.get(frankie));
    }

    @Test
    public void testPutSameValue() {
        assertEquals(4, studentTable.size());

        studentTable.put(jess, 10);
        studentTable.put(james, 10);
        studentTable.put(jack, 10);
        studentTable.put(jello, 10);

        assertEquals(4, studentTable.size());

        assertTrue(studentTable.containsValue(10));
        assertTrue(studentTable.containsValue(10));
        assertTrue(studentTable.containsValue(10));
        assertTrue(studentTable.containsValue(10));
        assertTrue(studentTable.containsValue(10));
    }

    @Test
    public void testPutAlreadyExists() {
        assertEquals(4, studentTable.size());

        assertEquals(1, studentTable.put(jess, 10));
        assertEquals(4, studentTable.size());
        assertEquals(10, studentTable.get(jess));

        assertEquals(3, studentTable.put(jack, 11));
        assertEquals(4, studentTable.size());
        assertEquals(11, studentTable.get(jack));
    }

    @Test
    public void testRemove() {
        assertEquals(4, studentTable.size());

        assertEquals(1, studentTable.remove(jess));
        assertFalse(studentTable.containsKey(jess));
        assertEquals(3, studentTable.size());

        assertEquals(3, studentTable.remove(jack));
        assertFalse(studentTable.containsKey(jack));
        assertEquals(2, studentTable.size());
    }

    @Test
    public void testRemoveNonExistent() {
        assertEquals(4, studentTable.size());

        assertFalse(studentTable.containsKey(frank));
        assertNull(studentTable.remove(frank));
        assertEquals(4, studentTable.size());

        assertFalse(studentTable.containsKey(frankie));
        assertNull(studentTable.remove(frankie));
        assertEquals(4, studentTable.size());
    }

    @Test
    public void testSize() {
        assertEquals(4, studentTable.size());
        studentTable.remove(jack);
        assertEquals(3, studentTable.size());
    }

    @Test
    public void testEmptySize() {
        assertEquals(4, studentTable.size());
        studentTable.clear();
        assertEquals(0, studentTable.size());
        assertEquals(0, emptyHashTable.size());
    }

    @Test
    public void testStudentBadHash() {
        boolean isInteger = false;
        StudentBadHash jon = new StudentBadHash(1337889, "jon", "stewart");
        if (Integer.class.isInstance(jon.hashCode()))
            isInteger = true;
        assertTrue(isInteger);

    }

    @Test
    public void testStudentMediumHash() {
        boolean isInteger = false;
        StudentMediumHash johnny = new StudentMediumHash(1337890, "johnny", "stew");
        if (Integer.class.isInstance(johnny.hashCode()))
            isInteger = true;
        assertTrue(isInteger);
    }

    @Test
    public void testStudentGoodHash() {
        boolean isInteger = false;
        StudentGoodHash johnny = new StudentGoodHash(1337890, "johnny", "stew");
        if (Integer.class.isInstance(johnny.hashCode()))
            isInteger = true;
        assertTrue(isInteger);
    }

    @Test
    public void testBadHashCodeInHashTable() {
        StudentBadHash mike = new StudentBadHash(1336898, "Mike", "Wad");
        StudentBadHash michael = new StudentBadHash(1336778, "Michael", "Wadley");
        StudentBadHash marlo = new StudentBadHash(1336778, "Marlo", "Destin");

        HashTable<StudentBadHash, Integer> badHashTable = new HashTable<>();
        assertNull(badHashTable.put(mike, 0));
        assertNull(badHashTable.put(michael, 5));
        assertNull(badHashTable.put(marlo, 10));

        assertEquals(0, badHashTable.get(mike));
        assertEquals(5, badHashTable.get(michael));
        assertEquals(10, badHashTable.get(marlo));
    }

    @Test
    public void testMediumHashCodeHashTable() {
        StudentMediumHash darius = new StudentMediumHash(1332763, "Darius", "Super");
        StudentMediumHash jamie = new StudentMediumHash(1332789, "jamie", "fox");
        StudentMediumHash brighton = new StudentMediumHash(1332736, "brighton", "fox");

        HashTable<StudentMediumHash, Integer> mediumHashTable = new HashTable<>();
        assertNull(mediumHashTable.put(darius, 0));
        assertNull(mediumHashTable.put(jamie, 8));
        assertNull(mediumHashTable.put(brighton, 40));

        assertEquals(0, mediumHashTable.get(darius));
        assertEquals(8, mediumHashTable.get(jamie));
        assertEquals(40, mediumHashTable.get(brighton));

    }

    @Test
    public void testGoodHashCodeInHashTable() {
        StudentGoodHash mike = new StudentGoodHash(1336898, "Mike", "Wad");
        StudentGoodHash michael = new StudentGoodHash(1336778, "Michael", "Wadley");
        StudentGoodHash marlo = new StudentGoodHash(1336778, "Marlo", "Destin");

        HashTable<StudentGoodHash, Integer> goodHashTable = new HashTable<>();
        assertNull(goodHashTable.put(mike, 0));
        assertNull(goodHashTable.put(michael, 5));
        assertNull(goodHashTable.put(marlo, 10));

        assertEquals(0, goodHashTable.get(mike));
        assertEquals(5, goodHashTable.get(michael));
        assertEquals(10, goodHashTable.get(marlo));
    }

    @Test
    public void testRehash() {
        HashTable<Integer, String> table = new HashTable<>();
        assertEquals(10, table.backingArraySize);
        assertEquals(0, table.size());
        for (int i = 0; i < 50; i++) {
            table.put(i, "test " + i);
        }

        assertEquals(50, table.size());
        assertEquals(20, table.backingArraySize);

        assertTrue(table.containsKey(0));
        assertTrue(table.containsKey(25));
        assertTrue(table.containsKey(49));

        assertTrue(table.containsValue("test 0"));
        assertTrue(table.containsValue("test 25"));
        assertTrue(table.containsValue("test 49"));
    }


}