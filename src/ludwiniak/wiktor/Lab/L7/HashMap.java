package ludwiniak.wiktor.Lab.L7;

import ludwiniak.wiktor.Lab.L4.TwoWayLinkedList;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

public class HashMap<TKey, TValue> {
    private final int initialSize;
    private final double loadFactor;
    private Function<TKey, Integer> hashFunction;
    private int size;
    private int elementsCount = 0;
    private TwoWayLinkedList<Entry<TKey, TValue>>[] array;

    public HashMap(int initialSize, double loadFactor, Function<TKey, Integer> hashFunction) {
        this.initialSize = initialSize;
        this.size = initialSize;
        this.loadFactor = loadFactor;
        this.hashFunction = hashFunction;

        array = new TwoWayLinkedList[size];
    }

    public void add(TKey key, TValue value) throws DuplicateKeyException {
        TwoWayLinkedList<Entry<TKey, TValue>> entries = getNonNullEntries(key);
        Entry<TKey, TValue> entry = new Entry<>(key, value);

        if (entries.contains(entry)) {
            throw new DuplicateKeyException();
        }

        addEntryToList(entry, entries);
    }

    private void tryToResize() {
        if (elementsCount / (double) size >= loadFactor) {
            size *= 2;
            rebuild();
        }
    }

    public void clear() {
        elementsCount = 0;
        size = initialSize;
        array = new TwoWayLinkedList[size];
    }

    public boolean containsKey(TKey key) {
        return contains((entry) -> entry.key.equals(key));
    }

    public boolean containsValue(TValue value) {
        return contains((entry) -> entry.value.equals(value));
    }

    private boolean contains(Function<Entry<TKey, TValue>, Boolean> callback) {
        AtomicBoolean output = new AtomicBoolean(false);
        iterateByElements((entry)->{
            if (callback.apply(entry)) {
                output.set(true);
                return true;
            }

            return false;
        });

        return output.get();
    }

    public int elements() {
        return elementsCount;
    }

    public TValue get(TKey key) throws NoSuchElementException {
        TwoWayLinkedList<Entry<TKey, TValue>> entries = getEntries(key);
        if (entries == null) {
            throw new NoSuchElementException();
        }

        for (Entry<TKey, TValue> entry : entries) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }

        throw new NoSuchElementException();
    }

    public void put(TKey key, TValue value) {
        TwoWayLinkedList<Entry<TKey, TValue>> entries = getNonNullEntries(key);
        Entry<TKey, TValue> entry = new Entry<>(key, value);

        int elementIndex = entries.indexOf(entry);
        if (elementIndex != -1) {
            entries.get(elementIndex).value = value;
        } else {
            addEntryToList(entry, entries);
        }
    }

    private void addEntryToList(Entry<TKey, TValue> entry, TwoWayLinkedList<Entry<TKey, TValue>> list) {
        elementsCount++;
        list.add(entry);
        tryToResize();
    }

    private TwoWayLinkedList<Entry<TKey, TValue>> getNonNullEntries(TKey key) {
        TwoWayLinkedList<Entry<TKey, TValue>> entries = getEntries(key);

        if (entries == null) {
            entries = new TwoWayLinkedList<>();
            array[hashFunction.apply(key) % size] = entries;
        }

        return entries;
    }

    public TValue remove(TKey key) {
        TwoWayLinkedList<Entry<TKey, TValue>> entries = getEntries(key);
        if (entries == null) {
            return null;
        }

        for (Entry<TKey, TValue> entry : entries) {
            if (entry.key.equals(key)) {
                elementsCount--;
                entries.remove(entry);
                return entry.value;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    private TwoWayLinkedList<Entry<TKey, TValue>> getEntries(TKey key) {
        return array[hashFunction.apply(key) % size];
    }

    private void rebuild() {
        HashMap<TKey, TValue> newHashMap = new HashMap<>(size, loadFactor, hashFunction);

        iterateByElements((value) -> {
            newHashMap.put(value.getKey(), value.getValue());
            return false;
        });

        array = newHashMap.array;
    }

    private void iterateByElements(Function<Entry<TKey, TValue>, Boolean> callback) {
        for (TwoWayLinkedList<Entry<TKey, TValue>> values : array) {
            if (values != null) {
                for (Entry<TKey, TValue> value : (values)) {
                    if(callback.apply(value)) {
                        return;
                    }
                }
            }
        }
    }

    public void rehash(Function<TKey, Integer> newHashFunction) {
        this.hashFunction = newHashFunction;
        rebuild();
    }

    private static class Entry<K, V> {
        private final K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry<K, V> entry = (Entry<K, V>) o;
            return Objects.equals(key, entry.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
}
