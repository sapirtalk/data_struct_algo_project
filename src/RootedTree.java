import java.io.DataOutputStream;
import java.io.IOException;


public class RootedTree {
    GraphNode root;


    public RootedTree() {
        this.root = null;
    }




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
            out.writeBytes(System.lineSeparator());
        }
    }

    void printPreorder(GraphNode node ,DataOutputStream out ) throws IOException {
        if (node == null)
            return;

        /* first print data of node */

        out.writeBytes(node.getKey() + ",");

        /* then recur on left subtree */
        printPreorder(node.left_child , out);

        /* now recur on right subtree */
        printPreorder(node.right_sibling , out);
    }

    public void preorderPrint(DataOutputStream out) throws IOException {
        GraphNode root = this.root;

        printPreorder(root , out);

    }

}
