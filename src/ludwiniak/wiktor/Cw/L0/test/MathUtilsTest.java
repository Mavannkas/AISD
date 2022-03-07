package ludwiniak.wiktor.Cw.L0.test;

import ludwiniak.wiktor.Cw.L0.src.MathUtils;
import ludwiniak.wiktor.Utils.NoAnswerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathUtilsTest {

    @Test
    @DisplayName("Check solution for even array")
    void nextPascalLineEven() {
        int[] input = new int[]{1, 2, 1};
        int[] output = new int[]{1, 3, 3, 1};

        Assertions.assertArrayEquals(output, MathUtils.nextPascalLine(input));
    }

    @Test()
    @DisplayName("Check solution for uneven array")
    void nextPascalLineUnEven() {
        int[] input = new int[]{1, 1};
        int[] output = new int[]{1, 2, 1};

        Assertions.assertArrayEquals(output, MathUtils.nextPascalLine(input));
    }

    @Test()
    @DisplayName("Check solution for arr1")
    void getSecondSmallest() throws NoAnswerException {
        int[] input = new int[]{1, 2};
        int output = 2;

        Assertions.assertEquals(output, MathUtils.getSecondSmallest(input));
    }

    @Test()
    @DisplayName("Check solution for arr2")
    void getSecondSmallest2() throws NoAnswerException {
        int[] input = new int[]{6, 5, 4, 3, 2, 1, 0};
        int output = 1;

        Assertions.assertEquals(output, MathUtils.getSecondSmallest(input));
    }
    @Test()
    @DisplayName("Check solution for arr4")
    void getSecondSmallest21() throws NoAnswerException {
        int[] input = new int[]{2, 5, 1, 4, 4, 2, 3, 1, 3, 5};
        int output = 2;

        Assertions.assertEquals(output, MathUtils.getSecondSmallest(input));
    }

    @Test()
    @DisplayName("Check solution for arr4")
    void getSecondSmallest22() throws NoAnswerException {
        int[] input = new int[]{5, 4, 1, 2};
        int output = 2;

        Assertions.assertEquals(output, MathUtils.getSecondSmallest(input));
    }

    @Test()
    @DisplayName("Check solution for arr4")
    void getSecondSmallest23() throws NoAnswerException {
        int[] input = new int[]{5, 4, 2, 1};
        int output = 2;

        Assertions.assertEquals(output, MathUtils.getSecondSmallest(input));
    }

    @Test()
    @DisplayName("Check solution for arr3")
    void getSecondSmallest6() throws NoAnswerException {
        int[] input = new int[]{9, 1, 7, 5, 3, 6, 1};
        int output = 3;

        Assertions.assertEquals(output, MathUtils.getSecondSmallest(input));
    }

    @Test()
    @DisplayName("Check solution for uniform data")
    void getSecondSmallest3() throws NoAnswerException {
        int[] input = new int[]{1, 1, 1, 1, 1, 1 ,1, 1};

        Assertions.assertThrows(NoAnswerException.class, ()->MathUtils.getSecondSmallest(input));
    }

    @Test()
    @DisplayName("Check solution for uniform data")
    void getSecondSmallest333() throws NoAnswerException {
        int[] input = new int[]{};

        Assertions.assertThrows(NoAnswerException.class, ()->MathUtils.getSecondSmallest(input));
    }

    @Test()
    @DisplayName("Check solution for small array")
    void getSecondSmallest4() throws NoAnswerException {
        int[] input = new int[]{1};

        Assertions.assertThrows(NoAnswerException.class, ()->MathUtils.getSecondSmallest(input));
    }

    @Test
    void nextPermutation() {
        int[] input = new int[]{3,4,2,5,1 };
        MathUtils.nextPermutation(input);
        Assertions.assertArrayEquals(new int[]{3,4,5,1,2}, input);
    }
}