package ludwiniak.wiktor.Lab.L5;

public class BubbleSorter implements ISorter {
    private final IChecker checker;

    public BubbleSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values.length - (i + 1); j++) {
                if (values[j] > values[j + 1]) {
                    Swap.swap(values, j + 1, j);
                }
            }
            checker.check(values);
        }
    }
}
