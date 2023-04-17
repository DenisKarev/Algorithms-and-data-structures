public class HW3RBTree {
    public static void main(String[] args) {
        // 24 5 1 15 3 8 13 16 values to add %))
        RBTree tree = new RBTree();
        // tree.add(24);
        // tree.add(5);
        // tree.add(1);
        // tree.add(15);
        // tree.add(3);
        // tree.add(8);
        // tree.add(13);
        // tree.add(16);
        // tree.printTree();
        tree.add(10);
        tree.add(20);
        tree.add(30);
        tree.add(40);
        tree.add(50);
        tree.add(25);
        tree.printTree();

    }
}

class RBTree {
    private RBNode root;

    public boolean add(int value) {
        if (this.root != null) { // if root exists
            this.root = addNode(this.root, value);
            if (this.root == null) {
                return false;
            }
            this.root.color = false;
        } else { // if no nodes yet
            this.root = new RBNode(value);
            this.root.color = false;
        }
        return true;
    }

    private RBNode addNode(RBNode node, int value) {
        if (node == null) {
            return new RBNode(value);
        }
        if (node.value > value) {
            node.left = addNode(node.left, value);
        } else if (node.value < value) {
            node.right = addNode(node.right, value);
        } else {
            return null;
        }
        return rebalance(node);
    }

    private RBNode rebalance(RBNode node) {
        if (isRed(node.right) && !isRed(node.left)) {
            node = smallTurnLeft(node);
            node.left.color = !node.left.color;
            node.color = !node.color;
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = smallTurnRight(node);
            node.right.color = !node.right.color;
            node.color = !node.color;
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    private RBNode smallTurnLeft(RBNode node) {
        System.out.printf("<<-- rotation!!\n");
        RBNode child = node.right;
        RBNode childLeft = child.left;

        child.left = node;

        node.right = childLeft;
        return child;
    }

    private RBNode smallTurnRight(RBNode node) {
        System.out.printf("-->> rotation\n");
        RBNode child = node.left;
        RBNode childRight = child.right;

        child.right = node;
        node.left = childRight;

        return child;
    }

    private void flipColors(RBNode node) {
        System.out.printf("cswap\n");
        if (!node.equals(this.root)) {
            node.color = !node.color;
        }
        node.right.color = !node.right.color;
        node.left.color = !node.left.color;
    }

    boolean isRed(RBNode node) {
        return node == null ? false : node.color;
    }

    public void printTree() {
        // String spacer = "";
        printTree(root, "");
            // System.out.printf("%s%s-%d\n", spacer, root.color, root.value);
            // printTree(root.left, spacer + "\t");
            // printTree(root.right, spacer + "\t");
    }

    private void printTree(RBNode node, String spacer) {
        if (node != null) {
            System.out.printf("%s%s-%d\n", spacer, node.color, node.value);
            printTree(node.left, spacer + "\t");
            printTree(node.right, spacer + "\t");
        }
    }

    private class RBNode {
        private RBNode left;
        private RBNode right;
        private int value;

        private boolean color; // true == RED

        public RBNode(int value) {
            this.value = value;
            this.color = true; // new node always RED
            // this.left = null;
            // this.right = null;
        }

    }

}
