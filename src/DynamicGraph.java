

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
        newNode.reference = Vertex_List.insert(newNode);
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
        newEdge.reference = Adj_List.insert(newEdge);
        return newEdge;
    }

    public void deleteEdge(GraphEdge edge){

        if(Adj_List.isEmpty())
            return;

        edge.from.outTo.delete(edge.fromRef);
        edge.to.inTo.delete(edge.toRef);
        Adj_List.delete(edge.reference);
    }

    public List<GraphNode> DFS(List<GraphNode> vertex_List) {
        List DFS_Order = new List<GraphNode>();
        List.item v = vertex_List.head;
        this.time = 0;
        while (v != null) {
            ((GraphNode) v.data).color = "white";
            ((GraphNode) v.data).d = 0;
            ((GraphNode) v.data).f = 0;
            ((GraphNode) v.data).parent = null;
            ((GraphNode) v.data).right_sibling = null;
            ((GraphNode) v.data).left_child = null;
            v = v.next;
        }

        v = vertex_List.head;

        while (v != null) {
            if (((GraphNode) v.data).color.equals("white")) {
               RootedTree newTree = new RootedTree();
               newTree.root = (GraphNode) v.data;
               DFS_Order = DFS_Visit((GraphNode) v.data , DFS_Order , newTree);
            }
            v = v.next;
        }

        return DFS_Order;

    }

    public List<GraphNode> DFS_Visit(GraphNode v ,List<GraphNode> list , RootedTree tree){
        this.time = this.time + 1;
        v.d = time;
        v.color = "grey";
        List.item u = v.outTo.head;
        while (u != null){
            if (((GraphNode) u.data).color.equals("white")){
                ((GraphNode) u.data).parent = v;
                tree.addChild(v , (GraphNode) u.data);

                DFS_Visit((GraphNode) u.data , list , tree);
            }
            v.color = "black";
            this.time = this.time + 1;
            v.f = this.time;
            list.insert(v);
            u = u.next;
        }

        return list;

    }



    public RootedTree scc(){
        RootedTree mainTree = new RootedTree();
        List newOrder;
        List oldOrder;
        mainTree.root = new GraphNode(0);
        newOrder = DFS(this.Vertex_List);
        transpose(newOrder);
        DFS(newOrder);
        transpose(newOrder);

        List.item temp = Vertex_List.head;

        while (temp != null){
            if(((GraphNode) temp.data).parent == null){
                mainTree.addChild(mainTree.root ,(GraphNode) temp.data);
            }
            temp = temp.next;
        }

        return mainTree;

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
//                    if(u.left_child == null){
//                        u.left_child = (GraphNode) v.data;
//                    }
//                    else {
//                        u.temp = u.left_child.right_sibling;
//                        while (u.temp != null){
//                            u.temp = u.temp.right_sibling;
//                        }
//                        u.temp = (GraphNode) v.data;
//                    }
                  addChild(u, (GraphNode) v.data);
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
                ((GraphNode) v.data).d = -1;
                ((GraphNode) v.data).parent = null;
            }
            v = v.next;
        }
        source.color = "grey";
        source.d = 0;
        source.parent = null;
        Q.insert(source);
    }

    public void transpose(List<GraphNode> nodes){
        List.item v = nodes.head;
        List<GraphNode> temp;

        while(v != null){
                temp = ((GraphNode) v.data).outTo;
                ((GraphNode) v.data).outTo = ((GraphNode) v.data).inTo;
                ((GraphNode) v.data).inTo = temp;

            v = v.next;

        }

    }

    public GraphNode addSibling(GraphNode n, GraphNode data)
    {
        if (n == null)
            return null;
        while (n.right_sibling != null)
            n = n.right_sibling;
        return n.right_sibling = data;
    }

    // Add child Node to a Node
    public GraphNode addChild(GraphNode n, GraphNode data)
    {
        if (n == null)
            return null;

        // Check if child list is not empty.
        if (n.left_child != null)
            return addSibling(n.left_child, data);
        else
            return n.left_child = data;
    }


}
