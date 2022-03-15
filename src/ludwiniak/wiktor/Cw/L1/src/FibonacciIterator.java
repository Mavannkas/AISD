package ludwiniak.wiktor.Cw.L1.src;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FibonacciIterator implements Iterator<Integer> {
    private int prev = 0;
    private int actual = 1;
    private final int max;
    public FibonacciIterator(int max) {
        this.max = max;
    }

    @Override
    public boolean hasNext() {
        return actual <= max;
    }

    @Override
    public Integer next() {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }

        final int output = actual;
        actual = prev + actual;
        prev = actual - prev;
        return output;
    }
}
