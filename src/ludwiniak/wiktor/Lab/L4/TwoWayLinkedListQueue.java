package ludwiniak.wiktor.Lab.L4;

import ludwiniak.wiktor.Lab.L4.exceptions.*;

public class TwoWayLinkedListQueue<T> implements IQueue<T> {
    private final TwoWayLinkedList<T> queue = new TwoWayLinkedList<T>();
    private int capacity;
    public TwoWayLinkedListQueue(int capacity) {
        this.capacity  = capacity;
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public boolean isFull() {
        return queue.size() == capacity;
    }

    @Override
    public void enqueue(T value) throws FullQueueException {
        if(isFull()) {
            throw new FullQueueException();
        }

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
