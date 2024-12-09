import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TreesForm extends JFrame {
    private BinaryTree.Node root;
    private JPanel panelTree;
    private JTextField txtNodeValue;
    private JTextArea txtOutput;

    public TreesForm() {
        setTitle("Binary Tree Operations");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        root = new BinaryTree.Node();

        // Panel para los botones y entrada de texto
        JPanel panelControls = new JPanel();
        panelControls.setLayout(new FlowLayout());

        txtNodeValue = new JTextField(10);
        JButton btnInsertNode = new JButton("Insert");
        JButton btnDelete = new JButton("Delete");
        JButton btnSearch = new JButton("Search");
        JButton btnClear = new JButton("Clear");
        JButton btnPreOrder = new JButton("Pre-Order");
        JButton btnInOrder = new JButton("In-Order");
        JButton btnPostOrder = new JButton("Post-Order");
        txtOutput = new JTextArea(5, 50);
        txtOutput.setEditable(false);

        // Panel para dibujar el árbol
        panelTree = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (root.value != null) {
                    drawNode(g, root, getWidth() / 2, 20, 100);
                }
            }
        };
        panelTree.setPreferredSize(new Dimension(800, 400));
        panelTree.setBackground(Color.WHITE);

        // Agregar componentes al panel de controles
        panelControls.add(new JLabel("Node Value:"));
        panelControls.add(txtNodeValue);
        panelControls.add(btnInsertNode);
        panelControls.add(btnDelete);
        panelControls.add(btnSearch);
        panelControls.add(btnClear);
        panelControls.add(btnPreOrder);
        panelControls.add(btnInOrder);
        panelControls.add(btnPostOrder);

        // Agregar componentes al frame
        add(panelControls, BorderLayout.NORTH);
        add(new JScrollPane(panelTree), BorderLayout.CENTER);
        add(new JScrollPane(txtOutput), BorderLayout.SOUTH);

        // Listeners
        btnInsertNode.addActionListener(e -> {
            try {
                int value = Integer.parseInt(txtNodeValue.getText());
                root.insert(value);
                repaint();
                txtNodeValue.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid integer.");
            }
        });

        btnDelete.addActionListener(e -> {
            try {
                int value = Integer.parseInt(txtNodeValue.getText());
                root = root.delete(root, value);
                repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid integer.");
            }
        });

        btnSearch.addActionListener(e -> {
            try {
                int value = Integer.parseInt(txtNodeValue.getText());
                BinaryTree.Node foundNode = root.search(value);
                JOptionPane.showMessageDialog(this, root.getNodeInfo(foundNode, "Found"));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid integer.");
            }
        });

        btnClear.addActionListener(e -> {
            root = new BinaryTree.Node();
            repaint();
        });

        btnPreOrder.addActionListener(e -> txtOutput.setText(preOrderTraversal(root)));
        btnInOrder.addActionListener(e -> txtOutput.setText(inOrderTraversal(root)));
        btnPostOrder.addActionListener(e -> txtOutput.setText(postOrderTraversal(root)));
    }

    private void drawNode(Graphics g, BinaryTree.Node node, int x, int y, int xOffset) {
        if (node == null) return;

        int panelWidth = panelTree.getWidth();
        int panelHeight = panelTree.getHeight();

        x = Math.max(30, Math.min(panelWidth - 30, x));
        y = Math.max(30, Math.min(panelHeight - 30, y));

        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(x - 15, y - 15, 30, 30);
        g.setColor(Color.BLACK);
        g.drawString(node.value.toString(), x - 10, y + 5);

        if (node.left != null) {
            int leftX = x - xOffset;
            int leftY = y + 60;
            g.drawLine(x, y, leftX, leftY);
            drawNode(g, node.left, leftX, leftY, xOffset / 2);
        }

        if (node.right != null) {
            int rightX = x + xOffset;
            int rightY = y + 60;
            g.drawLine(x, y, rightX, rightY);
            drawNode(g, node.right, rightX, rightY, xOffset / 2);
        }
    }

    private String preOrderTraversal(BinaryTree.Node node) {
        if (node == null) return "";
        return node.value + ", " + preOrderTraversal(node.left) + preOrderTraversal(node.right);
    }

    private String inOrderTraversal(BinaryTree.Node node) {
        if (node == null) return "";
        return inOrderTraversal(node.left) + node.value + ", " + inOrderTraversal(node.right);
    }

    private String postOrderTraversal(BinaryTree.Node node) {
        if (node == null) return "";
        return postOrderTraversal(node.left) + postOrderTraversal(node.right) + node.value + ", ";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TreesForm frame = new TreesForm();
            frame.setVisible(true);
        });
    }
}
