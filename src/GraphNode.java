
public class GraphNode {
    private int key;
    LinkedList outTo = new LinkedList();
    LinkedList inTo = new LinkedList();
    private GraphNode left_child;
    private GraphNode right_sibling;
    private GraphNode parent;

    public GraphNode(int nodeKey) {
        this.key = nodeKey;
    }

    public int getKey() {
        return this.key;
    }

    public int getOutDegree() {
        LinkedList.Node x = this.outTo.head;
        int count = 0;

        while (x != null) {
            count++;
            x = x.next;
        }
        return count;
    }

    public int getInDegree() {
        LinkedList.Node x = this.inTo.head;
        int count = 0;

        while (x != null) {
            count++;
            x = x.next;
        }
        return count;
    }

    public GraphNode getLeftChild() {
        return left_child;

    }

    public GraphNode getRightSibling() {
        return right_sibling ;
    }
}

    public GraphNode getParent() {
        return this.parent;

    }