/**

 * This class represents a DynamicGraph

 * @version final

 * @author Sapir Talker and Bar Amrani
 * @date 16/02/2022
 *
 * fields:
 *
 *  Adj_List : List of Edges of the DynamicGraph
 *  Vertex_List : List of Vertexes of the DynamicGraph
 *  time : To be used in BFS/DFS algorithms (int)


 */



@SuppressWarnings("all")
public class DynamicGraph {
    List<GraphEdge> Adj_List;
    List<GraphNode> Vertex_List;
    int time;

    /**
     * Constructor
     */
    public DynamicGraph(){
        this.Adj_List = new List<>();
        this.Vertex_List = new List<>();
    }

    /**
     * inserting a GraphNode to this DynamicGraph
     * @param nodeKey the value being assigned
     * @return the inserted GraphNode
     */
    public GraphNode insertNode(int nodeKey){
        GraphNode newNode = new GraphNode(nodeKey);
        newNode.reference = Vertex_List.insert(newNode);
        return newNode;
    }

    /**
     * Deleting a GraphNode from this DynamicGraph
     * @param node the GraphNode that is being deleted
     */
    public void deleteNode(GraphNode node){

        if(Vertex_List.isEmpty())
            return;

        if(node.outTo.isEmpty() && node.inTo.isEmpty())
            Vertex_List.delete(node.reference);
    }

    /**
     * Inserting a new GraphEdge to this DynamicGraph
     * @param from the source GraphNode
     * @param to the end GraphNode
     * @return the inserted GraphEdge
     */
    public GraphEdge insertEdge(GraphNode from, GraphNode to){
        GraphEdge newEdge = new GraphEdge(from,to);
        newEdge.reference = Adj_List.insert(newEdge);
        return newEdge;
    }

    /**
     * Deletes a GraphEdge from this DynamicGraph
     * @param edge the GraphEdge that is being deleted
     */
    public void deleteEdge(GraphEdge edge){

        if(Adj_List.isEmpty())
            return;

        edge.from.outTo.delete(edge.fromRef);
        edge.to.inTo.delete(edge.toRef);
        Adj_List.delete(edge.reference);
    }

    /**
     * DFS algorithm on this DynamicGraph
     * @param vertex_List a list of Vertexes
     * @return the final DFS Order List
     */
    public List<GraphNode> DFS(List<GraphNode> vertex_List) {
        List DFS_Order = new List<GraphNode>();
        List.item v = vertex_List.head;
        this.time = 0;
        while (v != null) {
            ((GraphNode) v.data).color = 0;
            ((GraphNode) v.data).parent = null;
            ((GraphNode) v.data).forTree = null;
            v = v.next;
        }

        v = vertex_List.head;

        while (v != null) {
            if (((GraphNode) v.data).color == 0) {
               RootedTree newTree = new RootedTree();
               ((GraphNode) v.data).forTree = new GraphNode(((GraphNode) v.data).getKey());
               newTree.root = ((GraphNode) v.data).forTree;
               DFS_Visit((GraphNode) v.data , DFS_Order , newTree);
            }
            v = v.next;
        }

        return DFS_Order;

    }

    /**
     * Part of the DFS algorithm, Used in DFS function
     * @param v the GraphNode that his children are being explored
     * @param list the current DFS_Order list
     * @param tree the Rooted tree that the GraphNodes are being assigned to
     */
    public void DFS_Visit(GraphNode v ,List<GraphNode> list , RootedTree tree){
        this.time = this.time + 1;
        v.d = this.time;
        v.color = 1;
        List.item u = v.outTo.head;
        while (u != null){
            if (((GraphNode) u.data).color == 0){
                ((GraphNode) u.data).parent = v;
                addChild(v , (GraphNode) u.data);
                DFS_Visit((GraphNode) u.data , list , tree);
            }
            u = u.next;
        }
        v.color = 2;
        this.time = this.time + 1;
        v.f = time;
        list.insert(v);
    }


    /**
     *
     * @return Tree of Strongly Connected Components
     */
    public RootedTree scc(){
        RootedTree mainTree = new RootedTree();
        mainTree.numNodes = Vertex_List.length + 1;
        List newOrder;
        List after;
        GraphNode root = new GraphNode(0);
        mainTree.root = root;
        newOrder = DFS(this.Vertex_List);
        transpose(newOrder);
        after = DFS(newOrder);
        transpose(newOrder);

        List.item temp = after.tail;

        while (temp != null){
            if(((GraphNode) temp.data).parent == null){
                addChild(mainTree.root ,(GraphNode) temp.data);
            }
            temp = temp.prev;
        }

        return mainTree;

    }

    /**
     * BFS algorithm on this DynamicGraph
     * @param source the root of the BFS Tree
     * @return A BFS tree
     */
    public RootedTree bfs(GraphNode source){

        List<GraphNode> Q = new List<>();
        RootedTree bfs_Tree = new RootedTree();
        bfs_Init(source , Q);
        source.forTree = new GraphNode(source.getKey());
        bfs_Tree.root = source.forTree;
        bfs_Tree.numNodes = 1;

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
                    bfs_Tree.numNodes++;
                }
                v = v.next;
                u.color = 2;
            }
        }

        return bfs_Tree;

    }

    /**
     * Part of the BFS algoithm , to be used in BFS function
     * @param source The root of the BFS Tree
     * @param Q an empty List
     */
    public void bfs_Init(GraphNode source , List<GraphNode> Q){
        List.item v = Vertex_List.head;
        while (v != null){
                ((GraphNode) v.data).color = 0;
                ((GraphNode) v.data).d = -1;
                ((GraphNode) v.data).parent = null;
                ((GraphNode) v.data).forTree = null;

            v = v.next;
        }
        source.color = 1;
        source.d = 0;
        source.parent = null;
        Q.insert(source);
    }

    /**
     * transpose the connections of the given GraphNodes
     * @param nodes list of GraphNodes
     */
    public void transpose(List<GraphNode> nodes){
        List.item v = nodes.head;
        while(v != null){
            List temp = ((GraphNode) v.data).outTo;
            ((GraphNode) v.data).outTo = ((GraphNode) v.data).inTo;
            ((GraphNode) v.data).inTo = temp;
            v = v.next;
        }

    }

    /**
     * Adding as a child the data Graphnode to the n Graphnode
     * @param n the parent GraphNode
     * @param data the to be child GraphNode
     */
    public GraphNode addChild(GraphNode n, GraphNode data)
    {

        if (data.forTree == null)
            data.forTree = new GraphNode(data.getKey());

        if(n.getKey() == 0){
            if (n.left_child == null)
                return n.left_child = data.forTree;

            else if (n.left_child.right_sibling == null){
                n.left_child.last_sibling = data.forTree;
                return n.left_child.right_sibling = data.forTree;
            }
            else
                n.left_child.last_sibling.right_sibling = data.forTree;
            return n.left_child.last_sibling = data.forTree;
        }
        else {
            if (n.forTree.left_child == null)
                return n.forTree.left_child = data.forTree;

            else if (n.forTree.left_child.right_sibling == null) {
                n.forTree.left_child.last_sibling = data.forTree;
                return n.forTree.left_child.right_sibling = data.forTree;
            } else
                n.forTree.left_child.last_sibling.right_sibling = data.forTree;
            return n.forTree.left_child.last_sibling = data.forTree;
        }
    }


}
