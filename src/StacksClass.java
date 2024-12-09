import javax.swing.JOptionPane;

public class StacksClass {

    // Static Stack Implementation
    public static class StaticStack {
        private int[] elements; // Array to store stack elements
        private int top;        // Index of the top element

        // Constructor to initialize the stack with a fixed size
        public StaticStack() {
            elements = new int[8];
            top = -1;           // Initially, the stack is empty
        }

        // Method to push an element onto the stack
        public void push(int data) {
            if (isFull()) {
                JOptionPane.showMessageDialog(null, "The static stack is full.");
                return;
            }
            elements[++top] = data; // Increment 'top' and add the data
        }

        // Method to pop the top element from the stack
        public String pop() {
            if (isEmpty()) {
                return "The stack is empty.";
            }
            return Integer.toString(elements[top--]); // Return and decrement 'top'
        }

        // Method to peek at the top element without removing it
        public String peek() {
            if (isEmpty()) {
                return "The stack is empty.";
            }
            return Integer.toString(elements[top]); // Return the value without modifying 'top'
        }

        // Method to check if the stack is empty
        public boolean isEmpty() {
            return top == -1; // Stack is empty if 'top' is -1
        }

        // Method to check if the stack is full
        public boolean isFull() {
            return top == elements.length - 1; // Stack is full if 'top' reaches max size
        }

        // Method to return the size of the stack
        public int size() {
            return top + 1; // Current size is 'top + 1'
        }
    }

    // Dynamic Stack Implementation
    public static class DynamicStack {
        private Node top;

        // Constructor to initialize an empty stack
        public DynamicStack() {
            top = null;
        }

        // Method to push a node onto the stack
        public void push(Node newNode) {
            if (isEmpty()) {
                top = newNode;
                return;
            }

            newNode.setNext(top);
            top = newNode;
        }

        // Method to pop the top node from the stack
        public Node pop() {
            if (!isEmpty()) {
                Node current = top;
                top = top.getNext();
                current.setNext(null);
                return current;
            }
            return null;
        }

        // Method to peek at the top node without removing it
        public Node peek() {
            if (isEmpty()) {
                return null;
            }
            return top;
        }

        // Method to check if the stack is empty
        public boolean isEmpty() {
            return top == null;
        }

        // Method to return the size of the stack
        public int size() {
            int count = 0;
            Node current = top;

            // Traverse the stack from top to bottom
            while (current != null) {
                count++;
                current = current.getNext(); // Move to the next node
            }

            return count; // Return the total number of elements
        }
    }

    // Node Class for Dynamic Stack
    public static class Node {
        private int data;
        private Node next;

        // Constructor
        public Node(int data) {
            this.data = data;
            this.next = null;
        }

        // Getters and Setters
        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
