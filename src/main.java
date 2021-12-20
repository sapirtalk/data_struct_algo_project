public class main {

    public static void main(String[] args) {

            GraphNode node = new GraphNode(1);
            GraphNode node1 = new GraphNode(2);
            GraphNode node2 = new GraphNode(3);
            GraphNode node3 = new GraphNode(3);

            node.outTo.insert(node1);
            node.outTo.insert(node2);
            node.outTo.insert(node3);
            node.inTo.insert(node1);
            node.inTo.insert(node2);

            int j = node.getOutDegree();
            int i = node.getInDegree();

            System.out.println(j +" "+ i);

        }
    }

