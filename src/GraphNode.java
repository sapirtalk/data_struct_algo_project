
public class GraphNode {
    private final int key;
    List<GraphNode> outTo = new List<>();
    List<GraphNode> inTo = new List<>();
    String color = "";
    GraphNode parent;
    GraphNode left_child;
    GraphNode right_sibling;
    int d;
    int f;
    List.item reference;

    public GraphNode(int nodeKey) {
        this.key = nodeKey;
        parent = null;
        left_child = null;
        right_sibling = null;
    }

    public int getKey() {
        return this.key;
    }

    public int getOutDegree() {
        return outTo.length;
    }

    public int getInDegree() {
        return inTo.length;
    }


}