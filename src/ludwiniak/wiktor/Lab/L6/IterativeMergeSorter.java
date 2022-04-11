package ludwiniak.wiktor.Lab.L6;


public class IterativeMergeSorter implements ISorter {
    private final IChecker checker;

    public IterativeMergeSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        int step = 1;
        do {
            step *= 2;
            sortByStepSize(values, step);
            checker.check(values);
        } while (step < values.length);
    }

    private void sortByStepSize(int[] values, int step) {
        for (int i = 0; i + step <= values.length || i == 0; i += step) {
            merge(values, i, step);
        }
    }

    private void merge(int[] values, int startIndex, int step) {
        int[] mergedArray = mergeArray(values, startIndex, step);
        System.arraycopy(mergedArray, 0, values, startIndex, Math.min(step, values.length));
    }

    private int[] mergeArray(int[] values, int startIndex, int step) {
        int leftArrayIndex = startIndex;
        int rightArrayIndex = startIndex + step / 2;

        int lastIndexForLeftArray = startIndex + step / 2;
        int lastIndexForRightArray = startIndex + step;

        int[] output = new int[step];
        int outputIndex = 0;

        if (startIndex + step >= values.length) {
            lastIndexForRightArray = values.length;
            output = new int[values.length];
        }

        while (leftArrayIndex < lastIndexForLeftArray && rightArrayIndex < lastIndexForRightArray) {
            if (values[leftArrayIndex] < values[rightArrayIndex]) {
                output[outputIndex++] = values[leftArrayIndex++];
            } else {
                output[outputIndex++] = values[rightArrayIndex++];
            }
        }

        while (leftArrayIndex < lastIndexForLeftArray) {
            output[outputIndex++] = values[leftArrayIndex++];
        }

        while (rightArrayIndex < lastIndexForRightArray) {
            output[outputIndex++] = values[rightArrayIndex++];
        }

        return output;
    }
}
