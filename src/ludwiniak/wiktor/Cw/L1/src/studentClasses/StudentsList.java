package ludwiniak.wiktor.Cw.L1.src.studentClasses;

import java.util.Iterator;

public class StudentsList<T extends WithValue> implements Iterable<T>{
    private final T[] students;

    public StudentsList(T[] students) {
        this.students = students;
    }

    @Override
    public Iterator<T> iterator() {
        return new StudentsListIterator<T>(students);
    }

    public Iterator<T> goodIterator() {
        return new StudentsListFilterIterator<T>(new StudentsListIterator<T>(students), new GoodStudentPredicate<T>());
    }

    public Iterator<T> badIterator() {
        return new StudentsListFilterIterator<T>(new StudentsListIterator<T>(students), new BadStudentPredicate<T>());
    }
}
