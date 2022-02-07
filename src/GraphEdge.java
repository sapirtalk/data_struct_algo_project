public class GraphEdge {
    GraphNode from;
    GraphNode to;
    List.item fromRef;
    List.item toRef;
    List.item reference;

    public GraphEdge(GraphNode from, GraphNode to) {
        fromRef = from.outTo.insert(to);
        toRef = to.inTo.insert(from);
        this.from = from;
        this.to = to;
    }

}
