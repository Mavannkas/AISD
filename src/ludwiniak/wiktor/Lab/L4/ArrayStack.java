package ludwiniak.wiktor.Lab.L4;

import ludwiniak.wiktor.Lab.L4.exceptions.FullStackException;

import java.util.EmptyStackException;
import java.util.Stack;

public class ArrayStack<T> implements IStack<T>{
    private T[] array;
    private int lastIndex = 0;

    public ArrayStack(int capacity) {
        array = (T[]) new Object[capacity];
    }

    @Override
    public boolean isEmpty() {
        return lastIndex == 0;
    }

    @Override
    public boolean isFull() {
        return array.length == lastIndex;
    }

    @Override
    public T top() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return array[lastIndex - 1];
    }

    @Override
    public T pop() throws EmptyStackException {
        if (isEmpty()) {
           throw new EmptyStackException();
        }

        T element = array[--lastIndex];
        array[lastIndex] = null;

        return element;
    }

    @Override
    public void push(T value) throws FullStackException {
        if(isFull()) {
            throw new FullStackException();
        }
        array[lastIndex++] = value;
    }

    @Override
    public int size() {
        return lastIndex;
    }
}
