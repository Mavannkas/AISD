package ludwiniak.wiktor.Lab.L2;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InserterTests {
    @org.junit.jupiter.api.Test
    void mergeList2Then1ByIndex() {
        var list1 = new TwoWayLinkedList<String>();
        list1.add("d");
        list1.add("e");
        list1.add("f");

        var list2 = new TwoWayLinkedList<String>();
        list2.add("a");
        list2.add("b");
        list2.add("c");

        var mergedList = Inserter.insert(list1, list2, 0);
        assertEquals("a", mergedList.get(0));
        assertEquals("b", mergedList.get(1));
        assertEquals("c", mergedList.get(2));
        assertEquals("d", mergedList.get(3));
        assertEquals("e", mergedList.get(4));
        assertEquals("f", mergedList.get(5));
    }

    @org.junit.jupiter.api.Test
    void mergeList2Then1ByValue() {
        var list1 = new TwoWayLinkedList<String>();
        list1.add("d");
        list1.add("e");
        list1.add("f");

        var list2 = new TwoWayLinkedList<String>();
        list2.add("a");
        list2.add("b");
        list2.add("c");

        var mergedList = Inserter.insert(list1, list2, "d");
        assertEquals("a", mergedList.get(0));
        assertEquals("b", mergedList.get(1));
        assertEquals("c", mergedList.get(2));
        assertEquals("d", mergedList.get(3));
        assertEquals("e", mergedList.get(4));
        assertEquals("f", mergedList.get(5));
    }

    @org.junit.jupiter.api.Test
    void mergeList1InMiddleOfList2ByIndex() {
        var list1 = new TwoWayLinkedList<String>();
        list1.add("d");
        list1.add("e");
        list1.add("f");

        var list2 = new TwoWayLinkedList<String>();
        list2.add("a");
        list2.add("b");
        list2.add("c");

        var mergedList = Inserter.insert(list1, list2, 1);
        assertEquals("d", mergedList.get(0));
        assertEquals("a", mergedList.get(1));
        assertEquals("b", mergedList.get(2));
        assertEquals("c", mergedList.get(3));
        assertEquals("e", mergedList.get(4));
        assertEquals("f", mergedList.get(5));
    }

    @org.junit.jupiter.api.Test
    void mergeList1InMiddleOfList2ByValue() {
        var list1 = new TwoWayLinkedList<String>();
        list1.add("d");
        list1.add("e");
        list1.add("f");

        var list2 = new TwoWayLinkedList<String>();
        list2.add("a");
        list2.add("b");
        list2.add("c");

        var mergedList = Inserter.insert(list1, list2, "e");
        assertEquals("d", mergedList.get(0));
        assertEquals("a", mergedList.get(1));
        assertEquals("b", mergedList.get(2));
        assertEquals("c", mergedList.get(3));
        assertEquals("e", mergedList.get(4));
        assertEquals("f", mergedList.get(5));
    }
}
