import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StacksForm extends JFrame {
    private StacksClass.DynamicStack dynamicStack;
    private StacksClass.StaticStack staticStack;
    private JComboBox<String> cmbStackType;
    private JTextField txtStackValue;
    private JList<String> lstStaticStack;
    private DefaultListModel<String> stackListModel;

    public StacksForm() {
        // Initialize components
        dynamicStack = new StacksClass.DynamicStack();
        staticStack = new StacksClass.StaticStack();
        cmbStackType = new JComboBox<>(new String[]{"Static", "Dynamic"});
        txtStackValue = new JTextField(15);
        stackListModel = new DefaultListModel<>();
        lstStaticStack = new JList<>(stackListModel);

        JButton btnPush = new JButton("Push");
        JButton btnPop = new JButton("Pop");
        JButton btnPeek = new JButton("Peek");
        JButton btnSize = new JButton("Size");

        // Set up the layout
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Select Stack Type:"));
        add(cmbStackType);
        add(new JLabel("Enter Value:"));
        add(txtStackValue);
        add(btnPush);
        add(btnPop);
        add(btnPeek);
        add(btnSize);
        add(new JScrollPane(lstStaticStack));

        // Default selection
        cmbStackType.setSelectedIndex(0);

        // Event listeners
        btnPush.addActionListener(this::btnPush_Click);
        btnPop.addActionListener(this::btnPop_Click);
        btnPeek.addActionListener(this::btnPeek_Click);
        btnSize.addActionListener(this::btnSize_Click);
        cmbStackType.addActionListener(this::cmbStackType_SelectedIndexChanged);

        // Frame settings
        setTitle("Stacks Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void btnPush_Click(ActionEvent e) {
        try {
            int value = Integer.parseInt(txtStackValue.getText());
            String selectedStack = (String) cmbStackType.getSelectedItem();
            if ("Static".equals(selectedStack)) {
                staticStack.push(value);
            } else if ("Dynamic".equals(selectedStack)) {
                StacksClass.Node newNode = new StacksClass.Node(value);
                dynamicStack.push(newNode);
            }
            UpdateStacks();
            txtStackValue.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer value.");
        }
    }

    private void UpdateStacks() {
        stackListModel.clear();
        String selectedStack = (String) cmbStackType.getSelectedItem();
        if ("Static".equals(selectedStack)) {
            StacksClass.StaticStack tempStack = new StacksClass.StaticStack();
            while (!staticStack.isEmpty()) {
                String element = staticStack.pop();
                stackListModel.addElement(element);
                tempStack.push(Integer.parseInt(element));
            }
            while (!tempStack.isEmpty()) {
                staticStack.push(Integer.parseInt(tempStack.pop()));
            }
        } else if ("Dynamic".equals(selectedStack)) {
            StacksClass.Node currentNode = dynamicStack.peek();
            while (currentNode != null) {
                stackListModel.addElement(String.valueOf(currentNode.getData()));
                currentNode = currentNode.getNext();
            }
        }
    }

    private void cmbStackType_SelectedIndexChanged(ActionEvent e) {
        stackListModel.clear();
        UpdateStacks();
    }

    private void btnPop_Click(ActionEvent e) {
        String selectedStack = (String) cmbStackType.getSelectedItem();
        if ("Static".equals(selectedStack)) {
            JOptionPane.showMessageDialog(this, "The popped value is: " + staticStack.pop());
        } else if ("Dynamic".equals(selectedStack)) {
            StacksClass.Node poppedNode = dynamicStack.pop();
            if (poppedNode != null) {
                JOptionPane.showMessageDialog(this, "Element removed from the dynamic stack: " + poppedNode.getData());
            }
        }
        UpdateStacks();
    }

    private void btnPeek_Click(ActionEvent e) {
        String selectedStack = (String) cmbStackType.getSelectedItem();
        if ("Static".equals(selectedStack)) {
            JOptionPane.showMessageDialog(this, "The peeked value is: " + staticStack.peek());
        } else if ("Dynamic".equals(selectedStack)) {
            StacksClass.Node peekedNode = dynamicStack.peek();
            if (peekedNode != null) {
                JOptionPane.showMessageDialog(this, "The peeked value is: " + peekedNode.getData());
            }
        }
    }

    private void btnSize_Click(ActionEvent e) {
        String selectedStack = (String) cmbStackType.getSelectedItem();
        if ("Static".equals(selectedStack)) {
            JOptionPane.showMessageDialog(this, "The size of the stack is: " + staticStack.size());
        } else if ("Dynamic".equals(selectedStack)) {
            JOptionPane.showMessageDialog(this, "The size of the stack is: " + dynamicStack.size());
        }
    }

    public static void main(String[] args) {
        new StacksForm();
    }
}
