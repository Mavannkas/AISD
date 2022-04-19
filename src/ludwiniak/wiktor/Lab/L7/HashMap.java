package ludwiniak.wiktor.Lab.L7;

import ludwiniak.wiktor.Lab.L4.TwoWayLinkedList;
import org.junit.platform.commons.util.CollectionUtils;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;

public class HashMap<TKey, TValue> {
    private final int initialSize;
    private int size;
    private double loadFactor;
    private int elementsCount = 0;
    private Function<TKey, Integer> hashFunction;

    private TwoWayLinkedList<Entry<TKey, TValue>>[] array;

//    @SuppressWarnings("unchecked")
    public HashMap(int initialSize, double loadFactor, Function<TKey, Integer> hashFunction) {
        this.initialSize = initialSize;
        this.size = initialSize;
        this.loadFactor = loadFactor;
        this.hashFunction = hashFunction;

        array = new TwoWayLinkedList[size];
    }

    public void add(TKey key, TValue value) throws DuplicateKeyException {
        TwoWayLinkedList<Entry<TKey, TValue>> entries = getEntries(key);
        Entry<TKey, TValue> entry= new Entry<>(key, value);

        if(entries == null) {
            entries = new TwoWayLinkedList<>();
            array[hashFunction.apply(key) % size] = entries;
        }

        if(entries.contains(entry)) {
            throw new DuplicateKeyException();
        }

        elementsCount++;
        entries.add(entry);
        tryToResize();
    }

    private void tryToResize() {
        if(elementsCount / (double)size >= loadFactor) {
            changeSize();
        }
    }

    @SuppressWarnings("unchecked")
    public void clear() {
        elementsCount = 0;
        size = initialSize;
        array = new TwoWayLinkedList[size];
    }

    public boolean containsKey(TKey key) {
        for(TwoWayLinkedList<Entry<TKey, TValue>> values : array) {
            if(values != null) {
                for(Entry<TKey, TValue> entryValue: values) {
                    if(entryValue.key.equals(key)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean containsValue(TValue value) {
        for(TwoWayLinkedList<Entry<TKey, TValue>> values : array) {
            if(values != null) {
                for(Entry<TKey, TValue> entryValue: values) {
                    if(entryValue.value.equals(value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int elements() {
        return elementsCount;
    }

    public TValue get(TKey key) throws NoSuchElementException {
        TwoWayLinkedList<Entry<TKey, TValue>> entries = getEntries(key);
        if(entries == null) {
            throw new NoSuchElementException();
        }
        for(Entry<TKey, TValue> entry: entries) {
            if(entry.key.equals(key)) {
                return entry.value;
            }
        }
        throw new NoSuchElementException();
    }

    public void put(TKey key, TValue value) {
        TwoWayLinkedList<Entry<TKey, TValue>> entries = getEntries(key);
        Entry<TKey, TValue> entry= new Entry<>(key, value);

        if(entries == null) {
            entries = new TwoWayLinkedList<>();
            array[hashFunction.apply(key) % size] = entries;
        }

        int elementIndex = entries.indexOf(entry);
        if(elementIndex != -1) {
            entries.get(elementIndex).value = value;
        } else {
            elementsCount++;
            entries.add(entry);
            tryToResize();
        }
    }

    public TValue remove(TKey key) {
        TwoWayLinkedList<Entry<TKey, TValue>> entries = getEntries(key);
        if(entries == null) {
            return null;
        }

        for(Entry<TKey, TValue> entry: entries) {
            if(entry.key.equals(key)) {
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

    private void changeSize() {
        size *= 2;
        HashMap<TKey, TValue> newHashMap = new HashMap<>(size, loadFactor, hashFunction);

        for(TwoWayLinkedList<Entry<TKey, TValue>> values : array) {
            if(values != null) {
                for(Entry<TKey, TValue> value:  (values)) {
                    try {
                        newHashMap.add(value.getKey(), value.getValue());
                    } catch (DuplicateKeyException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        array = newHashMap.array;
    }

    private class Entry<K, V> {
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
