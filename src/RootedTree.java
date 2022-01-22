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
        return n.right_sibling = new GraphNode(data.getKey());
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
            return n.left_child = new GraphNode(data.getKey());
    }


    public void printByLayer (DataOutputStream out) throws IOException {
        GraphNode root = this.root;
        if (root == null)
            return;
        out.writeUTF(root.getKey() + "/n");
        if (root.left_child == null)
            return;

        // Create a queue and enqueue root
        LinkedList_V q = new LinkedList_V();
        GraphNode curr = root.left_child;
        q.insert(curr);

        while (!q.isEmpty())
        {

            // Take out an item from the queue
            curr = q.head;
            q.delete(q.head);

            // Print next level of taken out item and enqueue
            // next level's children
            while (curr != null)
            {
                if(curr.right_sibling != null)
                    out.writeUTF(curr.getKey() + ",");
                else
                    out.writeUTF(curr.getKey() + "/n");
                if (curr.left_child != null)
                {
                    q.insert(curr.left_child);
                }
                curr = curr.right_sibling;
            }
        }
    }

    void printPreorder(GraphNode node ,DataOutputStream out ) throws IOException {
        if (node == null)
            return;

        /* first print data of node */
        if(node.right_sibling == null && node.left_child == null)
            out.write(node.getKey());
        else
            out.writeUTF(node.getKey() + ",");

        /* then recur on left subtree */
        printPreorder(node.left_child , out);

        /* now recur on right subtree */
        printPreorder(node.right_sibling , out);
    }

    public void preorderPrint(DataOutputStream out) throws IOException {
        GraphNode root = this.root.left_child;

        printPreorder(root , out);

    }

}
