package ludwiniak.wiktor.Lab.L1;

public class Drawer {
    private static final String SYMBOL = "#";
    private static final String SEPARATOR = " ";
    private static final String NEW_LINE = "\n";
    private static final String ERROR_MSG = "fail";

    public static void drawTriangle(int size) {
        if (size <= 0) {
            printError();
            return;
        }
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= i; j++) {
                printSymbol();
            }
            printNewLine();
        }
    }

    public static void drawSquare(int size) {
        if (size <= 0) {
            printError();
            return;
        }

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                if (i == 1 || i == size || j == 1 || j == size) {
                    printSymbol();
                } else {
                    printSeparator();
                }
            }
            printNewLine();
        }
    }

    public static void drawPyramid(int size) {
        if (size <= 0) {
            printError();
            return;
        }

        final int limit = size * 2 - 1;

        for (int i = 1; i <= size; i++) {
            drawPyramidLine(i, limit, size);
        }
    }

    private static void drawPyramidLine(final int i, final int limit, final int size) {
        for (int j = 1; j <= limit; j++) {
            final int border = i - 1;
            final int symbolLimit = size + border;

            if (j == size || (symbolLimit >= j && size - border <= j)) {
                printSymbol();
            } else if (!(symbolLimit <= j)) {
                printSeparator();
            } else {
                break;
            }
        }
        printNewLine();
    }

    public static void drawChristmasTree(int size) {
        if (size <= 0) {
            printError();
            return;
        }

        for (int segmentSize = 1; segmentSize <= size; segmentSize++) {
            final int limit = segmentSize * 2 - 1;

            for (int i = 1; i <= segmentSize; i++) {
                for (int z = 1; z <= size - segmentSize; z++) {
                    printSeparator();
                }
                drawPyramidLine(i, limit, segmentSize);
            }
        }
    }

    private static void printSymbol() {
        System.out.print(SYMBOL);
    }

    private static void printSeparator() {
        System.out.print(SEPARATOR);
    }

    private static void printNewLine() {
        System.out.print(NEW_LINE);
    }

    private static void printError() {
        System.out.print(ERROR_MSG + NEW_LINE);
    }
}
