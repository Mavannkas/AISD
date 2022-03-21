package ludwiniak.wiktor.Lab.L3;

import ludwiniak.wiktor.Lab.L2.helpers.Function2Args;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class TwoWayLinkedList<T> implements IList<T>, Serializable {
    private static final long serialVersionUID = 1L;
    private LinkedElement<T> head;
    private LinkedElement<T> tail;
    private int length = 0;

    public TwoWayLinkedList() {

    }

    public TwoWayLinkedList(LinkedElement<T> head) {
        this.head = head;
    }

    public LinkedElement<T> getHead() {
        return head;
    }

    public LinkedElement<T> getTail() {
        return tail;
    }

    @Override
    public void add(T value) {
        LinkedElement<T> newElement = new LinkedElement<>(value);
        length++;

        if (isEmpty()) {
            head = newElement;
            tail = head;
            return;
        }

        if (length - 1 == 1) {
            head.next = tail;
        }

        newElement.prev = tail;
        tail.next = newElement;
        tail = newElement;
    }

    @Override
    public void addAt(int index, T value) throws NoSuchElementException {
        LinkedElement<T> newElement = new LinkedElement<>(value);

        if (isEmpty() && index == 0) {
            add(value);
            return;
        }

        if (index == 0) {
            newElement.next = head;
            head = newElement;
            newElement.next.prev = newElement;
            length++;
            return;
        }

        if (index == length) {
            add(value);
            return;
        }

        LinkedElement<T> element = getLinkedElement(index - 1);
        newElement.prev = element;
        newElement.next = element.next;
        newElement.next.prev =newElement;
        element.next = newElement;
        length++;
    }

    @Override
    public void clear() {
        length = 0;
        head = null;
        tail = null;
    }

    @Override
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    @Override
    public T get(int index) throws NoSuchElementException {
        return getLinkedElement(index).element;
    }

    public LinkedElement<T> getLinkedElement(int index) throws NoSuchElementException {
        if (length < index || index < 0 || isEmpty()) {
            throw new NoSuchElementException();
        }

        LinkedElement<T> outputElement;

        if (length / 2 >= index) {
            outputElement = goToElementByCallBackForward((currentElement, currentIndex) -> currentIndex == index);
        } else {
            outputElement = goToElementByCallBackBackward((currentElement, currentIndex) -> currentIndex == index);
        }

        return outputElement;
    }

    @Override
    public void set(int index, T value) throws NoSuchElementException {
        getLinkedElement(index).element = value;
    }

    @Override
    public int indexOf(T value) {
        AtomicInteger index = new AtomicInteger(-1);
        goToElementByCallBackForward((linkedElement, currentIndex) -> {
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
        return Objects.isNull(head);
    }

    @Override
    public T removeAt(int index) throws NoSuchElementException {
        if (size() <= 0) {
            throw new NoSuchElementException();
        }

        LinkedElement<T> deletedElement;
        if (index == 0 && size() == 1) {
            deletedElement = head;
            head = null;
            tail = null;
        } else if (index == 0) {
            deletedElement = head;
            head = head.next;
            head.prev = null;
        } else if (index == length - 1) {
            deletedElement = tail;
            tail.prev.next = null;
            tail = tail.prev;
        } else {
            LinkedElement<T> prevElement = getLinkedElement(index - 1);
            deletedElement = prevElement.next;
            prevElement.next = prevElement.next.next;
            prevElement.next.prev = prevElement;
        }

        length--;
        return deletedElement.element;
    }

    @Override
    public boolean remove(T value) {
        LinkedElement<T> elementToRemove = goToElementByCallBackForward((currentElement, currentIndex) -> currentElement.element == value);
        if (Objects.isNull(elementToRemove)) {
            return false;
        }

        if (Objects.isNull(elementToRemove.prev) && Objects.isNull(elementToRemove.next)) {
            head = null;
            tail = null;
        } else if (Objects.isNull(elementToRemove.prev)) {
            head = head.next;
            head.prev = null;
        } else if (Objects.isNull(elementToRemove.next)) {
            tail.prev.next = null;
            tail = tail.prev;
        } else {
            LinkedElement<T> prevElement = elementToRemove.prev;
            prevElement.next = prevElement.next.next;
            prevElement.next.prev = prevElement;
        }

        return true;
    }

    public boolean removeAll(T value) {
        int i = 0;
        while (remove(value)) {i++;}
        return i != 0;
    }

    public void save() throws IOException {
        FileOutputStream fos = new FileOutputStream("foo.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.close();
    }


    @Override
    public int size() {
        return length;
    }

    @Override
    public void print() {
        goToElementByCallBackForward((linkedElement, index) -> {
            System.out.printf("%2d %10s\n", index, linkedElement.element);
            return false;
        });

    }

    @Override
    public Iterator<T> iterator() {
        return new TwoWayLinkedListIterator();
    }

    private LinkedElement<T> goToElementByCallBackForward(Function2Args<LinkedElement<T>, Integer, Boolean> callback) throws NoSuchElementException {
        LinkedElement<T> currentElement = head;
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

    private LinkedElement<T> goToElementByCallBackBackward(Function2Args<LinkedElement<T>, Integer, Boolean> callback) throws NoSuchElementException {
        LinkedElement<T> currentElement = tail;
        int currentIndex = length - 1;

        while (!Objects.isNull(currentElement)) {
            if (callback.apply(currentElement, currentIndex)) {
                return currentElement;
            }
            currentIndex--;
            currentElement = currentElement.prev;
        }

        return null;
    }

    public static class LinkedElement<E> implements Serializable {
        private static final long serialVersionUID = 2L;
        public E element;
        public LinkedElement<E> next;
        public LinkedElement<E> prev;

        public LinkedElement(E element) {
            this.element = element;
        }
    }

    private class TwoWayLinkedListIterator implements Iterator<T> {
        private LinkedElement<T> currentElement;

        public TwoWayLinkedListIterator() {
            this.currentElement = head;
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
