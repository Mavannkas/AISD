package ludwiniak.wiktor.Lab.L2;

import ludwiniak.wiktor.Lab.L2.helpers.Function2Args;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


public class OneWayLinkedList<T> implements IList<T> {
    private LinkedElement<T> first;
    private int length = 0;
    @Override
    public void add(T value) {
        LinkedElement<T> newElement = new LinkedElement<>(value);
        length++;

        if (isEmpty()) {
            first = newElement;
            return;
        }

        AtomicReference<LinkedElement<T>> lastElement = new AtomicReference<>();
        goToElementByCallBack(((linkedElement, index) -> {
            lastElement.set(linkedElement);
            return false;
        }));

        lastElement.get().next = newElement;
    }

    @Override
    public void addAt(int index, T value) throws NoSuchElementException { //TODO refaktor
        LinkedElement<T> oldElement;
        LinkedElement<T> element;

        if (index == 0 && !isEmpty()) {
            oldElement = first;
            first = new LinkedElement<T>(value);
            first.next = oldElement;
        } else {
            element = getLinkedElement(index - 1);
            oldElement = element.next;
            element.next = new LinkedElement<T>(value);
            element.next.next = oldElement;
        }

        length++;
    }

    @Override
    public void clear() {
        length = 0;
        first = null;
    }

    @Override
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    @Override
    public T get(int index) throws NoSuchElementException {
        return getLinkedElement(index).element;
    }

    private LinkedElement<T> getLinkedElement(int index) throws NoSuchElementException {
        if (size() + 1 < index || index < 0) { //TODO
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
        LinkedElement<T> newElement = new LinkedElement<T>(value);

        if (index == 0 && !isEmpty()) {
            newElement.next = first.next;
            first = newElement;
        } else {
            LinkedElement<T> prevElement = getLinkedElement(index - 1);
            LinkedElement<T> currentElement = prevElement.next;
            newElement.next = currentElement.next;
            prevElement.next = newElement;
        }
    }

    @Override
    public int indexOf(T value) {
        AtomicInteger index = new AtomicInteger(-1);
        LinkedElement<T> element = goToElementByCallBack((linkedElement, currentIndex) -> {
            if(linkedElement.element == value) {
                index.set(currentIndex);
                return true;
            }
            return false;
        });

        return index.get();
    }

    @Override
    public boolean isEmpty() {
        return Objects.isNull(first);
    }

    @Override
    public T removeAt(int index) throws NoSuchElementException {
        return null; //TODO
    }

    @Override
    public boolean remove(T value) {
        return false; //TODO
    }

    @Override
    public int size() {
        return length;
    }

    public int countSize() {
        AtomicInteger counter = new AtomicInteger(-1);

        goToElementByCallBack(((linkedElement, index) -> {
            counter.set(index);
            return false;
        }));

        return counter.get() == -1 ? 0 : counter.get() + 1;
    }

    @Override
    public void print() {
        goToElementByCallBack(((linkedElement, index) -> {
            System.out.printf("%2d %10s\n", index, linkedElement);
            return false;
        }));
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
