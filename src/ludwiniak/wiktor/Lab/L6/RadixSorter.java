package ludwiniak.wiktor.Lab.L6;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

public class RadixSorter implements ISorter {
    private final IChecker checker;

    public RadixSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        Map map = new HashMap(11);
        // TODO
        for (int i = 0; i < 3; i++) {
            countingSortByDigitPlace(values, i);
            checker.check(values);
        }

        // Pamiętaj o wywołaniu checker.check(values); po kazdym wywołaniu zewnętrznej petli
    }

    private void countingSortByDigitPlace(int[] values, int place) {
        HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();
        fillMap(map);
        int fullDigit = (int) Math.pow(10, place);
        for (int value : values) {
            if (value < fullDigit) {
                map.get(-1).addLast(value);
            } else {
                map.get((value / fullDigit) % 10 ).addLast(value);
            }
        }

        copyMapToSourceArray(map, values);
    }

    private void copyMapToSourceArray(HashMap<Integer, LinkedList<Integer>> map, int[] values) {
        int index = 0;
        for(int i = -1; i <= 10; i++) {
            LinkedList<Integer> numbers = map.get(i);
            while (!numbers.isEmpty()) {
                values[index++] = numbers.removeFirst();
            }
        }
    }

    private void fillMap(HashMap<Integer, LinkedList<Integer>> map) {
        for(int i = -1; i <= 10; i++) {
            map.put(i, new LinkedList<>());
        }
    }
}
