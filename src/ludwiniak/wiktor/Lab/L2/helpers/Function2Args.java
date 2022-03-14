package ludwiniak.wiktor.Lab.L2.helpers;

@FunctionalInterface
public interface Function2Args<One, Two, Out> {
    Out apply(One one, Two two);
}

