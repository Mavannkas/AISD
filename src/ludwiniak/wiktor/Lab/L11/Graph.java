package ludwiniak.wiktor.Lab.L11;

import java.util.*;

public class Graph<T> {
    private final Map<T, Integer> nodes = new HashMap<>();
    private final List<List<Integer>> distances = new ArrayList<>();
    private final Map<T, Map<T, Integer>> cache = new HashMap<>();

    public Graph(List<Edge<T>> edges) {
        getNodes(edges);
        fillDistances();
        addDistances(edges);
    }


    private void addDistances(List<Edge<T>> edges) {
        for (Edge<T> edge : edges) {
            distances.get(nodes.get(edge.getNode1())).set(nodes.get(edge.getNode2()), edge.getDistance());
            distances.get(nodes.get(edge.getNode2())).set(nodes.get(edge.getNode1()), edge.getDistance());
        }
    }

    private void fillDistances() {
        for (int i = 0; i < nodes.size(); i++) {
            distances.add(new ArrayList<>(Collections.nCopies(nodes.size(), Integer.MAX_VALUE)));
        }
    }

    private void getNodes(List<Edge<T>> edges) {
        for (Edge<T> edge : edges) {
            if (!nodes.containsKey(edge.getNode1())) {
                nodes.put(edge.getNode1(), nodes.size());
            }

            if (!nodes.containsKey(edge.getNode2())) {
                nodes.put(edge.getNode2(), nodes.size());
            }
        }
    }

    public Map<T, Integer> calculateShortestPaths(T startNode) throws NoSuchElementException {
        return calculateShortestPaths(startNode, null);
    }

    public Map<T, Integer> calculateShortestPaths(T startNode, T endNode) throws NoSuchElementException {
        if (!nodes.containsKey(startNode) || (endNode != null && !nodes.containsKey(endNode))) {
            throw new NoSuchElementException("There is no such node in graph");
        }

        if (cache.containsKey(startNode)) {
            return cache.get(startNode);
        }

        int startIndex = nodes.get(startNode);
        Map<T, Integer> output = new HashMap<>();
        Map<T, Boolean> visited = new HashMap<>();

        for (T key : nodes.keySet()) {
            if (key.equals(startNode)) {
                continue;
            }

            visited.put(key, false);
            if (distances.get(startIndex).get(nodes.get(key)) != Integer.MAX_VALUE) {
                output.put(key, distances.get(startIndex).get(nodes.get(key)));
            } else {
                output.put(key, Integer.MAX_VALUE);
            }
        }

        while (visited.containsValue(false)) {
            T minNode = getMinNode(output, visited);
            visited.put(minNode, true);

            if (minNode.equals(endNode)) {
                break;
            }

            for (T key : nodes.keySet()) {
                if (!output.containsKey(key) || visited.get(key)) {
                    continue;
                }

                int currentDistance = distances.get(nodes.get(minNode)).get(nodes.get(key));
                if (currentDistance == Integer.MAX_VALUE) {
                    continue;
                }

                output.put(key, Math.min(output.get(minNode) + currentDistance, output.get(key)));
            }

        }

        cache.put(startNode, output);
        return output;
    }

    private T getMinNode(Map<T, Integer> output, Map<T, Boolean> visited) {
        T minNode = null;
        int minDistance = Integer.MAX_VALUE;

        for (T key : visited.keySet()) {
            if (!visited.get(key) && output.get(key) < minDistance) {
                minDistance = output.get(key);
                minNode = key;
            }
        }

        return minNode;
    }

    public Integer calculateShortestPath(T startNode, T endNode) throws NoSuchElementException {
        return calculateShortestPaths(startNode, endNode).get(endNode);
    }

}
