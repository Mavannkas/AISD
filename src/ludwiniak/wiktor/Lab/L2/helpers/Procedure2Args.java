package ludwiniak.wiktor.Lab.L2.helpers;

@FunctionalInterface
public interface Procedure2Args<One, Two> {
    void apply(One one, Two two);
}

