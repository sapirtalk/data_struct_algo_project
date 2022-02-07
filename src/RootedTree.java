import java.io.DataOutputStream;
import java.io.IOException;


public class RootedTree {
    GraphNode root;


    public RootedTree() {
        this.root = null;
    }




    // Adds a sibling to a list with starting with n
    public GraphNode addSibling(GraphNode n, GraphNode data)
    {
        if (n == null)
            return null;
        while (n.right_sibling != null)
            n = n.right_sibling;
        return n.right_sibling = data;
    }

    // Add child Node to a Node
    public GraphNode addChild(GraphNode n, GraphNode data)
    {
        if (n == null)
            return null;

        // Check if child list is not empty.
        if (n.left_child != null)
            return addSibling(n.left_child, data);
        else
            return n.left_child = data;
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

        if(node.right_sibling == null && node.left_child == null)
            out.writeBytes(node.getKey() + System.lineSeparator());
        else
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
