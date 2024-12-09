import javax.swing.*;

public class BinaryTree {
    public static class Node {
        public Node left;
        public Node right;
        public Integer value;

        // Constructor con valor
        public Node(int value) {
            this.value = value;
            left = null;
            right = null;
        }

        // Constructor sin valor
        public Node() {
            value = null;
            left = null;
            right = null;
        }

        // Método para insertar un valor en el árbol
        public void insert(int value) {
            if (this.value == null) {
                this.value = value;
                return;
            }
            if (value < this.value) {
                if (left == null) {
                    left = new Node(value);
                } else {
                    left.insert(value);
                }
            } else {
                if (right == null) {
                    right = new Node(value);
                } else {
                    right.insert(value);
                }
            }
        }

        // Método para buscar un nodo
        public Node search(int value) {
            if (this.value == null) return null;

            if (this.value == value) return this;

            if (value < this.value) {
                return (left != null) ? left.search(value) : null;
            } else {
                return (right != null) ? right.search(value) : null;
            }
        }

        // Método para eliminar un nodo
        public Node delete(Node root, int value) {
            if (root == null) return null;

            if (value < root.value) {
                root.left = delete(root.left, value);
            } else if (value > root.value) {
                root.right = delete(root.right, value);
            } else {
                if (root.left == null && root.right == null) {
                    return null;
                } else if (root.left == null) {
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                } else {
                    Node minNode = findMin(root.right);
                    root.value = minNode.value;
                    root.right = delete(root.right, minNode.value);
                }
            }
            return root;
        }

        // Método para encontrar el nodo con el valor mínimo
        private Node findMin(Node node) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        // Método para obtener información de un nodo
        public String getNodeInfo(Node node, String action) {
            if (node == null) {
                return "Node with value not found.";
            }

            int height = getNodeHeight(node);
            boolean isLeaf = (node.left == null && node.right == null);
            String leafStatus = isLeaf ? "is a leaf node" : "is not a leaf node";

            return String.format("The %s Node with value %d was found at the height %d and %s.",
                    action, node.value, height, leafStatus);
        }

        // Método para obtener la altura de un nodo
        public int getNodeHeight(Node node) {
            if (this == null) return 0;
            int height = 1;
            if (value.equals(node.value)) return height;

            if (node.value < value) {
                return (left != null) ? height + left.getNodeHeight(node) : 0;
            } else {
                return (right != null) ? height + right.getNodeHeight(node) : 0;
            }
        }

        // Método para contar los nodos del árbol
        public int countNodes() {
            int count = 1;
            if (left != null) count += left.countNodes();
            if (right != null) count += right.countNodes();
            return count;
        }

        // Método para obtener la altura del árbol
        public int getHeight() {
            if (this == null) return 0;

            int leftHeight = (left != null) ? left.getHeight() : 0;
            int rightHeight = (right != null) ? right.getHeight() : 0;

            return Math.max(leftHeight, rightHeight) + 1;
        }

        // Método para mostrar información del árbol
        public void treeInfo() {
            int height = getHeight();
            int nodeCount = countNodes();
            int levels = height;

            JOptionPane.showMessageDialog(null,
                    String.format("Tree Information:\nNodes: %d\nHeight: %d\nLevels: %d",
                            nodeCount, height, levels));
        }
    }
}
