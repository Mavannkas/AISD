package ludwiniak.wiktor.Lab.L9;
public class DisjointSetForestTests extends DisjointSetTestBase {
    protected DisjointSetForestTests() {
        super(size -> new DisjointSetForest(size));
    }
}
