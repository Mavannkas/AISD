package ludwiniak.wiktor.Lab.L4;


import ludwiniak.wiktor.Lab.L4.exceptions.EmptyQueueException;
import ludwiniak.wiktor.Lab.L4.exceptions.FullQueueException;
import ludwiniak.wiktor.Lab.L4.exceptions.FullStackException;

public class Inverter {
    public static <T> IQueue<T> invert(IQueue<T> queue) throws EmptyQueueException, FullQueueException {
        ArrayStack<T> subStack = new ArrayStack<>(queue.size());
        try {

            while (!queue.isEmpty()) {
                subStack.push(queue.dequeue());
            }
            while (!subStack.isEmpty()) {
                queue.enqueue(subStack.pop());
            }

        } catch (FullStackException e) {
            System.out.println(e.getMessage());
        }
        return queue;
    }
}
