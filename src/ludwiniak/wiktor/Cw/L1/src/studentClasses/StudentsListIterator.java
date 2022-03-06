package ludwiniak.wiktor.Cw.L1.src.studentClasses;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StudentsListIterator<T> implements Iterator<T> {
    private final T[] students;
    private int pos = 0;

    public StudentsListIterator(T[] students) {
        this.students = students;
    }

    @Override
    public boolean hasNext() {
        return pos < students.length;
    }

    @Override
    public T next() {
        if(hasNext()) {
            return students[pos++];
        }

        throw new NoSuchElementException();
    }
}
