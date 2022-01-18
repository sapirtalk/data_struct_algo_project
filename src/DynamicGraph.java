public class DynamicGraph {
    private final LinkedList_E Adj_List;
    private final LinkedList_V Vertex_List;

    public DynamicGraph(){
        this.Adj_List = new LinkedList_E();
        this.Vertex_List = new LinkedList_V();
    }

    public GraphNode insertNode(int nodeKey){
        GraphNode newNode = new GraphNode(nodeKey);
        Vertex_List.insert(newNode);
        return newNode;
    }

    public void deleteNode(GraphNode node){

        if(Vertex_List.isEmpty())
            return;
        if(node.outTo.isEmpty() || node.inTo.isEmpty())
            return;

        Vertex_List.delete(node);
    }

    public GraphEdge insertEdge(GraphNode from, GraphNode to){
        GraphEdge newEdge = new GraphEdge(from,to);
        Adj_List.insert(newEdge);
        return newEdge;
    }

    public void deleteEdge(GraphEdge edge){

        if(Adj_List.isEmpty())
            return;

        Adj_List.delete(edge);
    }

    RootedTree DFS(GraphNode v)
    {
        // Mark the current node as visited and print it
        v.setVisited(true);
        RootedTree.TreeNode node = new RootedTree.TreeNode(v);

        // Recur for all the vertices adjacent to this vertex
        GraphNode n = v.outTo.head;
        while (n != null)
        {
            if (!n.getVisited()){
                DFS();
            }
        }

    public RootedTree scc(){

    }



}
