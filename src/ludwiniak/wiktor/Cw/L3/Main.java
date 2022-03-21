package ludwiniak.wiktor.Cw.L3;

import ludwiniak.wiktor.Lab.L2.OneWayLinkedList;

public class Main {
    public static void main(String[] args) {
        OneWayLinkedList<Integer> l1 = new OneWayLinkedList<>();
        l1.add(1);
        l1.add(3);
        l1.add(5);
        l1.add(6);
        l1.add(7);
        l1.add(9);
        OneWayLinkedList<Integer> l2 = new OneWayLinkedList<>();
        l2.add(0);
        l2.add(2);
        l2.add(6);
        l2.add(10);
        l2.add(12);

        new OneWayLinkedList<Integer>(MergeOneWayLists.merge(l1.getFirst(), l2.getFirst())).print();

    }
}

// Zaimplementuj FIFO
// Zaimplementuj STOS
// Zaimplementuj MergeOneWayLists
// Z tailem