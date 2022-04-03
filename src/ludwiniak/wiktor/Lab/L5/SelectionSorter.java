package ludwiniak.wiktor.Lab.L5;

public class SelectionSorter implements ISorter {
    private final IChecker checker;

    public SelectionSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        for (int i = 0; i < values.length; i++) {
            int smallestIndex = getSmallestFromIndex(values, i);
            Swap.swap(values, smallestIndex, i);
            checker.check(values);
        }
    }

    private int getSmallestFromIndex(int[] values, int start) {
        int minValue = values[start];
        int minIndex = start;
        for (int i = start + 1; i < values.length; i++) {
            if (values[i] < minValue) {
                minIndex = i;
                minValue = values[i];
            }
        }
        return minIndex;
    }
}
