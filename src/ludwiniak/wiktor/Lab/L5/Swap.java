package ludwiniak.wiktor.Lab.L5;

public class Swap {
    public static  void swap(int[] values, int a, int b) {
        int temp = values[a];
        values[a] = values[b];
        values[b] = temp;
    }
}
