package ludwiniak.wiktor.Lab.L2;

import ludwiniak.wiktor.Lab.L2.helpers.Function2Args;
import ludwiniak.wiktor.Lab.L2.helpers.Procedure2Args;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


public class OneWayLinkedList<T> implements IList<T>, Iterable<T> {
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
        invokeForAllElements(((linkedElement, index) -> lastElement.set(linkedElement)));

        lastElement.get().next = newElement;
    }

    @Override
    public void addAt(int index, T value) throws NoSuchElementException {
        LinkedElement<T> newNode = new LinkedElement<T>(value);
        if (index == 0) {
            newNode.next = first;
            first = newNode;
        } else {
            LinkedElement<T> element = getLinkedElement(index - 1);
            newNode.next = element.next;
            element.next = newNode;
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
        if (size() + 1 < index || index < 0 || isEmpty()) {
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

    private void invokeForAllElements(Procedure2Args<LinkedElement<T>, Integer> callback) {
        goToElementByCallBack(((linkedElement, index) -> {
            callback.apply(linkedElement, index);
            return false;
        }));
    }

    @Override
    public void set(int index, T value) throws NoSuchElementException {
        if (index == 0) {
            first.element = value;
        } else {
            getLinkedElement(index).element = value;
        }
    }

    @Override
    public int indexOf(T value) {
        AtomicInteger index = new AtomicInteger(-1);
        goToElementByCallBack((linkedElement, currentIndex) -> {
            if (linkedElement.element == value) {
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
        if (size() <= 0) {
            throw new NoSuchElementException();
        }

        LinkedElement<T> deletedElement;
        if (index == 0 && size() == 1) {
            deletedElement = first;
            first = null;
        } else if (index == 0) {
            deletedElement = first;
            first = first.next;
        } else {
            LinkedElement<T> prevElement = getLinkedElement(index - 1);
            deletedElement = prevElement.next;
            prevElement.next = prevElement.next.next;
        }

        length--;
        return deletedElement.element;
    }

    @Override
    public boolean remove(T value) {
        try {
            removeAt(indexOf(value));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int size() {
        return length;
    }

    public int countSize() {
        AtomicInteger counter = new AtomicInteger(-1);

        invokeForAllElements(((linkedElement, index) -> counter.set(index)));

        return counter.get() + 1;
    }

    @Override
    public void print() {
        invokeForAllElements(((linkedElement, index) -> System.out.printf("%2d %10s\n", index, linkedElement.element)));
    }

    @Override
    public Iterator<T> iterator() {
        return new OneWayLinkedListIterator();
    }

    private static class LinkedElement<E> {
        private E element;
        private LinkedElement<E> next;

        public LinkedElement(E element) {
            this.element = element;
        }
    }

    private class OneWayLinkedListIterator implements Iterator<T> {
        private LinkedElement<T> currentElement;

        public OneWayLinkedListIterator() {
            this.currentElement = first;
        }

        @Override
        public boolean hasNext() {
            return !Objects.isNull(currentElement);
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T output = currentElement.element;
            currentElement = currentElement.next;

            return output;
        }
    }

}
