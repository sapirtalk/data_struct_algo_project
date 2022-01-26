

public class DynamicGraph {
    List<GraphEdge> Adj_List;
    List<GraphNode> Vertex_List;
    int time;

    public DynamicGraph(){
        this.Adj_List = new List<>();
        this.Vertex_List = new List<>();
    }

    public GraphNode insertNode(int nodeKey){
        GraphNode newNode = new GraphNode(nodeKey);
        List.item temp = Vertex_List.insert(newNode);
        newNode.reference = temp;
        return newNode;
    }

    public void deleteNode(GraphNode node){

        if(Vertex_List.isEmpty())
            return;

        if(node.outTo.isEmpty() && node.inTo.isEmpty())
            return;
        Vertex_List.delete(node.reference);
    }

    public GraphEdge insertEdge(GraphNode from, GraphNode to){
        GraphEdge newEdge = new GraphEdge(from,to);
        List.item temp = Adj_List.insert(newEdge);
        newEdge.reference = temp;
        return newEdge;
    }

    public void deleteEdge(GraphEdge edge){

        if(Adj_List.isEmpty())
            return;

        Adj_List.delete(edge.reference);
    }

    public RootedTree DFS(List<GraphNode> vertex_List) {
        List.item v = vertex_List.head;
        RootedTree mainTree = new RootedTree();
        mainTree.root = new GraphNode(0);
        this.time = 0;
        while (v != null) {
            ((GraphNode) v.data).color = "white";
            ((GraphNode) v.data).d = 0;
            ((GraphNode) v.data).f = 0;
            ((GraphNode) v.data).parent = null;
            ((GraphNode) v.data).right_sibling = null;
            v = v.next;
        }

        v = vertex_List.head;
        RootedTree newTree = new RootedTree();

        while (v != null) {
            if (((GraphNode) v.data).color.equals("white")) {
                newTree.root = ((GraphNode) v.data);
                DFS_Visit(newTree.root, newTree);
            }
            mainTree.addChild(mainTree.root,newTree.root);
            v = v.next;
        }
        return mainTree;
    }

    public void DFS_Visit(GraphNode v , RootedTree Tree){
        this.time = this.time + 1;
        v.d = time;
        v.color = "grey";
        List.item u = v.outTo.head;
        while (u != null){
            if (((GraphNode) u.data).color.equals("white")){
                ((GraphNode) u.data).parent = v;
                Tree.addChild(v , (GraphNode) u.data);
                DFS_Visit((GraphNode) u.data, Tree);
            }
            v.color = "black";
            this.time = this.time + 1;
            v.f = this.time;
            u = u.next;
        }

    }



    public RootedTree scc(){

        return DFS(this.Vertex_List);

    }

    public RootedTree bfs(GraphNode source){

        List<GraphNode> Q = new List<>();
        RootedTree bfs_Tree = new RootedTree();
        bfs_Tree.root = source;
        bfs_Init(Vertex_List , source , Q);
        while (!Q.isEmpty()){
            GraphNode u = (GraphNode) Q.tail.data;
            Q.delete(Q.tail);
            List.item v = u.outTo.head;
            while (v != null){
                if(((GraphNode) v.data).color.equals("white")){
                    ((GraphNode) v.data).color = "grey";
                    ((GraphNode) v.data).d = u.d + 1;
                    ((GraphNode) v.data).parent = u;
                    Q.insert((GraphNode) v.data);
                    bfs_Tree.addChild(u, (GraphNode) v.data);
                }
                v = v.next;
                /*u.color = "black";*/
            }
        }

        return bfs_Tree;

    }

    public void bfs_Init(List<GraphNode> vertex_List , GraphNode source , List<GraphNode> Q){
        List.item v = vertex_List.head;
        while (v != null){
            if(v.data != source){
                ((GraphNode) v.data).color = "white";
                ((GraphNode) v.data).d = Integer.MAX_VALUE;
                ((GraphNode) v.data).parent = null;
            }
            v = v.next;
        }
        source.color = "grey";
        source.d = 0;
        source.parent = null;
        Q.insert(source);
    }



}
