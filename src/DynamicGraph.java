public class DynamicGraph {
    private LinkedList Adj_List;
    private LinkedList Vertex_List;

    public DynamicGraph(){
        this.Adj_List = new LinkedList();
        this.Vertex_List = new LinkedList();
    }

    public GraphNode insertNode(int nodeKey){
        GraphNode newNode = new GraphNode(nodeKey);
        Vertex_List.insert(newNode);
        return newNode;
    }

    public void deleteNode(GraphNode node){
        Vertex_List.delete();
    }

}
