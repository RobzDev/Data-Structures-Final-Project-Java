import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueuesForm extends JFrame {

    private queues.StaticQueue staticQueue;
    private queues.DynamicQueue dynamicQueue;
    private queues.CircularQueue circularQueue;
    private JComboBox<String> cmbQueueType;
    private JTextField txtQueueValue;
    private JTextArea txtAreaQueue;
    private JButton btnEnqueue, btnDequeue, btnPeek, btnSize;

    public QueuesForm() {
        // Set up the frame
        setTitle("Queue Operations");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Initialize the queues
        staticQueue = new queues.StaticQueue(5);
        dynamicQueue = new queues.DynamicQueue();
        circularQueue = new queues.CircularQueue(5);

        // Queue type selection
        JLabel lblQueueType = new JLabel("Select Queue Type:");
        lblQueueType.setBounds(20, 20, 150, 30);
        add(lblQueueType);

        cmbQueueType = new JComboBox<>(new String[]{"Static", "Dynamic", "Circular"});
        cmbQueueType.setBounds(180, 20, 150, 30);
        add(cmbQueueType);

        // Queue value input
        JLabel lblQueueValue = new JLabel("Enter Value:");
        lblQueueValue.setBounds(20, 60, 150, 30);
        add(lblQueueValue);

        txtQueueValue = new JTextField();
        txtQueueValue.setBounds(180, 60, 150, 30);
        add(txtQueueValue);

        // Buttons
        btnEnqueue = new JButton("Enqueue");
        btnEnqueue.setBounds(20, 100, 100, 30);
        add(btnEnqueue);

        btnDequeue = new JButton("Dequeue");
        btnDequeue.setBounds(130, 100, 100, 30);
        add(btnDequeue);

        btnPeek = new JButton("Peek");
        btnPeek.setBounds(240, 100, 100, 30);
        add(btnPeek);

        btnSize = new JButton("Size");
        btnSize.setBounds(20, 140, 100, 30);
        add(btnSize);

        // Text area for displaying queue
        txtAreaQueue = new JTextArea();
        txtAreaQueue.setBounds(20, 180, 340, 150);
        txtAreaQueue.setEditable(false);
        add(txtAreaQueue);

        // Action listeners for buttons
        btnEnqueue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int value = Integer.parseInt(txtQueueValue.getText());
                    String selectedQueue = (String) cmbQueueType.getSelectedItem();

                    switch (selectedQueue) {
                        case "Static":
                            staticQueue.enqueue(value);
                            break;
                        case "Dynamic":
                            dynamicQueue.enqueue(value);
                            break;
                        case "Circular":
                            circularQueue.enqueue(value);
                            break;
                    }
                    updateQueueDisplay();
                    txtQueueValue.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
                }
            }
        });

        btnDequeue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedQueue = (String) cmbQueueType.getSelectedItem();
                String result = "";
                switch (selectedQueue) {
                    case "Static":
                        result = "Dequeued: " + staticQueue.dequeue();
                        break;
                    case "Dynamic":
                        result = "Dequeued: " + dynamicQueue.dequeue();
                        break;
                    case "Circular":
                        result = "Dequeued: " + circularQueue.dequeue();
                        break;
                }
                updateQueueDisplay();
                JOptionPane.showMessageDialog(null, result);
            }
        });

        btnPeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedQueue = (String) cmbQueueType.getSelectedItem();
                String result = "";
                switch (selectedQueue) {
                    case "Static":
                        result = "Peeked: " + staticQueue.peek();
                        break;
                    case "Dynamic":
                        result = "Peeked: " + dynamicQueue.peek();
                        break;
                    case "Circular":
                        result = "Peeked: " + circularQueue.peek();
                        break;
                }
                JOptionPane.showMessageDialog(null, result);
            }
        });

        btnSize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedQueue = (String) cmbQueueType.getSelectedItem();
                String result = "";
                switch (selectedQueue) {
                    case "Static":
                        result = "Size: " + staticQueue.size();
                        break;
                    case "Dynamic":
                        result = "Size: " + dynamicQueue.size();
                        break;
                    case "Circular":
                        result = "Size: " + circularQueue.size();
                        break;
                }
                JOptionPane.showMessageDialog(null, result);
            }
        });
    }

    // Method to update the queue display
    private void updateQueueDisplay() {
        String selectedQueue = (String) cmbQueueType.getSelectedItem();
        txtAreaQueue.setText("");
        switch (selectedQueue) {
            case "Static":
                for (int i = 0; i < staticQueue.size(); i++) {
                    txtAreaQueue.append(staticQueue.dequeue() + "\n");
                }
                break;
            case "Dynamic":
                queues.Node currentNode = dynamicQueue.front;
                while (currentNode != null) {
                    txtAreaQueue.append(currentNode.value + "\n");
                    currentNode = currentNode.next;
                }
                break;
            case "Circular":
                for (int i = 0; i < circularQueue.size(); i++) {
                    txtAreaQueue.append(circularQueue.dequeue() + "\n");
                }
                break;
        }
    }
}
