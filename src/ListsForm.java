import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ListsForm extends ZBase {
    private JComboBox<String> cmbListType;
    private JTextField txtValue;
    private JList<String> lstSimpleLinkedList;
    private DefaultListModel<String> listModel;

    // List data structures
    private Lists.LinkedList linkedList;
    private Lists.DoublyLinkedList doublyLinkedList;
    private Lists.CircularLinkedList circularLinkedList;
    private Lists.DoublyCircularLinkedList doublyCircularLinkedList;

    public ListsForm() {
        // Set up the form
        setTitle("Lists Data Structures");
        setSize(500, 400);
        setLayout(new BorderLayout());

        // Initialize data structures
        linkedList = new Lists.LinkedList();
        doublyLinkedList = new Lists.DoublyLinkedList();
        circularLinkedList = new Lists.CircularLinkedList();
        doublyCircularLinkedList = new Lists.DoublyCircularLinkedList();

        // Create components
        initComponents();
    }

    private void initComponents() {
        // Input panel
        JPanel inputPanel = new JPanel(new FlowLayout());

        // List type combo box
        cmbListType = new JComboBox<>(new String[]{
                "Simple Linked List",
                "Doubly Linked List",
                "Circular Linked List",
                "Doubly Circular Linked List"
        });
        cmbListType.setSelectedIndex(0);
        cmbListType.addActionListener(this::cmbListTypeActionPerformed);
        inputPanel.add(new JLabel("List Type:"));
        inputPanel.add(cmbListType);

        // Value input
        txtValue = new JTextField(10);
        inputPanel.add(new JLabel("Value:"));
        inputPanel.add(txtValue);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(this::btnAddActionPerformed);
        buttonPanel.add(btnAdd);

        JButton btnRemove = new JButton("Remove");
        btnRemove.addActionListener(this::btnRemoveActionPerformed);
        buttonPanel.add(btnRemove);

        JButton btnContains = new JButton("Contains");
        btnContains.addActionListener(this::btnContainsActionPerformed);
        buttonPanel.add(btnContains);

        JButton btnCount = new JButton("Count");
        btnCount.addActionListener(this::btnCountActionPerformed);
        buttonPanel.add(btnCount);

        // List display
        listModel = new DefaultListModel<>();
        lstSimpleLinkedList = new JList<>(listModel);
        JScrollPane listScrollPane = new JScrollPane(lstSimpleLinkedList);

        // Add components to frame
        add(inputPanel, BorderLayout.NORTH);
        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void cmbListTypeActionPerformed(ActionEvent e) {
        listModel.clear();
    }

    private void btnAddActionPerformed(ActionEvent e) {
        try {
            int value = Integer.parseInt(txtValue.getText());
            String selectedList = (String) cmbListType.getSelectedItem();

            switch (selectedList) {
                case "Simple Linked List":
                    linkedList.add(new Lists.Node(value));
                    updateListBox(linkedList.getHead());
                    break;
                case "Doubly Linked List":
                    doublyLinkedList.add(new Lists.DoublyNode(value));
                    updateListBox(doublyLinkedList.getHead());
                    break;
                case "Circular Linked List":
                    circularLinkedList.add(new Lists.Node(value));
                    updateListBox(circularLinkedList.getHead());
                    break;
                case "Doubly Circular Linked List":
                    doublyCircularLinkedList.add(new Lists.DoublyNode(value));
                    updateListBox(doublyCircularLinkedList.getHead());
                    break;
            }

            txtValue.setText("");
            txtValue.requestFocusInWindow();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer value.");
        }
    }

    private void btnRemoveActionPerformed(ActionEvent e) {
        try {
            int value = Integer.parseInt(txtValue.getText());
            String selectedList = (String) cmbListType.getSelectedItem();

            switch (selectedList) {
                case "Simple Linked List":
                    linkedList.remove(value);
                    updateListBox(linkedList.getHead());
                    break;
                case "Doubly Linked List":
                    doublyLinkedList.remove(value);
                    updateListBox(doublyLinkedList.getHead());
                    break;
                case "Circular Linked List":
                    circularLinkedList.remove(value);
                    updateListBox(circularLinkedList.getHead());
                    break;
                case "Doubly Circular Linked List":
                    doublyCircularLinkedList.remove(value);
                    updateListBox(doublyCircularLinkedList.getHead());
                    break;
            }

            txtValue.setText("");
            txtValue.requestFocusInWindow();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer value.");
        }
    }

    private void btnContainsActionPerformed(ActionEvent e) {
        try {
            int value = Integer.parseInt(txtValue.getText());
            String selectedList = (String) cmbListType.getSelectedItem();
            boolean contains = false;

            switch (selectedList) {
                case "Simple Linked List":
                    contains = linkedList.contains(value);
                    break;
                case "Doubly Linked List":
                    contains = doublyLinkedList.contains(value);
                    break;
                case "Circular Linked List":
                    contains = circularLinkedList.contains(value);
                    break;
                case "Doubly Circular Linked List":
                    contains = doublyCircularLinkedList.contains(value);
                    break;
            }

            if (contains) {
                JOptionPane.showMessageDialog(this, "The list contains the value.");
            } else {
                JOptionPane.showMessageDialog(this, "The list does not contain the value.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer value.");
        }
    }

    private void btnCountActionPerformed(ActionEvent e) {
        String selectedList = (String) cmbListType.getSelectedItem();
        int count = 0;

        switch (selectedList) {
            case "Simple Linked List":
                count = linkedList.count();
                break;
            case "Doubly Linked List":
                count = doublyLinkedList.count();
                break;
            case "Circular Linked List":
                count = circularLinkedList.count();
                break;
            case "Doubly Circular Linked List":
                count = doublyCircularLinkedList.count();
                break;
        }

        JOptionPane.showMessageDialog(this, "The list has " + count + " elements.");
    }

    private void updateListBox(Object head) {
        listModel.clear();

        if (head instanceof Lists.Node headNode) {
            // Simple or Circular Linked List
            Lists.Node current = headNode;
            do {
                if (current == null) break;
                listModel.addElement(String.valueOf(current.getValue()));
                current = current.getNext();
            } while (current != null && current != headNode);
        } else if (head instanceof Lists.DoublyNode headDoubleNode) {
            // Doubly or Doubly Circular Linked List
            Lists.DoublyNode current = headDoubleNode;
            do {
                if (current == null) break;
                listModel.addElement(String.valueOf(current.getValue()));
                current = current.getNext();
            } while (current != null && current != headDoubleNode);
        }
    }
}