package ludwiniak.wiktor.Lab.L4;

import ludwiniak.wiktor.Lab.L4.exceptions.FullStackException;

import java.util.EmptyStackException;

public interface IStack<T> {
    boolean isEmpty();
    boolean isFull();
    T top() throws EmptyStackException;
    T pop() throws EmptyStackException;
    void push(T value) throws FullStackException;
    int size();
}
