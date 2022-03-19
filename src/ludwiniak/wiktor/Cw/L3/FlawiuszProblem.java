package ludwiniak.wiktor.Cw.L3;

import ludwiniak.wiktor.Lab.L2.OneWayLinkedList;

import java.util.Iterator;

public class FlawiuszProblem {
    public static void startSimulation(int N, int K) {
        OneWayLinkedList<Integer> list = generateList(N);

        int startNumber = (K - 1) % (N + 1);
        Iterator<Integer> iterator = list.endlessIterator();

        moveIterator(iterator, startNumber - 1);
        deleteItem(list, iterator);

        while (list.size() >= K) {
            moveIterator(iterator, startNumber);
            deleteItem(list, iterator);
        }

        System.out.println("Bezpieczne miejsca");
        for (int index : list) {
            System.out.println(index);
        }
    }

    private static void moveIterator(Iterator<Integer> iterator, int end) {
        for (int i = 0; i < end; i++) {
            iterator.next();
        }
    }

    private static void deleteItem(OneWayLinkedList<Integer> list, Iterator<Integer> iterator) {
        int deletedIndex = iterator.next();
        System.out.println("usunięto " + deletedIndex);
        list.remove(deletedIndex);
    }

    private static OneWayLinkedList<Integer> generateList(int n) {
        OneWayLinkedList<Integer> list = new OneWayLinkedList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        return list;
    }
}

