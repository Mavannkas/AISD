package ludwiniak.wiktor.Lab.L9;

public class DisjointSetLinkedList implements IDisjointSetStructure {
    private final Node[] p;

    public DisjointSetLinkedList(int size) {
        p = new Node[size];

        for (int i = 0; i < size; i++) {
            p[i] = new Node(i);
        }
    }

    @Override
    public int findSet(int item) throws ItemOutOfRangeException {
        if (item >= p.length || item < 0) {
            throw new ItemOutOfRangeException();
        }
        return findSet(p[item]).id;
    }


    public Node findSet(Node item) {
        if (item.id != item.next.id) {
            item.next = findSet(item.next);
        }
        return item.next;
    }

    @Override
    public void union(int item1, int item2) throws ItemOutOfRangeException {
        link(findSet(item1), findSet(item2));
    }

    private void link(int item1, int item2) {
        if (p[item1].rank > p[item2].rank) {
            p[item2].next = p[item1];
            return;
        }

        p[item1].next = p[item2];
        if (p[item1].rank == p[item2].rank) {
            p[item2].rank = p[item2].rank + 1;
        }

    }

    private static class Node {
        int id;
        int rank = 0;
        Node next = this;

        public Node(int id) {
            this.id = id;
        }
    }
}
