/**

 * This class represents a single NODE

 * @version final

 * @author Sapir Talker and Bar Amrani
 * @date 16/02/2022
 *
 * fields:
 *
 * key : the value of the GraphNode (int)
 * color : the status of the GraphNode in DFS/BFS [0,1,2]
 * d : the exploration time of the GraphNode in DFS/BFS (int)
 * f : the retreat time from the GraphNode in DFS/BFS (int)
 *  parent : the Parent GraphNode of this
 *  right_sibling : the right sibling GraphNode of this
 *  left_child : the left child GraphNode of this
 *  last_sibling : the right most sibling GraphNode of this (only for left childs to a parent)
 *  forTree : a shallow copy of this GraphNode to be used in Rooted Trees
 *  reference : the Item that contain this GraphNode in List
 *  outTo : Contain the the direct childs of this
 *  inTo : Contain the direct parents of this

 */

@SuppressWarnings("all")
public class GraphNode {
    private final int key;
    List<GraphNode> outTo = new List<>();
    List<GraphNode> inTo = new List<>();
    int color;
    GraphNode parent;
    GraphNode left_child;
    GraphNode right_sibling;
    GraphNode forTree;
    GraphNode last_sibling;
    int d;
    int f;
    List.item reference;

    /**
     * Constructor of the GraphNode
      * @param nodeKey the value being assigned
     */
    public GraphNode(int nodeKey) {
        this.key = nodeKey;
    }

    /**
     *
     * @return the key of this
     */
    public int getKey() {
        return this.key;
    }

    /**
     *
     * @return the length of outTo List
     */
    public int getOutDegree() {
        return outTo.length;
    }

    /**
     *
     * @return the length of inTo List
     */
    public int getInDegree() {
        return inTo.length;
    }


}