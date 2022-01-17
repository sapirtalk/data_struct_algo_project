import java.io.DataOutputStream;
import java.io.IOException;


public class RootedTree {
    TreeNode root;


    public RootedTree() {
        this.root = null;
    }

    static class TreeNode {
        GraphNode data;
        private TreeNode left_child;
        private TreeNode right_sibling;
        private TreeNode parent;

        TreeNode(GraphNode d) {
            data = d;
            left_child = null;
            right_sibling = null;
            parent = null;
        }

        public TreeNode getRoot() {
            if(parent == null){
                return this;
            }
            return parent.getRoot();
        }

    }

    // Adds a sibling to a list with starting with n
    static TreeNode addSibling(TreeNode n, GraphNode data)
    {
        if (n == null)
            return null;
        while (n.right_sibling != null)
            n = n.right_sibling;
        return (n.right_sibling = new TreeNode(data));
    }

    // Add child Node to a Node
    static TreeNode addChild(TreeNode n, GraphNode data)
    {
        if (n == null)
            return null;

        // Check if child list is not empty.
        if (n.left_child != null)
            return addSibling(n.left_child, data);
        else
            return (n.left_child = new TreeNode(data));
    }


    public void printByLayer (DataOutputStream out) throws IOException {
        TreeNode root = this.root.left_child;
        if (root == null)
            return;
        out.writeUTF(root.data.getKey() + "/n");
        if (root.left_child == null)
            return;

        // Create a queue and enqueue root
        LinkedList q = new LinkedList();
        TreeNode curr = root.left_child;
        q.insert(curr);

        while (!q.isEmpty())
        {

            // Take out an item from the queue
            curr = q.head.T_data;
            q.delete(q.head);

            // Print next level of taken out item and enqueue
            // next level's children
            while (curr != null)
            {
                if(curr.right_sibling != null)
                    out.writeUTF(curr.data.getKey() + ",");
                else
                    out.writeUTF(curr.data.getKey() + "/n");
                if (curr.left_child != null)
                {
                    q.insert(curr.left_child);
                }
                curr = curr.right_sibling;
            }
        }
    }

    void printPreorder(TreeNode node ,DataOutputStream out ) throws IOException {
        if (node == null)
            return;

        /* first print data of node */
        if(node.right_sibling == null && node.left_child == null)
            out.write(node.data.getKey());
        else
            out.writeUTF(node.data.getKey() + ",");

        /* then recur on left subtree */
        printPreorder(node.left_child , out);

        /* now recur on right subtree */
        printPreorder(node.right_sibling , out);
    }

    public void preorderPrint(DataOutputStream out) throws IOException {
        TreeNode root = this.root.left_child;

        printPreorder(root , out);

    }

}
