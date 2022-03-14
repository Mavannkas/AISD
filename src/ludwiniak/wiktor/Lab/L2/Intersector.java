package ludwiniak.wiktor.Lab.L2;

public class Intersector {

    private static OneWayLinkedList<Integer> output;

    public static OneWayLinkedList<Integer> intersect(
            OneWayLinkedList<Integer> list1,
            OneWayLinkedList<Integer> list2) {
        OneWayLinkedList<Integer> output = new OneWayLinkedList<>();
        for (int number : list2) {
            if(list1.contains(number)) {
                output.add(number);
            }
        }
        return output;
    }
}
