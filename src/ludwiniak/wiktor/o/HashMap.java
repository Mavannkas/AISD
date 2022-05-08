package ludwiniak.wiktor.o;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class HashMap<TKey, TValue> {
    private final int beginnigSize;
    private LinkedList<Entry>[] table;
    private int size;
    private int elements = 0;
    private double loadFactor;
    private Function<TKey, Integer> hashFunction;


    public HashMap(int initialSize, double loadFactor, Function<TKey, Integer> hashFunction) {

        this.loadFactor = loadFactor;
        this.hashFunction = hashFunction;
        this.size = initialSize;
        table = new LinkedList[initialSize];
        beginnigSize = initialSize;
    }

    public void add(TKey key, TValue value) throws DuplicateKeyException {
        int hash = calculateHash(key);

        if (findIndexOfEntry(key, hash) == -1) {
            if (table[hash] == null) {
                table[hash] = new LinkedList<>();
            }

            elements++;
            double currentLoadFactor = (double) elements / size;

            Entry entryToAdd = new Entry(key, value);
            table[hash].add(entryToAdd);


            if (currentLoadFactor >= loadFactor) {
                refactorNewHashMap(size * 2);
            }


        } else {
            throw new DuplicateKeyException();
        }
    }


    public boolean containsKey(TKey key) {
        int hash = calculateHash(key);
        if (table[hash] != null) {
            for (Entry e : table[hash]) {
                if (e.key.equals(key))
                    return true;
            }
        }
        return false;
    }

    public boolean containsValue(TValue value) {
        for (LinkedList<Entry> list : table) {
            if (list != null) {
                for (Entry e : list) {
                    if (e.value.equals(value))
                        return true;
                }
            }
        }
        return false;
    }

    public int elements() {
        return elements;
    }

    public TValue get(TKey key) throws NoSuchElementException {
        int hash = calculateHash(key);
        int index = findIndexOfEntry(key, hash);

        if (index != -1) {
            return (TValue) table[hash].get(index).value;
        } else {
            throw new NoSuchElementException();
        }
    }

    public void put(TKey key, TValue value) {
        int hash = calculateHash(key);
        int index = findIndexOfEntry(key, hash);

        if (index != -1) {
            table[hash].get(index).value = value;
        } else {
            try {
                add(key, value);
            } catch (DuplicateKeyException e) {
                e.printStackTrace();
            }
        }
    }

    public TValue remove(TKey key) {
        int hash = calculateHash(key);
        int index = findIndexOfEntry(key, hash);

        if (index != -1) {
            TValue valueToDelete = (TValue) table[hash].get(index).value;
            table[hash].remove(index);
            elements--;
            return valueToDelete;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public void rehash(Function<TKey, Integer> newHashFunction) {
        this.hashFunction = newHashFunction;

        LinkedList<Entry>[] newTable = new LinkedList[table.length];

        for (int i = 0; i < newTable.length; i++) {
            newTable[i] = new LinkedList<>();
        }

        for (int i = 0; i < table.length; i++) {
            if (table[i] != null)
                for (int j = 0; j < table[i].size(); j++) {
                    int hash = calculateHash((TKey) table[i].get(j).key);
                    newTable[hash].add(table[i].get(j));
                }
        }
        table = newTable;
    }

    private int findIndexOfEntry(TKey key, int hash) {
        int curr = -1;
        if (table[hash] != null) {
            for (Entry e : table[hash]) {
                curr++;
                if (e.key.equals(key))
                    return curr;
            }
        }
        return curr;
    }

    private void refactorNewHashMap(int newSize) {
        HashMap newHashMap = new HashMap(newSize, loadFactor, hashFunction);
        for (int i = 0; i < size; i++) {
            if (table[i] != null) {
                for (Entry e : table[i]) {
                    try {
                        newHashMap.add(e.key, e.value);
                    } catch (DuplicateKeyException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        table = newHashMap.table;
        size = newSize;
    }

    private int calculateHash(TKey key) {
        return hashFunction.apply(key) % size;
    }

    public void clear() {
        elements = 0;
        size = beginnigSize;
        Arrays.fill(table, null);
    }

    class Entry<K, V> {
        private K key;
        private V value;
        private Entry next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            next = null;
        }
    }
}
