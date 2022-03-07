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

        drawRectangle(size, size);
    }

    public static void drawPyramid(int size) {
        if (size <= 0) {
            printError();
            return;
        }
        for (int i = 1; i <= size; i++) {
            drawPyramidLine(i, size);
        }
    }

    private static void drawPyramidLine(final int i, final int size) {
        final int border = i - 1;
        final int symbolLastIndex = size + border;
        final int symbolStartIndex = size - border;

        for (int j = 1; j <= symbolLastIndex ; j++) {
            if (symbolStartIndex <= j) {
                printSymbol();
            } else {
                printSeparator();
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
            for (int i = 1; i <= segmentSize; i++) {
                for (int z = 1; z <= size - segmentSize; z++) {
                    printSeparator();
                }
                drawPyramidLine(i, segmentSize);
            }
        }
    }

    public static void drawRectangle(int width, int height) {
        if (width <= 0 || height <= 0) {
            printError();
            return;
        }

        for (int row = 1; row <= height; row++) {
            for (int col = 1; col <= width; col++) {
                if (row == 1 || row == height || col == 1 || col == width) {
                    printSymbol();
                } else {
                    printSeparator();
                }
            }
            printNewLine();
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
