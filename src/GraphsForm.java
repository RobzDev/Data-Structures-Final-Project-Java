import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphsForm extends JFrame {
    private Graphs.Graph graph;
    private JTextField txtNode;
    private JTextField txtFrom;
    private JTextField txtTo;
    private JTextField txtWeight;
    private JTextField txtStartNode;
    private JTextArea txtGraphRepresentation;
    private JCheckBox chkDirected;

    public GraphsForm() {
        graph = new Graphs.Graph();
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Graphs Form");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        txtNode = new JTextField();
        txtFrom = new JTextField();
        txtTo = new JTextField();
        txtWeight = new JTextField();
        txtStartNode = new JTextField();
        txtGraphRepresentation = new JTextArea();
        chkDirected = new JCheckBox("Directed");

        JButton btnAddNode = new JButton("Add Node");
        JButton btnAddEdge = new JButton("Add Edge");
        JButton btnRemoveNode = new JButton("Remove Node");
        JButton btnRemoveEdge = new JButton("Remove Edge");
        JButton btnShowDFS = new JButton("Show DFS");
        JButton btnShowBFS = new JButton("Show BFS");
        JButton btnRemoveAll = new JButton("Remove All");

        // Configura la posición y tamaño de los componentes
        txtNode.setBounds(20, 20, 150, 25);
        txtFrom.setBounds(20, 60, 150, 25);
        txtTo.setBounds(20, 100, 150, 25);
        txtWeight.setBounds(20, 140, 150, 25);
        txtStartNode.setBounds(20, 180, 150, 25);
        txtGraphRepresentation.setBounds(20, 220, 300, 200);
        chkDirected.setBounds(200, 60, 100, 25);

        btnAddNode.setBounds(200, 20, 150, 25);
        btnAddEdge.setBounds(200, 100, 150, 25);
        btnRemoveNode.setBounds(200, 140, 150, 25);
        btnRemoveEdge.setBounds(200, 180, 150, 25);
        btnShowDFS.setBounds(20, 430, 150, 25);
        btnShowBFS.setBounds(200, 430, 150, 25);
        btnRemoveAll.setBounds(20, 460, 150, 25);

        // Agrega los componentes a la ventana
        add(txtNode);
        add(txtFrom);
        add(txtTo);
        add(txtWeight);
        add(txtStartNode);
        add(txtGraphRepresentation);
        add(chkDirected);
        add(btnAddNode);
        add(btnAddEdge);
        add(btnRemoveNode);
        add(btnRemoveEdge);
        add(btnShowDFS);
        add(btnShowBFS);
        add(btnRemoveAll);

        // Agrega los listeners de eventos
        btnAddNode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNode();
            }
        });

        btnAddEdge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEdge();
            }
        });

        btnRemoveNode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeNode();
            }
        });

        btnRemoveEdge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEdge();
            }
        });

        btnShowDFS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDFS();
            }
        });

        btnShowBFS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBFS();
            }
        });

        btnRemoveAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
            }
        });
    }

    private void addNode() {
        String nodeName = txtNode.getText().trim();
        if (!nodeName.isEmpty()) {
            Graphs.GraphNode newNode = new Graphs.GraphNode(nodeName);
            graph.addNode(newNode);
            JOptionPane.showMessageDialog(this, "Node added: " + nodeName);
            txtNode.setText("");
            updateGraphRepresentation();
        } else {
            JOptionPane.showMessageDialog(this, "Node name cannot be empty.");
        }
    }

    private void addEdge() {
        String fromName = txtFrom.getText().trim();
        String toName = txtTo.getText().trim();
        int weight = 0;

        if (!txtWeight.getText().trim().isEmpty()) {
            try {
                weight = Integer.parseInt(txtWeight.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Weight must be a number.");
                return;
            }
        }

        Graphs.GraphNode fromNode = findNodeByName(fromName);
        Graphs.GraphNode toNode = findNodeByName(toName);

        if (fromNode != null && toNode != null) {
            if (chkDirected.isSelected()) {
                graph.addEdge(fromNode, toNode, weight);
            } else {
                graph.addNoDirectedEdge(fromNode, toNode, weight);
            }
            JOptionPane.showMessageDialog(this, "Edge added from " + fromName + " to " + toName);
            txtFrom.setText("");
            txtTo.setText("");
            txtWeight.setText("");
            updateGraphRepresentation();
        } else {
            JOptionPane.showMessageDialog(this, "Both nodes must exist.");
        }
    }

    private void removeNode() {
        String nodeName = txtNode.getText().trim();
        Graphs.GraphNode nodeToRemove = findNodeByName(nodeName);
        if (nodeToRemove != null) {
            graph.removeNode(nodeToRemove);
            JOptionPane.showMessageDialog(this, "Node removed: " + nodeName);
            txtNode.setText("");
            updateGraphRepresentation();
        } else {
            JOptionPane.showMessageDialog(this, "Node does not exist.");
        }
    }

    private void removeEdge() {
        String fromName = txtFrom.getText().trim();
        String toName = txtTo.getText().trim();

        Graphs.GraphNode fromNode = findNodeByName(fromName);
        Graphs.GraphNode toNode = findNodeByName(toName);

        if (fromNode != null && toNode != null) {
            graph.removeNoDirectedEdge(fromNode, toNode);
            JOptionPane.showMessageDialog(this, "Edge removed from " + fromName + " to " + toName);
            txtFrom.setText("");
            txtTo.setText("");
            updateGraphRepresentation();
        } else {
            JOptionPane.showMessageDialog(this, "Both nodes must exist.");
        }
    }

    private void showDFS() {
        String startNodeName = txtStartNode.getText().trim();
        Graphs.GraphNode startNode = findNodeByName(startNodeName);
        if (startNode != null) {
            String result = graph.dfs(startNode);
            txtGraphRepresentation.setText("DFS: " + result);
        } else {
            JOptionPane.showMessageDialog(this, "Start node does not exist.");
        }
    }

    private void showBFS() {
        String startNodeName = txtStartNode.getText().trim();
        Graphs.GraphNode startNode = findNodeByName(startNodeName);
        if (startNode != null) {
            String result = graph.bfs(startNode);
            txtGraphRepresentation.setText("BFS: " + result);
        } else {
            JOptionPane.showMessageDialog(this, "Start node does not exist.");
        }
    }

    public void removeAll() {
        graph.clear();
        JOptionPane.showMessageDialog(this, "All nodes and edges removed.");
        updateGraphRepresentation();
    }

    private Graphs.GraphNode findNodeByName(String name) {
        for (Graphs.GraphNode node : graph.nodes) {
            if (node.getName().equals(name)) {
                return node;
            }
        }
        return null;
    }

    private void updateGraphRepresentation() {
        txtGraphRepresentation.setText(graph.showAdjacencyListWithWeights());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GraphsForm form = new GraphsForm();
            form.setVisible(true);
        });
    }
}