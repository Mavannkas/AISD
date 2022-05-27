package ludwiniak.wiktor.Lab.L9;

public class DisjointSetForest implements IDisjointSetStructure {
    private final int[] p;
    private final int[] rank;

    public DisjointSetForest(int size) {
        p = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++) {
            p[i] = i;
            rank[i] = 0;
        }
    }

    @Override
    public int findSet(int item) throws ItemOutOfRangeException {
        if (item >= p.length || item < 0) {
            throw new ItemOutOfRangeException();
        }
        if (item != p[item]) {
            p[item] = findSet(p[item]);
        }
        return p[item];
    }

    @Override
    public void union(int item1, int item2) throws ItemOutOfRangeException {
        link(findSet(item1), findSet(item2));
    }

    private void link(int item1, int item2) {
        if (rank[item1] > rank[item2]) {
            p[item2] = item1;
            return;
        }
        p[item1] = item2;
        if (rank[item1] == rank[item2]) {
            rank[item2] = rank[item2] + 1;
        }
    }
}
