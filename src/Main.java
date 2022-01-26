import java.io.DataOutputStream;
import java.io.IOException;

import static java.lang.System.out;

public class Main {
    public static void main(String[] args) throws IOException{

        GraphNode[] nodes = new GraphNode[100];
        GraphEdge[] edges = new GraphEdge[50];
        GraphNode temp;
        GraphEdge tempe;

        for(int i = 0 ; i<100; i++){
            temp = new GraphNode(i);
            nodes[i] = temp;
        }

        DynamicGraph G = new DynamicGraph();
        for(int i = 0 ; i<100; i++) {
            G.insertNode(nodes[i].getKey());;
        }



                G.insertEdge(nodes[0], nodes[2]);
                G.insertEdge(nodes[2], nodes[5]);
                G.insertEdge(nodes[0], nodes[1]);
                G.insertEdge(nodes[2], nodes[10]);


        DataOutputStream outStream = new DataOutputStream(out);
        RootedTree tree = G.scc();
        System.out.println();
        System.out.println(tree.root.left_child.right_sibling.right_sibling.getKey());




    }
}
