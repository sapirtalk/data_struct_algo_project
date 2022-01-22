
public class GraphNode {
    private final int key;
    LinkedList_V outTo = new LinkedList_V();
    LinkedList_V inTo = new LinkedList_V();
    GraphNode next = null;
    GraphNode prev = null;
    String color;
    GraphNode parent;
    GraphNode left_child;
    GraphNode right_sibling;
    int d;
    int f;

    public GraphNode(int nodeKey) {
        this.key = nodeKey;
    }

    public int getKey() {
        return this.key;
    }

    public int getOutDegree() {
        GraphNode x = this.outTo.head;
        int count = 0;

        while (x != null) {
            count++;
            x = x.next;
        }
        return count;
    }

    public int getInDegree() {
        GraphNode x = this.inTo.head;
        int count = 0;

        while (x != null) {
            count++;
            x = x.next;
        }
        return count;
    }



    public boolean isRoot(){
        return inTo.head == null;
    }

    public boolean isLeaf(){
        return outTo.head == null;
    }

    public GraphNode getRoot() {
        if(parent == null){
            return this;
        }
        return parent.getRoot();
    }

    public GraphNode CopyNode(){
        GraphNode newNode = new GraphNode(getKey());
        newNode.inTo = inTo;
        newNode.outTo = outTo;
        newNode.color = color;
        newNode.parent = parent;
        newNode.left_child = left_child;
        newNode.right_sibling = right_sibling;
        newNode.prev = prev;
        newNode.next = next;

        return newNode;
    }

}