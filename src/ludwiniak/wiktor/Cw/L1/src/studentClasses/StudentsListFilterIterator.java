package ludwiniak.wiktor.Cw.L1.src.studentClasses;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class StudentsListFilterIterator<T> implements Iterator<T> {
    private StudentsListIterator<T> iterator;
    private Predicate<T> predicate;

    private T nextElement = null;
    private boolean hasNextElement = true;

    public StudentsListFilterIterator(StudentsListIterator<T> iterator, Predicate<T> predicate) {
        this.iterator = iterator;
        this.predicate = predicate;
        findNextElement();
    }

    private void findNextElement() {
        hasNextElement = false;
        nextElement = null;
        while (iterator.hasNext()) {
            nextElement = iterator.next();
            hasNextElement = true;
            if(predicate.test(nextElement)) {
                return;
            }

            hasNextElement = false;
            nextElement = null;
        }
    }

    @Override
    public boolean hasNext() {
        return hasNextElement;
    }

    @Override
    public T next() {
        T nextStudent = nextElement;

        if(!hasNextElement) {
            throw new NoSuchElementException();
        }
        findNextElement();


        return nextStudent;
    }
}
