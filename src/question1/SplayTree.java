package question1;

class SplayTreeNode {
    int data;
    SplayTreeNode left;
    SplayTreeNode right;

    public SplayTreeNode(int data) {
        this.data = data;
    }
}

public class SplayTree {
    private SplayTreeNode root;
    private int rotationCount; // Variable to keep track of rotation count

    // Zig rotation
    private SplayTreeNode zig(SplayTreeNode x) {
        SplayTreeNode y = x.left;
        x.left = y.right;
        y.right = x;
        rotationCount++; // Increment rotation count
        return y;
    }

    // Zag rotation
    private SplayTreeNode zag(SplayTreeNode x) {
        SplayTreeNode y = x.right;
        x.right = y.left;
        y.left = x;
        rotationCount++; // Increment rotation count
        return y;
    }

    // Splay operation to bring the accessed node to the root
    private SplayTreeNode splay(SplayTreeNode root, int data) {
        if (root == null || root.data == data)
            return root;

        // Key is in left subtree
        if (data < root.data) {
            if (root.left == null)
                return root;

            // Zig-Zig (left-left)
            if (data < root.left.data) {
                root.left.left = splay(root.left.left, data);
                root = zig(root);
            }
            // Zig-Zag (left-right)
            else if (data > root.left.data) {
                root.left.right = splay(root.left.right, data);
                if (root.left.right != null)
                    root.left = zag(root.left);
            }

            return (root.left == null) ? root : zig(root);
        }
        // Key is in right subtree
        else {
            if (root.right == null)
                return root;

            // Zag-Zag (right-right)
            if (data > root.right.data) {
                root.right.right = splay(root.right.right, data);
                root = zag(root);
            }
            // Zag-Zig (right-left)
            else if (data < root.right.data) {
                root.right.left = splay(root.right.left, data);
                if (root.right.left != null)
                    root.right = zig(root.right);
            }

            return (root.right == null) ? root : zag(root);
        }
    }

    // Search for a node in the splay tree
    public boolean search(int data) {
        root = splay(root, data);
        return root.data == data;
    }

    // Insert a node into the splay tree
    public void insert(int data) {
        if (root == null) {
            root = new SplayTreeNode(data);
            return;
        }

        root = splay(root, data);

        if (root.data == data)
            return;

        SplayTreeNode newNode = new SplayTreeNode(data);

        if (data < root.data) {
            newNode.right = root;
            newNode.left = root.left;
            root.left = null;
        } else {
            newNode.left = root;
            newNode.right = root.right;
            root.right = null;
        }

        root = newNode;
    }

    public int getRotationCount() {
        return rotationCount;
    }
}
