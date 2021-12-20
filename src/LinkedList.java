public class LinkedList {
    Node head;

    class Node {
        GraphNode data;
        Node next;
        Node prev;

        Node(GraphNode d) {
            data = d;
            next = null;
            prev = null;
        }
    }

    public void insert (GraphNode newNode){
        Node x = new Node(newNode);
        if(this.head == null){
            this.head = x;
        }
        else
            x.next = this.head;
            head.prev = x;
            this.head = x;

    }

    public void delete (Node node){
        if (node.prev != null){
            node.prev.next = node.next;
        }
        else
            this.head = node.next;
        if (node.next != null){
            node.next.prev = node.prev;
        }
    }

}