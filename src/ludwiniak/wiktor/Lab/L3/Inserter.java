package ludwiniak.wiktor.Lab.L3;

import java.util.NoSuchElementException;

public class Inserter {
    public static TwoWayLinkedList<String> insert(
            TwoWayLinkedList<String> list1,
            TwoWayLinkedList<String> list2,
            int beforeIndex) throws NoSuchElementException {
        TwoWayLinkedList<String> output = new TwoWayLinkedList<>();
        int i = beforeIndex;

        for(String item : list1) {
            output.add(item);
        }

        for(String item : list2) {
            output.addAt(i++, item);
        }


        return output;

    }

    public static TwoWayLinkedList<String> insert(
            TwoWayLinkedList<String> list1,
            TwoWayLinkedList<String> list2,
            String beforeElement) throws NoSuchElementException {
        return Inserter.insert(list1, list2, list1.indexOf(beforeElement));
    }
}
