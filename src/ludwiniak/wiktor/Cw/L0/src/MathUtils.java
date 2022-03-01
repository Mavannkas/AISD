package ludwiniak.wiktor.Cw.L0.src;

import ludwiniak.wiktor.Utils.NoAnswerException;

import java.lang.reflect.Array;
import java.util.Objects;

public class MathUtils {
    public static int[] nextPascalLine(final int[] prevPascalLine) {
        final int len = prevPascalLine.length;
        final int[] output = new int[len + 1];
        output[0] = 1;
        output[output.length - 1] = 1;

        for (int i = 0; i < len / 2; i++) {
            final int calculatedValue = prevPascalLine[i] + prevPascalLine[i + 1];
            output[i + 1] = calculatedValue;
            output[output.length - (i + 2)] = calculatedValue;
        }

        return output;
    }

    public static int getSecondSmallest(int[] inputArray) throws NoAnswerException {
        int smallest = inputArray[0];
        Integer secondSmallest = null;

        for (int number : inputArray) {
            if (number < smallest) {
                secondSmallest = smallest;
                smallest = number;
            } else if ((Objects.isNull(secondSmallest) || number < secondSmallest) && number != smallest) {
                secondSmallest = number;
            }
        }

        if (Objects.isNull(secondSmallest)) {
            throw new NoAnswerException();
        }

        return secondSmallest;
    }

    public static boolean nextPermutation(final int[] numbers) {
        final int lastIndex = numbers.length - 1;
        int prefix = lastIndex;
        while (prefix > 0 && numbers[prefix - 1] >= numbers[prefix--]) ;

        if (++prefix == 0) return false;

        int suffix = lastIndex;
        while (suffix > 0 && numbers[suffix--] < numbers[prefix]) ;

        swap(numbers, prefix - 1, ++suffix);

        int arrayEnd = lastIndex;
        while (prefix++ < arrayEnd--)
            swap(numbers, prefix, arrayEnd);

        return true;
    }

    private static void swap(final int[] arr, final int a, final int b) {
        final int store = arr[a];
        arr[a] = arr[b];
        arr[b] = store;
    }
}
