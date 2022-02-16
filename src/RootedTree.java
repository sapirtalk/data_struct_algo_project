import java.io.DataOutputStream;
import java.io.IOException;
/**

 * This class represents a RootedTree

 * @version final

 * @author Sapir Talker and Bar Amrani
 * @date 16/2/2022
 *
 * fields:
 *
 * root : the root of this Tree
 * numNodes : to be used in PrintByLayer (int) as the number of nodes in this tree
 */

public class RootedTree {
    GraphNode root;
    int numNodes;

    /**
     * Constructor
     */
    public RootedTree() {
        this.root = null;
    }


    /**
     * Prints this RootedTree by layers
     * @param out given DataOutputStream
     * @throws IOException .
     */
    public void printByLayer (DataOutputStream out) throws IOException {
        GraphNode root = this.root;

        // Create a queue and enqueue root
        List<GraphNode> q = new List<>();
        q.insert(root);

        while (!q.isEmpty())
        {
            int size = q.length;

            while (size > 0) {

                GraphNode curr = (GraphNode) q.tail.data;
                q.delete(q.tail);

                while (curr != null) {

                    out.writeBytes(String.valueOf(curr.getKey()));


                    if (curr.left_child != null) {
                        q.insert(curr.left_child);
                    }
                    curr = curr.right_sibling;


                    if(size == 1 && curr == null)
                        break;
                    else
                        out.writeBytes(",");
                }
                size--;

            }
            if(!q.isEmpty())
                out.writeBytes(System.lineSeparator());
        }
    }

    /**
     * To be used in preorderPrint
     * @param node the next GraphNode to be printed
     * @param out Given DataOutputStream
     * @throws IOException .
     */
    void printPreorder(GraphNode node ,DataOutputStream out ) throws IOException {
        if (node == null)
            return;

        /* first print data of node */
        if(this.numNodes > 1) {
            out.writeBytes(node.getKey() + ",");
            this.numNodes--;
        }
        else
            out.writeBytes(node.getKey() + "");

        /* then recur on left subtree */
        printPreorder(node.left_child , out);

        /* now recur on right subtree */
        printPreorder(node.right_sibling , out);
    }

    /**
     * Prints this RootedTree preorder
     * @param out given DataOutputStream
     * @throws IOException .
     */
    public void preorderPrint(DataOutputStream out) throws IOException {
        GraphNode root = this.root;

        printPreorder(root , out);

    }

}
