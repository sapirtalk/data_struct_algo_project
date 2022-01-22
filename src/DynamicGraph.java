import jdk.nashorn.api.tree.Tree;

public class DynamicGraph {
    private final LinkedList_E Adj_List;
    private final LinkedList_V Vertex_List;
    int time;

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

    public RootedTree DFS(LinkedList_V vertex_List) {
        GraphNode v = vertex_List.head;
        RootedTree mainTree = new RootedTree();
        mainTree.root = new GraphNode(0);
        this.time = 0;
        while (v != null) {
            v.color = "white";
            v.d = 0;
            v.f = 0;
            v.parent = null;
            v = v.next;
        }

        v = vertex_List.head;
        RootedTree newTree = new RootedTree();

        while (v != null) {
            if (v.color.equals("white")) {
                newTree.root = v;
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
        GraphNode u = v.outTo.head;
        while (u != null){
            if (u.color.equals("white")){
                u.parent = v;
                Tree.addChild(v , u);
                DFS_Visit(u , Tree);
                u = u.next;
            }
            v.color = "black";
            this.time = this.time + 1;
            v.f = this.time;
        }

    }



    public RootedTree scc(){

        return DFS(this.Vertex_List);

    }

    public RootedTree bfs(GraphNode source){

        LinkedList_V Q = new LinkedList_V();
        RootedTree bfs_Tree = new RootedTree();
        bfs_Tree.root = source;
        bfs_Init(Vertex_List , source , Q);
        while (!Q.isEmpty()){
            GraphNode u = Q.head;
            Q.delete(Q.head);
            GraphNode v = u.outTo.head;
            while (v != null){
                if(v.color.equals("white")){
                    v.color = "grey";
                    v.d = u.d + 1;
                    v.parent = u;
                    Q.insert(v);
                    bfs_Tree.addChild(v , u);
                }
                u.color = "black";
                v = v.next;
            }
        }

        return bfs_Tree;
        
    }

    public void bfs_Init(LinkedList_V vertex_List , GraphNode source , LinkedList_V Q){
        GraphNode v = vertex_List.head;
        while (v != null){
            if(v != source){
                v.color = "white";
                v.d = Integer.MAX_VALUE;
                v.parent = null;
            }
            v = v.next;
        }
        source.color = "grey";
        source.d = 0;
        source.parent = null;
        Q.insert(source);
    }



}
