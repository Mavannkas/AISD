package ludwiniak.wiktor.Lab.L10;

import ludwiniak.wiktor.Lab.L9.DisjointSetLinkedList;
import ludwiniak.wiktor.Lab.L9.ItemOutOfRangeException;

import java.util.*;

public class Graph<T> {
    private final List<Node<T>> nodes = new LinkedList<>();
    private final List<List<Integer>> weights = new LinkedList<>();

    public Graph(List<Edge<T>> edges) {
        getNodes(edges);
        fillWeights();
        addWeights(edges);
    }

    private void addWeights(List<Edge<T>> edges) {
        for (Edge<T> edge : edges) {
            weights.get(indexOf(edge.getSource())).set(indexOf(edge.getDestination()), edge.getWeight());
        }
    }

    private void fillWeights() {
        for (int i = 0; i < nodes.size(); i++) {
            weights.add(new ArrayList<>(Collections.nCopies(nodes.size(), 0)));
        }
    }

    private void getNodes(List<Edge<T>> edges) {
        for (Edge<T> edge : edges) {
            if (!contains(edge.getSource())) {
                nodes.add(new Node<>(edge.getSource()));
            }

            if (!contains(edge.getDestination())) {
                nodes.add(new Node<>(edge.getDestination()));
            }
        }
    }

    public String depthFirst(T startNode) throws NoSuchElementException {
        resetNodes();

        int startIndex = indexOf(startNode);
        if (startIndex == -1) {
            throw new NoSuchElementException();
        }

        return depthFirst(nodes.get(startIndex));
    }

    private String depthFirst(Node<T> node) {
        StringBuilder out = new StringBuilder(node.name.toString());
        Map<Node<T>, Integer> connections = getMapOfConnections(node);

        node.state = State.GREY;

        while (!connections.isEmpty()) {
            Node<T> innerNode = getNodeFromMap(connections);

            if (innerNode.state == State.WHITE) {
                out.append(", ").append(depthFirst(innerNode));
            }
        }

        node.state = State.BLACK;
        return out.toString();
    }

    private Map<Node<T>, Integer> getMapOfConnections(Node<T> node) {
        List<Integer> allConnections = weights.get(indexOf(node.name));
        Map<Node<T>, Integer> output = new HashMap<>();

        for (int i = 0; i < allConnections.size(); i++) {
            if (allConnections.get(i) != 0) {
                output.put(nodes.get(i), allConnections.get(i));
            }
        }

        return output;
    }

    private Node<T> getNodeFromMap(Map<Node<T>, Integer> nodesMap) {
        ArrayList<Node<T>> keys = new ArrayList<>(nodesMap.keySet());
        ArrayList<Integer> values = new ArrayList<>(nodesMap.values());

        Node<T> minNode = keys.get(0);
        int minValue = values.get(0);

        for (int i = 1; i < keys.size(); i++) {
            if (minValue > values.get(i)) {
                minValue = values.get(i);
                minNode = keys.get(i);
            }
        }

        nodesMap.remove(minNode);
        return minNode;
    }

    private void resetNodes() {
        for (Node<T> node : nodes) {
            node.reset();
        }
    }

    public String breadthFirst(T startNode) throws NoSuchElementException {
        resetNodes();

        int startIndex = indexOf(startNode);
        if (startIndex == -1) {
            throw new NoSuchElementException();
        }

        Queue<Node<T>> queue = new ArrayDeque<>();
        StringBuilder out = new StringBuilder();

        queue.offer(nodes.get(startIndex));

        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();

            node.state = State.BLACK;
            out.append(", ").append(node.name.toString());

            enqueueNodes(node, queue);
        }

        return out.substring(2);
    }

    private void enqueueNodes(Node<T> parenNode, Queue<Node<T>> queue) {
        Map<Node<T>, Integer> connections = getMapOfConnections(parenNode);

        while (!connections.isEmpty()) {
            Node<T> innerNode = getNodeFromMap(connections);

            if (innerNode.state == State.WHITE) {
                innerNode.state = State.GREY;
                queue.offer(innerNode);
            }
        }
    }

    public int connectedComponents() {
        try {

            DisjointSetLinkedList forest = new DisjointSetLinkedList(nodes.size());
            for (int i = 0; i < nodes.size(); i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    if (i == j) continue;

                    if (weights.get(i).get(j) > 0 && forest.findSet(i) != forest.findSet(j)) {
                        forest.union(i, j);
                    }
                }
            }
            return countConnections(forest);
        } catch (ItemOutOfRangeException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private int countConnections(DisjointSetLinkedList forest) {
        List<Integer> elements = new LinkedList<>();

        try {
            for (int i = 0; i < nodes.size(); i++) {
                int representative = forest.findSet(i);
                if (!elements.contains(representative)) {
                    elements.add(representative);
                }
            }
        } catch (ItemOutOfRangeException e) {
            e.printStackTrace();
        }

        return elements.size();
    }

    private int indexOf(T value) {
        for (int i = 0; i < nodes.size(); i++) {
            if (Objects.equals(nodes.get(i).name, value)) {
                return i;
            }
        }

        return -1;
    }

    private boolean contains(T value) {
        return indexOf(value) >= 0;
    }

    private enum State {
        WHITE,
        GREY,
        BLACK
    }

    private static class Node<T> {
        public State state = State.WHITE;
        public T name;

        public Node(T name) {
            this.name = name;
        }

        public void reset() {
            state = State.WHITE;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(name, node.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
