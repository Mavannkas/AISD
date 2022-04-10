package ludwiniak.wiktor.Lab.L5;

public class InvertedSelectionSorter implements ISorter {
    private final IChecker checker;

    public InvertedSelectionSorter(IChecker checker) {
        this.checker = checker;
    }

    public void sort(int[] values) {
        for (int i = values.length - 1; i >= 0; i--) {
            int smallestIndex = getHighestFromIndex(values, i);
            Swap.swap(values, smallestIndex, i);
            checker.check(values);
        }
    }

    private int getHighestFromIndex(int[] values, int limit) {
        int maxValue = values[0];
        int maxIndex = 0;
        for (int i = 1; i <= limit; i++) {
            if (values[i] > maxValue) {
                maxIndex = i;
                maxValue = values[i];
            }
        }
        return maxIndex;
    }
}


