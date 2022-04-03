package ludwiniak.wiktor.Lab.L5;

public class InsertionSorter implements ISorter {
    private final IChecker checker;

    public InsertionSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        for (int i = 0; i < values.length - 1; i++) {

            for (int j = i; j >= 0; j--) {
                if (values[j + 1] < values[j]) {
                    Swap.swap(values, j + 1, j);
                }
            }
            checker.check(values);
        }
    }
}
