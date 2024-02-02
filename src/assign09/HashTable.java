package assign09;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @param <K> A generic type for keys in the hash table.
 * @param <V> A generic type for values in the has table.
 * @author Michael Wadley & Adam Liu
 * @version 11/13/22
 */
public class HashTable<K, V> implements Map<K, V> {

    private ArrayList<LinkedList<MapEntry<K, V>>> table;

    private int size;

    // Used in JUnit tests for the rehash method.
    protected int backingArraySize;

    protected int collisionCount;

    public HashTable() {
        table = new ArrayList<>();
        size = 0;
        for (int i = 0; i < 10; i++)
            table.add(new LinkedList<>());
        backingArraySize = table.size();
        collisionCount = 0;
    }

    /**
     * Removes all mappings from this map.
     * <p>
     * O(table length)
     */
    @Override
    public void clear() {
        for (LinkedList<MapEntry<K, V>> list : table) {
            list.clear();
        }
        size = 0;
    }

    /**
     * Determines whether this map contains the specified key.
     * <p>
     * O(1)
     *
     * @param key The key to look for in the map.
     * @return true if this map contains the key, false otherwise
     */
    @Override
    public boolean containsKey(K key) {
        int hashcode = key.hashCode();
        if (hashcode == Integer.MIN_VALUE)
            hashcode++;
        hashcode = Math.abs(hashcode);
        LinkedList<MapEntry<K, V>> list = table.get(hashcode % table.size());
        for (MapEntry<K, V> entry : list) {
            if (entry.getKey().equals(key))
                return true;
            else
                collisionCount++;
        }
        return false;
    }

    /**
     * Determines whether this map contains the specified value.
     * <p>
     * O(table length)
     *
     * @param value The value to look for in the map.
     * @return true if this map contains one or more keys to the specified value,
     * false otherwise
     */
    @Override
    public boolean containsValue(V value) {
        for (LinkedList<MapEntry<K, V>> list : table) {
            for (MapEntry<K, V> entry : list) {
                if (entry.getValue().equals(value))
                    return true;
            }
        }
        return false;
    }

    /**
     * Returns a List view of the mappings contained in this map, where the ordering of
     * mapping in the list is insignificant.
     * <p>
     * O(table length)
     *
     * @return a List object containing all mapping (i.e., entries) in this map
     */
    @Override
    public List<MapEntry<K, V>> entries() {
        ArrayList<MapEntry<K, V>> listToReturn = new ArrayList<>();
        for (LinkedList<MapEntry<K, V>> list : table) {
            listToReturn.addAll(list);
        }
        return listToReturn;
    }

    /**
     * Gets the value to which the specified key is mapped.
     * <p>
     * O(1)
     *
     * @param key The paired key to a value contained in a map.
     * @return the value to which the specified key is mapped, or null if this map
     * contains no mapping for the key
     */
    @Override
    public V get(K key) {
        int hashcode = key.hashCode();
        if (hashcode == Integer.MIN_VALUE)
            hashcode++;
        hashcode = Math.abs(hashcode);
        LinkedList<MapEntry<K, V>> list = table.get(hashcode % table.size());
        for (MapEntry<K, V> entry : list) {
            if (entry.getKey().equals(key))
                return entry.getValue();
        }
        return null;
    }

    /**
     * Determines whether this map contains any mappings.
     * <p>
     * O(1)
     *
     * @return true if this map contains no mappings, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * (I.e., if the key already exists in this map, resets the value;
     * otherwise adds the specified key-value pair.)
     * <p>
     * O(1)
     *
     * @param key   The key for the key-value pair to add to the map.
     * @param value The value for the key-value pair to add to the map.
     * @return the previous value associated with key, or null if there was no
     * mapping for key
     */
    @Override
    public V put(K key, V value) {
        // If after adding the next item, the average amount of items in each linked list is >= 5, we rehash.
        if (((size + 1) / table.size()) >= 10)
            rehash();
        V originalValue = null;
        int hashcode = key.hashCode();
        if (hashcode == Integer.MIN_VALUE)
            hashcode++;
        hashcode = Math.abs(hashcode);
        LinkedList<MapEntry<K, V>> list = table.get(hashcode % table.size());
        boolean keyFound = false;
        for (MapEntry<K, V> entry : list) {
            if (entry.getKey().equals(key)) {
                keyFound = true;
                originalValue = entry.getValue();
                entry.setValue(value);
                break;
            }
        }
        if (!keyFound){
            list.add(new MapEntry<>(key, value));
            size++;
        }
        return originalValue;
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     * <p>
     * O(1)
     *
     * @param key The key for the key-value pair to add to the map.
     * @return the previous value associated with key, or null if there was no
     * mapping for key
     */
    @Override
    public V remove(K key) {
        V originalValue = null;
        int hashcode = key.hashCode();
        if (hashcode == Integer.MIN_VALUE)
            hashcode++;
        hashcode = Math.abs(hashcode);
        LinkedList<MapEntry<K, V>> list = table.get(hashcode % table.size());
        for (MapEntry<K, V> entry : list) {
            if (entry.getKey().equals(key)) {
                originalValue = entry.getValue();
                list.remove(entry);
                size--;
                return originalValue;
            }
        }
        return originalValue;
    }

    /**
     * Determines the number of mappings in this map.
     * <p>
     * O(1)
     *
     * @return the number of mappings in this map
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Rehashes the table by growing the backing array by two times its original size, and moves items to their newly hashed positions in the larger array.
     */
    private void rehash() {
        ArrayList<LinkedList<MapEntry<K, V>>> newTable = new ArrayList<>();
        for (int i = 0; i < table.size() * 2; i++) {
            newTable.add(new LinkedList<>());
        }
        for (LinkedList<MapEntry<K, V>> list : table) {
            for (MapEntry<K, V> entry : list) {
                int hashcode = entry.getKey().hashCode();
                if (hashcode == Integer.MIN_VALUE)
                    hashcode++;
                hashcode = Math.abs(hashcode);
                newTable.get(hashcode % newTable.size()).add(entry);
            }
        }
        table = newTable;
        backingArraySize = table.size();
    }
}