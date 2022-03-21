package ludwiniak.wiktor.Lab.L3;

import java.util.Iterator;

public class Distincter {
    public static TwoWayLinkedList<Integer> distinct(TwoWayLinkedList<Integer> list)
    {
        TwoWayLinkedList<Integer> output = new TwoWayLinkedList<>();
        Iterator<Integer> iterator = list.iterator();

        int lastElement = iterator.next();
        output.add(lastElement);

        while(iterator.hasNext()) {
            int element = iterator.next();
            if(element != lastElement) {
                output.add(element);
                lastElement = element;
            }
        }

        return output;
    }
}
