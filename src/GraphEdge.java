public class GraphEdge {
    GraphNode from;
    GraphNode to;

    public GraphEdge(GraphNode from, GraphNode to) {
        from.outTo.insert(to);
        to.inTo.insert(from);
        this.from = from;
        this.to = to;
    }

}
