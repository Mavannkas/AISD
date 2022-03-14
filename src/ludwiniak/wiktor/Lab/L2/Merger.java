package ludwiniak.wiktor.Lab.L2;

import java.util.LinkedList;

public class Merger {
    private static OneWayLinkedList<Integer> mergeList;
    public static OneWayLinkedList<Integer> merge(
            OneWayLinkedList<Integer> list1,
            OneWayLinkedList<Integer> list2) {
        mergeList = new OneWayLinkedList<>();
        copyElementsFrom(list1);
        copyElementsFrom(list2);
        sortList();
        return mergeList;

    }

    private static void sortList() {
        for(int i = 0; i < mergeList.size(); i++) {
            for(int j = 0; j < mergeList.size() - 1 - i; j++) {
                if(mergeList.get(j) > mergeList.get(j + 1)) {
                    swapElements(j, j + 1);
                }
            }
        }
    }

    private static void swapElements(int j, int i) {
        mergeList.addAt(i, mergeList.removeAt(j));
    }

    private static void copyElementsFrom(OneWayLinkedList<Integer> list) {
        for(int number : list) {
            mergeList.add(number);
        }
    }

}
