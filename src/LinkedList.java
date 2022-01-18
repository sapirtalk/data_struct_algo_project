public class LinkedList {
    Node head;

    public boolean isEmpty() {
        return this.head == null;
    }


    class Node {
        RootedTree.TreeNode T_data;
        Node next;
        Node prev;

        Node(RootedTree.TreeNode d) {
            T_data = d;
            next = null;
            prev = null;
        }


    }


    public void insertT (RootedTree.TreeNode newNode){
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