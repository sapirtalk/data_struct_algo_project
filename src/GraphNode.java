
public class GraphNode {
    private final int key;
    List<GraphNode> outTo = new List<>();
    List<GraphNode> inTo = new List<>();
    int color;
    GraphNode parent;
    GraphNode left_child;
    GraphNode right_sibling;
    GraphNode forTree;
    GraphNode last_sibling;
    int d;
    int f;
    List.item reference;

    public GraphNode(int nodeKey) {
        this.key = nodeKey;
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