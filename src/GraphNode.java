
public class GraphNode {
    private final int key;
    LinkedList_V outTo = new LinkedList_V();
    LinkedList_V inTo = new LinkedList_V();
    GraphNode next = null;
    GraphNode prev = null;
    private boolean visited = false;

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

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean getVisited(){
        return this.visited;
    }
}