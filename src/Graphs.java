import java.util.*;

public class Graphs {

    public static class GraphNode {
        private String name;
        private List<Edge> neighbors;

        public GraphNode(String name) {
            this.name = name;
            this.neighbors = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public List<Edge> getNeighbors() {
            return neighbors;
        }
    }

    public static class Edge {
        private GraphNode to;
        private int weight;

        public Edge(GraphNode to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        public Edge(GraphNode to) {
            this.to = to;
            this.weight = 0; // Default weight
        }

        public GraphNode getTo() {
            return to;
        }

        public int getWeight() {
            return weight;
        }
    }

    public static class Graph {
        public List<GraphNode> nodes;
        private List<List<Edge>> adjacencyList;

        public Graph() {
            this.nodes = new ArrayList<>();
            this.adjacencyList = new ArrayList<>();
        }

        public void addNode(GraphNode newNode) {
            nodes.add(newNode);
            adjacencyList.add(new ArrayList<>());
        }

        public void addEdge(GraphNode fromNode, GraphNode toNode) {
            if (nodes.contains(fromNode) && nodes.contains(toNode)) {
                Edge newEdge = new Edge(toNode);
                fromNode.getNeighbors().add(newEdge);
                adjacencyList.get(nodes.indexOf(fromNode)).add(newEdge);
            }
        }

        public void addEdge(GraphNode fromNode, GraphNode toNode, int weight) {
            if (nodes.contains(fromNode) && nodes.contains(toNode)) {
                Edge newEdge = new Edge(toNode, weight);
                fromNode.getNeighbors().add(newEdge);
                adjacencyList.get(nodes.indexOf(fromNode)).add(newEdge);
            }
        }

        public void addNoDirectedEdge(GraphNode node1, GraphNode node2, int weight) {
            addEdge(node1, node2, weight);
            addEdge(node2, node1, weight);
        }

        public void addNoDirectedEdge(GraphNode node1, GraphNode node2) {
            addEdge(node1, node2);
            addEdge(node2, node1);
        }

        public void removeNode(GraphNode nodeToRemove) {
            if (nodes.contains(nodeToRemove)) {
                int index = nodes.indexOf(nodeToRemove);
                nodes.remove(index);
                adjacencyList.remove(index);

                for (GraphNode node : nodes) {
                    node.getNeighbors().removeIf(edge -> edge.getTo() == nodeToRemove);
                }

                for (List<Edge> adjList : adjacencyList) {
                    adjList.removeIf(edge -> edge.getTo() == nodeToRemove);
                }
            }
        }

        public void removeEdge(GraphNode fromNode, GraphNode toNode) {
            if (nodes.contains(fromNode) && nodes.contains(toNode)) {
                List<Edge> edges = adjacencyList.get(nodes.indexOf(fromNode));
                edges.removeIf(edge -> edge.getTo() == toNode);

                fromNode.getNeighbors().removeIf(edge -> edge.getTo() == toNode);
            }
        }

        public void removeNoDirectedEdge(GraphNode node1, GraphNode node2) {
            removeEdge(node1, node2);
            removeEdge(node2, node1);
        }

        public void clear() {
            nodes.clear();
            adjacencyList.clear();
        }

        public String showAdjacencyList() {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < nodes.size(); i++) {
                sb.append(nodes.get(i).getName()).append(": ");
                for (Edge edge : adjacencyList.get(i)) {
                    sb.append(edge.getTo().getName()).append(", ");
                }
                sb.append("\n");
            }

            return sb.toString();
        }

        public String showAdjacencyListWithWeights() {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < nodes.size(); i++) {
                sb.append(nodes.get(i).getName()).append(": ");
                for (Edge edge : adjacencyList.get(i)) {
                    sb.append(edge.getTo().getName()).append(" (").append(edge.getWeight()).append("), ");
                }
                sb.append("\n");
            }

            return sb.toString();
        }

        public String dfs(GraphNode startNode) {
            if (startNode == null || ! nodes.contains(startNode)) return "";

            Set<GraphNode> visited = new HashSet<>();
            StringBuilder result = new StringBuilder();
            dfsRecursive(startNode, visited, result);

            return result.toString();
        }

        private void dfsRecursive(GraphNode currentNode, Set<GraphNode> visited, StringBuilder result) {
            visited.add(currentNode);

            if (result.length() > 0) {
                result.append(" -> ");
            }
            result.append(currentNode.getName());

            for (Edge edge : currentNode.getNeighbors()) {
                if (!visited.contains(edge.getTo())) {
                    dfsRecursive(edge.getTo(), visited, result);
                }
            }
        }

        public String dfsIterative(GraphNode startNode) {
            if (startNode == null || !nodes.contains(startNode)) return "";

            Set<GraphNode> visited = new HashSet<>();
            Stack<GraphNode> stack = new Stack<>();
            StringBuilder result = new StringBuilder();

            stack.push(startNode);

            while (!stack.isEmpty()) {
                GraphNode currentNode = stack.pop();

                if (!visited.contains(currentNode)) {
                    if (result.length() > 0) {
                        result.append(" -> ");
                    }
                    result.append(currentNode.getName());

                    visited.add(currentNode);

                    List<Edge> neighbors = new ArrayList<>(currentNode.getNeighbors());
                    Collections.reverse(neighbors);
                    for (Edge edge : neighbors) {
                        if (!visited.contains(edge.getTo())) {
                            stack.push(edge.getTo());
                        }
                    }
                }
            }

            return result.toString();
        }

        public String bfs(GraphNode startNode) {
            if (startNode == null || !nodes.contains(startNode)) return "";

            Set<GraphNode> visited = new HashSet<>();
            Queue<GraphNode> queue = new LinkedList<>();
            StringBuilder result = new StringBuilder();

            queue.add(startNode);

            while (!queue.isEmpty()) {
                GraphNode currentNode = queue.poll();

                if (!visited.contains(currentNode)) {
                    if (result.length() > 0) {
                        result.append(" -> ");
                    }
                    result.append(currentNode.getName());

                    visited.add(currentNode);

                    for (Edge edge : currentNode.getNeighbors()) {
                        if (!visited.contains(edge.getTo())) {
                            queue.add(edge.getTo());
                        }
                    }
                }
            }

            return result.toString();
        }
    }
}