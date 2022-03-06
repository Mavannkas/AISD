package ludwiniak.wiktor.Cw.L1.src.studentClasses;

import java.util.function.Predicate;

public class BadStudentPredicate<T extends WithValue> implements Predicate<T> {
    @Override
    public boolean test(T student) {
        return student.getValue() < 3.;
    }
}
