/**

 * This class represents a single Edge

 * @version final

 * @author Sapir Talker and Bar Amrani
 * @date 16/02/2022
 *
 * fields:
 *
 *  from : the source GraphNode of this GraphEdge
 *  to : the end GraphNode of this GraphEdge
 *  fromRef : the Item that contain the source GraphNode in List
 *  toRef : the Item that contain the end GraphNode in List
 *  reference : the Item that contain this GraphEdge in List

 */

@SuppressWarnings("all")
public class GraphEdge {
    GraphNode from;
    GraphNode to;
    List.item fromRef;
    List.item toRef;
    List.item reference;

    /**
     * Constructor of GraphEdge
     *
     * @param from the source GraphNode
     * @param to the end GraphNode
     */
    public GraphEdge(GraphNode from, GraphNode to) {
        fromRef = from.outTo.insert(to);
        toRef = to.inTo.insert(from);
        this.from = from;
        this.to = to;
    }

}
