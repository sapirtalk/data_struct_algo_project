public class LinkedList_E {
    GraphEdge head;

    public LinkedList_E(){
        this.head = null;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public GraphEdge copyEdge(GraphEdge edge){
        GraphEdge newEdge = new GraphEdge(edge.from,edge.to);
        return newEdge;
    }



    public void insert (GraphEdge newEdge){
        GraphEdge x = copyEdge(newEdge);
        if(this.head == null){
            this.head = x;
        }
        else
            x.next = this.head;
        head.prev = x;
        this.head = x;

    }

    public void delete (GraphEdge edge){

        if (edge.prev != null){
            edge.prev.next = edge.next;
        }
        else
            this.head = edge.next;
        if (edge.next != null){
            edge.next.prev = edge.prev;
        }
    }
}
