

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
            ((GraphNode) v.data).color = 0;
            ((GraphNode) v.data).d = 0;
            ((GraphNode) v.data).f = 0;
            ((GraphNode) v.data).parent = null;
            ((GraphNode) v.data).right_sibling = null;
            ((GraphNode) v.data).left_child = null;
            v = v.next;
        }

        v = vertex_List.head;

        while (v != null) {
            if (((GraphNode) v.data).color == 0) {
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
        v.color = 1;
        List.item u = v.outTo.head;
        while (u != null){
            if (((GraphNode) u.data).color == 0){
                ((GraphNode) u.data).parent = v;
                addChild(v , (GraphNode) u.data);

                DFS_Visit((GraphNode) u.data , list , tree);
            }
            v.color = 2;
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
                addChild(mainTree.root ,(GraphNode) temp.data);
            }
            temp = temp.next;
        }

        return mainTree;

    }

    public RootedTree bfs(GraphNode source){

        List<GraphNode> Q = new List<>();
        RootedTree bfs_Tree = new RootedTree();
        bfs_Init(source , Q);
        source.forTree = new GraphNode(source.getKey());
        bfs_Tree.root = source.forTree;

        while (!Q.isEmpty()){
            GraphNode u = (GraphNode) Q.tail.data;
            Q.delete(Q.tail);
            List.item v = u.outTo.head;
            while (v != null){
                if(((GraphNode) v.data).color == 0){
                    ((GraphNode) v.data).color = 1;
                    ((GraphNode) v.data).d = u.d + 1;
                    ((GraphNode) v.data).parent = u;
                    Q.insert((GraphNode) v.data);
                    addChild(u, (GraphNode) v.data);
                }
                v = v.next;
                u.color = 2;
            }
        }

        return bfs_Tree;

    }

    public void bfs_Init(GraphNode source , List<GraphNode> Q){
        List.item v = Vertex_List.head;
        while (v != null){
                ((GraphNode) v.data).color = 0;
                ((GraphNode) v.data).d = -1;
                ((GraphNode) v.data).parent = null;
                ((GraphNode) v.data).left_child = null;
                ((GraphNode) v.data).right_sibling = null;
                ((GraphNode) v.data).last_sibling = null;
                ((GraphNode) v.data).forTree = null;

            v = v.next;
        }
        source.color = 1;
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


    public GraphNode addChild(GraphNode n, GraphNode data)
    {

        if (data.forTree == null)
            data.forTree = new GraphNode(data.getKey());

        if (n.forTree.left_child == null)
            return n.forTree.left_child = data.forTree;

        else if (n.forTree.left_child.right_sibling == null){
           n.forTree.left_child.last_sibling = data.forTree;
           return n.forTree.left_child.right_sibling = data.forTree;
        }
        else
            n.forTree.left_child.last_sibling.right_sibling = data.forTree;
        return n.forTree.left_child.last_sibling = data.forTree;


//        if (data.forTree == null) {
//            data.forTree = new GraphNode(data.getKey());
//        }
//        new GraphEdge(n.forTree, data.forTree);



    }


}
