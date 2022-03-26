package ludwiniak.wiktor.Lab.L4;


import ludwiniak.wiktor.Lab.L4.exceptions.EmptyQueueException;
import ludwiniak.wiktor.Lab.L4.exceptions.FullQueueException;

public interface IQueue<T> {
    boolean isEmpty();
    boolean isFull();
    void enqueue(T value) throws FullQueueException;
    T first() throws EmptyQueueException;
    T dequeue() throws EmptyQueueException;
    int size();
}
