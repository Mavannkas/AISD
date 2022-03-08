package ludwiniak.wiktor.Lab.L2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TwoWayLinkedList<T> implements IList<T> {
    @Override
    public void add(T value) {
        // TODO
    }

    @Override
    public void addAt(int index, T value) throws NoSuchElementException {
        // TODO
    }

    @Override
    public void clear() {
        // TODO
    }

    @Override
    public boolean contains(T value) {
        // TODO
        return false;

    }

    @Override
    public T get(int index) throws NoSuchElementException {
        // TODO
        return null;


    }

    @Override
    public void set(int index, T value) throws NoSuchElementException {
        // TODO
    }

    @Override
    public int indexOf(T value) {
        // TODO
        return 0;

    }

    @Override
    public boolean isEmpty() {
        // TODO
        return false;


    }

    @Override
    public T removeAt(int index) throws NoSuchElementException {
        // TODO
        return null;

    }

    @Override
    public boolean remove(T value) {
        return false;

    }

    @Override
    public int size() {
        // TODO
        return 0;
    }

    @Override
    public void print() {
        // TODO: Zaimplementuj wypisanie zawartości listy do konsoli
    }

    @Override
    public Iterator<T> iterator() {
        return new TwoWayLinkedListIterator();
    }

    private class TwoWayLinkedListIterator implements Iterator<T> {
        @Override
        public boolean hasNext() {
            // TODO
            return false;

        }

        @Override
        public T next() {
            // TODO
            return null;

        }
    }
}
