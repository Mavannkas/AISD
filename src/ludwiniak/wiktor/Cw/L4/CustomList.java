package ludwiniak.wiktor.Cw.L4;

import ludwiniak.wiktor.Lab.L2.OneWayLinkedList;
import ludwiniak.wiktor.Lab.L4.IQueue;
import ludwiniak.wiktor.Lab.L4.TwoWayLinkedList;
import ludwiniak.wiktor.Lab.L4.exceptions.EmptyQueueException;
import ludwiniak.wiktor.Lab.L4.exceptions.FullQueueException;

public class CustomList<T> implements IQueue<T> {
    private final OneWayLinkedList<T> queue = new OneWayLinkedList<T>();

    public CustomList() {
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public boolean isFull() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void enqueue(T value) throws FullQueueException {
       queue.add(value);
    }
    
    @Override
    public T first() throws EmptyQueueException {
        if(isEmpty()) {
            throw new EmptyQueueException();
        }

        return queue.get(0);
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if(isEmpty()) {
            throw new EmptyQueueException();
        }

        return queue.removeAt(0);
    }

    @Override
    public int size() {
        return queue.size();
    }

}
