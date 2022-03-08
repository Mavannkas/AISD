package ludwiniak.wiktor.Lab.L2;

import ludwiniak.wiktor.Lab.L2.helpers.Function2Args;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


public class OneWayLinkedList<T> implements IList<T> {
    private LinkedElement<T> first;

    @Override
    public void add(T value) {
        if (isEmpty()) {
            first = new LinkedElement<>(value);
            return;
        }

        AtomicReference<LinkedElement<T>> lastElement = new AtomicReference<>();

        goToElementByCallBack(((linkedElement, index) -> {
            lastElement.set(linkedElement);
            return false;
        }));

        lastElement.get().next = new LinkedElement<T>(value);
    }

    @Override
    public void addAt(int index, T value) throws NoSuchElementException { //TODO refaktor
        LinkedElement<T> oldElement;
        LinkedElement<T> element;

        if (index == 0) {
            oldElement = first;
            first = new LinkedElement<T>(value);
            first.next = oldElement;
        } else {
            element = getLinkedElement(index - 1);
            oldElement = element.next;
            element.next = new LinkedElement<T>(value);
            element.next.next = oldElement;
        }
    }

    @Override
    public void clear() {
        first = null;
    }

    @Override
    public boolean contains(T value) {
        LinkedElement<T> element = goToElementByCallBack((linkedElement, index) -> value.equals(linkedElement.element));

        return !Objects.isNull(element);
    }

    @Override
    public T get(int index) throws NoSuchElementException {
        return getLinkedElement(index).element;
    }

    private LinkedElement<T> getLinkedElement(int index) throws NoSuchElementException {
        if (size() + 1 < index) { //TODO
            throw new NoSuchElementException();
        }

        return goToElementByCallBack((element, currentIndex) -> index == currentIndex);
    }

    private LinkedElement<T> goToElementByCallBack(Function2Args<LinkedElement<T>, Integer, Boolean> callback) throws NoSuchElementException {
        LinkedElement<T> currentElement = first;
        int currentIndex = 0;

        while (!Objects.isNull(currentElement)) {
            if (callback.apply(currentElement, currentIndex)) {
                return currentElement;
            }
            currentIndex++;
            currentElement = currentElement.next;

        }

        return null;
    }

    @Override
    public void set(int index, T value) throws NoSuchElementException {
        // TODO
    }

    @Override
    public int indexOf(T value) {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return Objects.isNull(first);
    }

    @Override
    public T removeAt(int index) throws NoSuchElementException {
        return null;
    }

    @Override
    public boolean remove(T value) {
        return false;
    }

    @Override
    public int size() {
        AtomicInteger counter = new AtomicInteger(-1);

        goToElementByCallBack(((linkedElement, index) -> {
            counter.set(index);
            return false;
        }));

        return counter.get() == -1 ? 0 : counter.get() + 1;
    }

    @Override
    public void print() {
        // TODO: Zaimplementuj wypisanie zawartości listy do konsoli
    }

    @Override
    public Iterator<T> iterator() {
        return new OneWayLinkedListIterator();
    }

    private static class LinkedElement<E> {
        private final E element;
        private LinkedElement<E> next;

        public LinkedElement(E element) {
            this.element = element;
        }
    }

    private class OneWayLinkedListIterator implements Iterator<T> {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public T next() {
            return null;
        }
    }

}
