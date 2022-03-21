package ludwiniak.wiktor.Cw.L3;

import ludwiniak.wiktor.Lab.L2.LinkedElement;
import ludwiniak.wiktor.Lab.L2.OneWayLinkedList;

import java.util.Objects;

public class MergeOneWayLists {
    public static LinkedElement<Integer> merge(LinkedElement<Integer> list1, LinkedElement<Integer> list2) {
        if(Objects.isNull(list1)) {
            return list2;
        }

        if(Objects.isNull(list2)) {
            return list1;
        }

        if(list1.element < list2.element) {
            list1.next = merge(list1.next, list2);
            return list1;
        } else {
            list2.next = merge(list1, list2.next);
            return list2;
        }
    }
}
