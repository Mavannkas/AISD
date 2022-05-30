package ludwiniak.wiktor.Lab.L11;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTests2 {
    private Graph<String> graph;

    @Test
    public void calculateShortestPath_af() {
        var distance = graph.calculateShortestPath("a", "f");
        assertEquals(6, distance);
    }

    @Test
    public void calculateShortestPath_df() {
        var distance = graph.calculateShortestPath("d", "f");
        assertEquals(1, distance);
    }

    @Test
    public void calculateShortestPath_gc() {
        var distance = graph.calculateShortestPath("g", "c");
        assertEquals(11, distance);
    }

    @Test
    public void calculateShortestPath_startNodeDoesntExist() {
        assertThrows(NoSuchElementException.class, () -> {
            graph.calculateShortestPath("z", "c");
        });
    }

    @Test
    public void calculateShortestPath_endNodeDoesntExist() {
        assertThrows(NoSuchElementException.class, () -> {
            graph.calculateShortestPath("a", "x");
        });
    }

    @BeforeEach
    public void createTestGraph() {
        this.graph = new Graph<>(createTestEdges());
    }

    private List<Edge<String>> createTestEdges() {
        return Arrays.asList(
                new Edge<>("a", "b", 2),
                new Edge<>("a", "c", 5),
                new Edge<>("b", "d", 3),
                new Edge<>("b", "e", 4),
                new Edge<>("c", "d", 5),
                new Edge<>("c", "f", 6),
                new Edge<>("d", "e", 3),
                new Edge<>("d", "f", 1),
                new Edge<>("e", "f", 4),
                new Edge<>("e", "g", 8),
                new Edge<>("e", "h", 2),
                new Edge<>("f", "g", 7),
                new Edge<>("g", "h", 1)
        );
    }
}
