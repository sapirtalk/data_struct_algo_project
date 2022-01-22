public class LinkedList_V {
    GraphNode head;

    public LinkedList_V(){
        this.head = null;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public GraphNode copyNode(GraphNode node){
        GraphNode newNode = new GraphNode(node.getKey());
        newNode.inTo = node.inTo;
        newNode.outTo = node.outTo;
        newNode.color = node.color;

        return newNode;
    }



    public void insert (GraphNode newNode){
        GraphNode x = copyNode(newNode);
        if(this.head == null){
            this.head = x;
        }
        else
            x.next = this.head;
        head.prev = x;
        this.head = x;

    }

    public void delete (GraphNode node){

        if(node == null){
            return;
        }

        if (node.prev != null){
            node.prev.next = node.next;
        }
        else
            this.head = node.next;
        if (node.next != null){
            node.next.prev = node.prev;
        }
    }

    public GraphNode getLast() {
        GraphNode v = this.head;
        while (v != null){
            v = v.next;
        }
        return v.prev;
    }
}
