
public class GraphNode {
    private int key;
    LinkedList outTo = new LinkedList();
    LinkedList inTo = new LinkedList();

    public GraphNode(int nodeKey){
        this.key = nodeKey;
    }

    public int getKey() {
        return this.key;
    }

    public int getOutDegree(){
        LinkedList.Node x = this.outTo.head;
        int count = 0;

        while (x != null){
            count++;
            x = x.next;
        }
        return count;
    }

    public int getInDegree(){
        LinkedList.Node x = this.inTo.head;
        int count = 0;

        while (x != null){
            count++;
            x = x.next;
        }
        return count;
    }
}
